package com.woowaSisters.woowaSisters.dto.meeting;

import com.woowaSisters.woowaSisters.domain.meeting.Meeting;
import com.woowaSisters.woowaSisters.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class MeetingSaveDto {
    private String meetingTitle;
    private UUID userUuid;
    private Integer meetingAttendees;
    private long meetingTime;
    private String meetingLocation;
    private String meetingContent;
    private Long bookId;
    private UUID meetingUuid;

    @Builder
    public MeetingSaveDto(String meetingTitle, UUID userUuid, Integer meetingAttendees,
                          long meetingTime, String meetingLocation, String meetingContent,
                          Long bookId, UUID meetingUuid) {
        this.meetingTitle = meetingTitle;
        this.userUuid = userUuid;
        this.meetingAttendees = meetingAttendees;
        this.meetingTime = meetingTime;
        this.meetingLocation = meetingLocation;
        this.meetingContent = meetingContent;
        this.bookId = bookId;
        this.meetingUuid = meetingUuid;
    }

    public Meeting toEntity(User user) {
        return Meeting.builder()
                .meetingTitle(meetingTitle)
                .user(user)
                .meetingAttendees(meetingAttendees)
                .meetingTime(meetingTime)
                .meetingLocation(meetingLocation)
                .meetingContent(meetingContent)
                .bookId(bookId)
                .meetingUuid(meetingUuid)
                .build();
    }

}