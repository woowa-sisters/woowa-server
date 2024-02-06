package com.woowaSisters.woowaSisters.dto.meeting;

import com.woowaSisters.woowaSisters.domain.meeting.Meeting;
import com.woowaSisters.woowaSisters.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class MeetingSaveDto {
    private String meetingTitle;
    private Integer meetingAttendees;
    private long meetingTime;
    private String meetingLocation;
    private String meetingContent;
    private String bookId;
    private String meetingFee;
    private UUID meetingUuid;

    @Builder
    public MeetingSaveDto(String meetingTitle,Integer meetingAttendees,
                          long meetingTime, String meetingLocation, String meetingContent,
                          String bookId,String meetingFee) {
        this.meetingTitle = meetingTitle;
        this.meetingAttendees = meetingAttendees;
        this.meetingTime = meetingTime;
        this.meetingLocation = meetingLocation;
        this.meetingContent = meetingContent;
        this.bookId = bookId;
        this.meetingFee = meetingFee;
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
                .meetingFee(meetingFee)
                .build();
    }

}