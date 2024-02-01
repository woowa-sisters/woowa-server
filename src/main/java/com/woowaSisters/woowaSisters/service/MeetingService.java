package com.woowaSisters.woowaSisters.service;
import com.woowaSisters.woowaSisters.domain.meeting.Meeting;
import com.woowaSisters.woowaSisters.domain.user.User;
import com.woowaSisters.woowaSisters.dto.meeting.MeetingJoinRequestDto;
import com.woowaSisters.woowaSisters.dto.meeting.MeetingListDto;
import com.woowaSisters.woowaSisters.dto.meeting.MeetingSaveDto;
import com.woowaSisters.woowaSisters.dto.meeting.MeetingResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface MeetingService {

    Meeting createMeeting(User user, MeetingSaveDto meetingSaveDto);

    Meeting updateMeeting(UUID meetingUuid, MeetingResponseDto updateDto);

    List<Meeting> getMeetings(MeetingListDto listDto);

/*    List<Meeting> getLatestMeetings();

    List<Meeting> getAllLatestMeetings();

    List<Meeting> getMeetingTime();

    List<Meeting> getAllByMeetingTime();*/

    Optional<MeetingResponseDto> getMeetingDetailById(UUID id);

    boolean joinMeeting(MeetingJoinRequestDto requestDto);

    boolean  cancelJoinMeeting(MeetingJoinRequestDto requestDto);

    boolean subscribeToMeeting(UUID meetingUuid, UUID userUuid);

    boolean unsubscribeFromMeeting(UUID meetingUuid, UUID userUuid);

    Set<Meeting> getSubscribedMeetings(UUID userUuid);
}
