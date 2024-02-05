package com.woowaSisters.woowaSisters.service;
import com.woowaSisters.woowaSisters.domain.meeting.Meeting;
import com.woowaSisters.woowaSisters.domain.meeting.MeetingRepository;
import com.woowaSisters.woowaSisters.domain.user.User;
import com.woowaSisters.woowaSisters.domain.user.UserRepository;
import com.woowaSisters.woowaSisters.dto.meeting.MeetingJoinRequestDto;
import com.woowaSisters.woowaSisters.dto.meeting.MeetingListDto;
import com.woowaSisters.woowaSisters.dto.meeting.MeetingSaveDto;
import com.woowaSisters.woowaSisters.dto.meeting.MeetingResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
@Transactional
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;


    @Autowired
    public MeetingServiceImpl(MeetingRepository meetingRepository, UserRepository userRepository) {
        this.meetingRepository = meetingRepository;
        this.userRepository = userRepository;
    }

    // 모임 생성
    @Override
    public Meeting createMeeting(User user, MeetingSaveDto meetingSaveDto){
        try {
            // 예외 처리
            if (meetingSaveDto.getMeetingTitle() == null || meetingSaveDto.getMeetingTitle().isEmpty()) {
                throw new IllegalArgumentException("모임 제목을 입력하세요");
            }
            if (meetingSaveDto.getMeetingAttendees() == null || meetingSaveDto.getMeetingAttendees() <= 0) {
                throw new IllegalArgumentException("모임 최소 인원수는 1명입니다");
            }
            if (meetingSaveDto.getMeetingTime() <= 0) {
                throw new IllegalArgumentException("모임 시간은 필수 요소입니다");
            }
            if (meetingSaveDto.getMeetingLocation() == null || meetingSaveDto.getMeetingLocation().isEmpty()) {
                throw new IllegalArgumentException("모임 장소를 입력하세요");
            }
            Meeting meeting = meetingSaveDto.toEntity(user);

            // 실제로 저장
            return meetingRepository.save(meeting);

        } catch (Exception e) {
            // 예외 로깅
            e.printStackTrace();
            // 필요한 경우 사용자 정의 예외를 던집니다.
            throw new RuntimeException("모임 저장 중 에러가 발생했습니다.", e);
        }


    }


    @Override
    public Meeting updateMeeting(UUID meetingUuid, MeetingResponseDto updateDto) {
        Meeting existingMeeting = meetingRepository.findById(meetingUuid)
                .orElseThrow(() -> new EntityNotFoundException("모임을 찾을 수 없습니다"));

        // 업데이트에 필요한 값만 변경
        if (updateDto.getMeetingTitle() != null && !updateDto.getMeetingTitle().isEmpty()) {
            existingMeeting.setMeetingTitle(updateDto.getMeetingTitle());
        }
        if (updateDto.getMeetingAttendees() != null && updateDto.getMeetingAttendees() > 0) {
            existingMeeting.setMeetingAttendees(updateDto.getMeetingAttendees());
        }
        if (updateDto.getMeetingTime() > 0) {
            existingMeeting.setMeetingTime(updateDto.getMeetingTime());
        }
        if (updateDto.getMeetingLocation() != null && !updateDto.getMeetingLocation().isEmpty()) {
            existingMeeting.setMeetingLocation(updateDto.getMeetingLocation());
        }
        if (updateDto.getMeetingContent() != null) {
            existingMeeting.setMeetingContent(updateDto.getMeetingContent());
        }

        // 저장된 모임 업데이트
        return meetingRepository.save(existingMeeting);
    }

    public List<Meeting> getMeetings(MeetingListDto listDto) {
        List<Meeting> meetings;

        switch (listDto.getSort()) {
            case "latest":
                meetings = meetingRepository.findTop10ByOrderByMeetingCreatedAtDesc();
                break;
            case "all-latest":
                meetings = meetingRepository.findAllByOrderByMeetingCreatedAtDesc();
                break;
            case "upcoming-top10":
                meetings = meetingRepository.findUpcomingMeetingsTop10();
                break;
            case "all-upcoming":
                meetings = meetingRepository.findAllUpcomingMeetings();
                break;
            default:
                throw new IllegalArgumentException("유효하지 않은 정렬 옵션입니다.");
        }

        return meetings;
    }

    //모임 상세 페이지 조회
    @Override
    public Optional<MeetingResponseDto> getMeetingDetailById(UUID id) {
        Optional<Meeting> meetingOptional = meetingRepository.findById(id);
        return meetingOptional.map(this::mapToMeetingResponseDto);
    }

    private MeetingResponseDto mapToMeetingResponseDto(Meeting meeting) {
        return MeetingResponseDto.builder()
                .meetingUuid(meeting.getMeetingUuid())
                .meetingTitle(meeting.getMeetingTitle())
                .meetingAttendees(meeting.getMeetingAttendees())
                .meetingTime(meeting.getMeetingTime())
                .meetingLocation(meeting.getMeetingLocation())
                .meetingContent(meeting.getMeetingContent())
                .build();
    }

    //모임 참여
    @Override
    public boolean joinMeeting(MeetingJoinRequestDto requestDto) {
        UUID meetingUuid = requestDto.getMeetingUuid();
        UUID userUuid = requestDto.getUserUuid();

        // 해당 모임과 사용자를 검색
        Optional<Meeting> meetingOptional = meetingRepository.findById(meetingUuid);
        Optional<User> userOptional = userRepository.findById(userUuid);

        if (meetingOptional.isPresent() && userOptional.isPresent()) {
            Meeting meeting = meetingOptional.get();
            User user = userOptional.get();

            // 참여 가능한지 확인
            if (!meeting.isFull()) {
                // 모임에 참여한 멤버 목록에 추가
                meeting.getMembers().add(user);
                meetingRepository.save(meeting);
                return true; // 성공적으로 참여
            } else {
                return false; // 모임이 가득 참
            }
        }

        return false; // 모임 또는 사용자를 찾을 수 없음
    }

    //모임 참여 취소
    @Override
    public boolean cancelJoinMeeting(MeetingJoinRequestDto requestDto) {
        UUID meetingUuid = requestDto.getMeetingUuid();
        UUID userUuid = requestDto.getUserUuid();

        // 해당 모임과 사용자를 검색
        Optional<Meeting> meetingOptional = meetingRepository.findById(meetingUuid);
        Optional<User> userOptional = userRepository.findById(userUuid);

        if (meetingOptional.isPresent() && userOptional.isPresent()) {
            Meeting meeting = meetingOptional.get();
            User user = userOptional.get();

            // 참여 여부 확인 후 취소
            if (meeting.getMembers().contains(user)) {
                meeting.getMembers().remove(user);
                meetingRepository.save(meeting);
                return true; // 성공적으로 참여 취소
            } else {
                return false; // 사용자가 해당 모임에 참여하지 않음
            }
        }

        return false; // 모임 또는 사용자를 찾을 수 없음
    }

    //모임 구독
    public boolean subscribeToMeeting(UUID meetingUuid, UUID userUuid) {
        Optional<Meeting> meetingOptional = meetingRepository.findById(meetingUuid);
        Optional<User> userOptional = userRepository.findById(userUuid);

        if (meetingOptional.isPresent() && userOptional.isPresent()) {
            Meeting meeting = meetingOptional.get();
            User user = userOptional.get();

            if (!meeting.getSubscribers().contains(user)) {
                meeting.getSubscribers().add(user);
                meetingRepository.save(meeting);
                return true; // 성공적으로 구독
            }
        }

        return false; // 모임 또는 사용자를 찾을 수 없거나 이미 구독 중
    }

    // 모임 구독 취소
    public boolean unsubscribeFromMeeting(UUID meetingUuid, UUID userUuid) {
        Optional<Meeting> meetingOptional = meetingRepository.findById(meetingUuid);
        Optional<User> userOptional = userRepository.findById(userUuid);

        if (meetingOptional.isPresent() && userOptional.isPresent()) {
            Meeting meeting = meetingOptional.get();
            User user = userOptional.get();

            if (meeting.getSubscribers().contains(user)) {
                meeting.getSubscribers().remove(user);
                meetingRepository.save(meeting);
                return true; // 성공적으로 구독 취소
            }
        }

        return false; // 모임 또는 사용자를 찾을 수 없거나 구독 중이 아님
    }

    public Set<Meeting> getSubscribedMeetings(UUID userUuid) {
        Optional<User> userOptional = userRepository.findById(userUuid);

//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            return user.getSubscribedMeetings();
//        }

        return Collections.emptySet(); // 사용자를 찾을 수 없을 경우 빈 Set 반환
    }
}
