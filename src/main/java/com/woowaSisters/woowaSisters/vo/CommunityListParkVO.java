package com.woowaSisters.woowaSisters.vo;

import com.woowaSisters.woowaSisters.domain.community.Community;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CommunityListParkVO {
    private UUID communityUuid;
    private String article;
    private String userNickname;
    private String parkName;
    private LocalDateTime createdAt;

    public CommunityListParkVO(Community entity) {
        this.communityUuid = entity.getCommunityUuid();
        this.article = entity.getArticle();
        this.userNickname = entity.getUser().getNickname();
        this.parkName = entity.getParks().getParkName();
        this.createdAt = entity.getCreatedAt();
    }
}
