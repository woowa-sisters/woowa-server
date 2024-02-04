package com.woowaSisters.woowaSisters.dto.meeting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class MeetingJoinRequestDto {
    private UUID meetingUuid;
    private UUID userUuid;
}
