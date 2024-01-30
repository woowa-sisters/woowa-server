package com.woowaSisters.woowaSisters.dto;

import com.woowaSisters.woowaSisters.domain.meeting.Meeting;
import com.woowaSisters.woowaSisters.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class MeetingRequestDto {
    private String meetingTitle;
    private UUID userUuid;
    private Integer meetingAttendees;
    private long meetingTime;
    private String meetingLocation;
    private String meetingContent;
/*
    private Long bookId;
    private UUID meetingUuid;

*/
    public MeetingRequestDto(String meetingTitle, UUID userUuid, Integer meetingAttendees,
                             long meetingTime,String meetingLocation,String meetingContent) {
        this.meetingTitle = meetingTitle;
        this.userUuid = userUuid;
        this.meetingAttendees = meetingAttendees;
        this.meetingTime = meetingTime;
        this.meetingLocation = meetingLocation;
        this.meetingContent = meetingContent;
    }

    public Meeting toEntity(User user) {
        return Meeting.builder()
                .meetingTitle(meetingTitle)
                .user(user)
                .meetingAttendees(meetingAttendees)
                .meetingTime(meetingTime)
                .meetingLocation(meetingLocation)
                .meetingContent(meetingContent)
                .build();
    }

}