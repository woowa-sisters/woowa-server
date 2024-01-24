package com.woowaSisters.woowaSisters.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommunityUpdateRequestDto {
    private String article;

    @Builder
    public CommunityUpdateRequestDto(String article) {
        this.article = article;
    }
}
