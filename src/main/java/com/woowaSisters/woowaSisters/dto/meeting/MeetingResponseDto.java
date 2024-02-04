package com.woowaSisters.woowaSisters.dto.meeting;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class MeetingResponseDto {
    private UUID meetingUuid;
    private String meetingTitle;
    private Integer meetingAttendees;
    private long meetingTime;
    private String meetingLocation;
    private String meetingContent;

    @Builder
    public MeetingResponseDto(UUID meetingUuid,String meetingTitle, Integer meetingAttendees,
                              long meetingTime, String meetingLocation, String meetingContent) {
        this.meetingUuid = meetingUuid;
        this.meetingTitle = meetingTitle;
        this.meetingAttendees = meetingAttendees;
        this.meetingTime = meetingTime;
        this.meetingLocation = meetingLocation;
        this.meetingContent = meetingContent;
    }
}
