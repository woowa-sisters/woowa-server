package com.woowaSisters.woowaSisters.service;
import com.woowaSisters.woowaSisters.domain.meeting.Meeting;
import com.woowaSisters.woowaSisters.domain.user.User;
import com.woowaSisters.woowaSisters.dto.MeetingRequestDto;

import java.util.List;
import java.util.Optional;

public interface MeetingService {
/*
    Meeting createMeeting(Meeting meeting);
    */
    Meeting createMeeting(User user, MeetingRequestDto meetingRequestDto);
/*
    Meeting updateMeeting(Meeting meeting);
*/
    List<Meeting> getLatestMeetings();

    List<Meeting> getAllLatestMeetings();

    List<Meeting> getMeetingTime();

    List<Meeting> getAllByMeetingTime();


    Optional<Meeting> getMeetingById(Long id);

    boolean joinMeeting(Long id);

    boolean leaveMeeting(Long id);
}
