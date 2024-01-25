/*
package com.woowaSisters.woowaSisters.dto;

import com.woowaSisters.woowaSisters.domain.community.Community;
import com.woowaSisters.woowaSisters.domain.user.User;
import com.woowaSisters.woowaSisters.domain.park.Parks;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor

public class CommunitySaveDto {
    private String article;
    private UUID userUuid;
    private UUID parkUuid;

    @Builder
    public CommunitySaveDto(String article, UUID userUuid, UUID parkUuid) {
        this.article = article;
        this.userUuid = userUuid;
        this.parkUuid = parkUuid;
    }

    //dto -> entity
    public Community toEntity(User user, Parks parks) {
        return Community.builder()
                .article(article)
                .user(user)
                .parks(parks)
                .build();
    }
}
*/
