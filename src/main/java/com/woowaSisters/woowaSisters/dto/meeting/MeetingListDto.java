package com.woowaSisters.woowaSisters.dto.meeting;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MeetingListDto {
    private String sort;
    private int num;

    @Builder
    public MeetingListDto(String sort, int num) {
        this.sort = sort;
        this.num = num;
    }
}
