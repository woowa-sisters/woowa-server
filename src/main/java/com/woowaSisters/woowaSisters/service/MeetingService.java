package com.woowaSisters.woowaSisters.service;
import com.woowaSisters.woowaSisters.domain.meeting.Meeting;

import java.util.List;
import java.util.Optional;

public interface MeetingService {

    Meeting createMeeting(Meeting meeting);

    Meeting updateMeeting(Meeting meeting);

    List<Meeting> getLatestMeetings();

    List<Meeting> getClosingSoonMeetings();

    List<Meeting> getAllLatestMeetings();

    List<Meeting> getAllClosingSoonMeetings();

    Optional<Meeting> getMeetingById(Long id);

    boolean joinMeeting(Long id);

    boolean leaveMeeting(Long id);
}
