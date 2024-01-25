package com.woowaSisters.woowaSisters.dto;

import com.woowaSisters.woowaSisters.domain.community.Community;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class CommunityResponseDto {
    private UUID communityUuid;
    private String article;
    private LocalDateTime createdAt;
    private String userNickname;

    public CommunityResponseDto(Community entity) {
        this.communityUuid = entity.getCommunityUuid();
        this.article= entity.getArticle();
        this.createdAt = entity.getCreatedAt();
        this.userNickname = entity.getUser().getNickname();

    }
}
