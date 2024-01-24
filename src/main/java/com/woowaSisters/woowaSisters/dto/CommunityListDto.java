package com.woowaSisters.woowaSisters.dto;

import com.woowaSisters.woowaSisters.domain.community.Community;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter

public class CommunityListDto {
    private UUID communityUuid;
    private String article;
    private String userNickname;
    private String parkName;
    private LocalDateTime createdAt;

    public CommunityListDto(Community entity) {
        this.communityUuid = entity.getCommunityUuid();
        this.article = entity.getArticle();
        this.userNickname = entity.getUser().getNickname();
        this.parkName = entity.getParks().getParkName();
        this.createdAt = entity.getCreatedAt();
    }
}
