package com.woowaSisters.woowaSisters.service;
import com.woowaSisters.woowaSisters.domain.meeting.Meeting;
import com.woowaSisters.woowaSisters.domain.meeting.MeetingRepository;
import com.woowaSisters.woowaSisters.domain.subscribeMeeting.SubscribeMeeting;
import com.woowaSisters.woowaSisters.domain.subscribeMeeting.SubscribeMeetingRepository;
import com.woowaSisters.woowaSisters.domain.user.User;
import com.woowaSisters.woowaSisters.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.UUID;

@Service
@Component
public class SubscribeMeetingServiceImpl implements SubscribeMeetingService {

    private final SubscribeMeetingRepository subscribeMeetingRepository;

    private final MeetingRepository meetingRepository;

    private final UserRepository userRepository;

    @Autowired
    public SubscribeMeetingServiceImpl(SubscribeMeetingRepository subscribeMeetingRepository, UserRepository userRepository, MeetingRepository meetingRepository) {
        this.subscribeMeetingRepository = subscribeMeetingRepository;
        this.userRepository = userRepository;
        this.meetingRepository = meetingRepository;
    }


    @Override
    @Transactional
    public void subscribeToMeeting(UUID userUuid, UUID meetingUuid) {
        // 사용자와 모임이 존재하는지 확인
        User user = Objects.requireNonNull(userRepository.findByUserUuid(userUuid), "User not found with id: " + userUuid);


        Meeting meeting = Objects.requireNonNull(meetingRepository.findByMeetingUuid(meetingUuid), "Meeting not found with id: " + meetingUuid);


        // 이미 구독 중인지 확인
        if (subscribeMeetingRepository.existsByUserAndMeeting(user, meeting)) {
            throw new DataIntegrityViolationException("User is already subscribed to the meeting");
        }

        // 모임에 구독 추가
        SubscribeMeeting subscribeMeeting = new SubscribeMeeting();
        subscribeMeeting.setUser(user);
        subscribeMeeting.setMeeting(meeting);
        subscribeMeetingRepository.save(subscribeMeeting);
    }

    @Override
    @Transactional
    public void cancelSubscription(UUID userUuid, UUID meetingUuid) {
        // 사용자와 모임이 존재하는지 확인
        User user = Objects.requireNonNull(userRepository.findByUserUuid(userUuid), "User not found with id: " + userUuid);

        Meeting meeting = Objects.requireNonNull(meetingRepository.findByMeetingUuid(meetingUuid), "Meeting not found with id: " + meetingUuid);


        // 구독이 존재하는지 확인
        if (!subscribeMeetingRepository.existsByUserAndMeeting(user, meeting)) {
            throw new DataIntegrityViolationException("User is not subscribed to the meeting");
        }

        // 모임의 구독 취소
        subscribeMeetingRepository.deleteByUserAndMeeting(user, meeting);
    }

}

