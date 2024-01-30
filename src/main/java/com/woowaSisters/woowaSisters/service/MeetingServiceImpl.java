package com.woowaSisters.woowaSisters.service;
import com.woowaSisters.woowaSisters.domain.community.Community;
import com.woowaSisters.woowaSisters.domain.meeting.Meeting;
import com.woowaSisters.woowaSisters.domain.meeting.MeetingRepository;
import com.woowaSisters.woowaSisters.domain.park.Parks;
import com.woowaSisters.woowaSisters.domain.user.User;
import com.woowaSisters.woowaSisters.dto.CommunitySaveDto;
import com.woowaSisters.woowaSisters.dto.MeetingRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetingRepository;

    @Autowired
    public MeetingServiceImpl(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    // 모임 생성
    @Override
    public Meeting createMeeting(User user, MeetingRequestDto meetingRequestDto){
        // 예외 처리
        if (meetingRequestDto.getMeetingTitle() == null || meetingRequestDto.getMeetingTitle().isEmpty()) {
            throw new IllegalArgumentException("모임 제목을 입력하세요");
        }
        if (meetingRequestDto.getMeetingAttendees() == null || meetingRequestDto.getMeetingAttendees() <= 0) {
            throw new IllegalArgumentException("모임 최소 인원수는 1명입니다");
        }
        if (meetingRequestDto.getMeetingTime() <= 0) {
            throw new IllegalArgumentException("모임 시간은 필수 요소입니다");
        }
        if (meetingRequestDto.getMeetingLocation() == null || meetingRequestDto.getMeetingLocation().isEmpty()) {
            throw new IllegalArgumentException("모임 장소를 입력하세요");
        }
        Meeting meeting = meetingRequestDto.toEntity(user);

        // 실제로 저장
        return meetingRepository.save(meeting);

    }


 /*   @Override
    public Meeting updateMeeting(Meeting meeting) {
        // 모임 업데이트
        if (meeting.getTitle() == null || meeting.getTitle().isEmpty()) {
            throw new IllegalArgumentException("모임 제목은 필수 요소 입니다");
        }
        if (meeting.getMeetingAttendees() == null || meeting.getMeetingAttendees() <= 0) {
            throw new IllegalArgumentException("모임 최소 인원수는 1명입니다");
        }
        if (meeting.getMeetingTime() == 0) {
            throw new IllegalArgumentException("모임 시간은 필수 요소입니다");
        }
        if (meeting.getMeetingLocation() == null || meeting.getMeetingLocation().isEmpty()) {
            throw new IllegalArgumentException("모임 장소는 필수 요소 입니다");
        }
        // 실제로 저장
        return meetingRepository.save(meeting);
    }
*/
    @Override
    public List<Meeting> getLatestMeetings() {
        // 최신 모임 목록 가져오기
        // 가장 최근 10개의 모임을 가져오도록 설정
        return meetingRepository.findTop10ByOrderByMeetingCreatedAtDesc();
    }

    @Override
    public List<Meeting> getAllLatestMeetings() {
        // 전체 최신 모임 목록 가져오기
        return meetingRepository.findAllByOrderByMeetingCreatedAtDesc();
    }


    @Override
    public List<Meeting> getMeetingTime() {
        // 마감임박한 모임 목록 가져오기
        // 마감일이 7일 이내인 모임을 가져오도록 설정
        return meetingRepository.findByMeetingTime(System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000));
    }


    @Override
      public List<Meeting> getAllByMeetingTime() {
          // 전체 마감 임박한 모임 목록 가져오기
          return meetingRepository.findAllByMeetingTime(System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000));
      }


    @Override
    public Optional<Meeting> getMeetingById(Long id) {
        // 특정 ID에 해당하는 모임 가져오기
        return meetingRepository.findById(id);
    }

    @Override
    public boolean joinMeeting(Long id) {
        // 모임 참여
        //모임에 참여 조건을 확인하고 참여가 가능하면 true 반환
        //참여 인원이 꽉 차지 않았으면 참여 가능
        Optional<Meeting> meetingOptional = meetingRepository.findById(id);
        if (meetingOptional.isPresent()) {
            Meeting meeting = meetingOptional.get();
            if (meeting.getMeetingAttendees() < 10) {
                meeting.setMeetingAttendees(meeting.getMeetingAttendees() + 1);
                meetingRepository.save(meeting);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean leaveMeeting(Long id) {
        // 모임 참여 취소
        //이미 참여한 모임에 대해서만 취소 가능하도록 설정
        Optional<Meeting> meetingOptional = meetingRepository.findById(id);
        if (meetingOptional.isPresent()) {
            Meeting meeting = meetingOptional.get();
            if (meeting.getMeetingAttendees() > 0) {
                meeting.setMeetingAttendees(meeting.getMeetingAttendees() - 1);
                meetingRepository.save(meeting);
                return true;
            }
        }
        return false;
    }
}
