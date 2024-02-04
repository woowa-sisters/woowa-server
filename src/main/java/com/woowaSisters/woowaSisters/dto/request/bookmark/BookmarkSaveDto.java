
package com.woowaSisters.woowaSisters.dto.request.bookmark;

import com.woowaSisters.woowaSisters.domain.bookmark.Bookmark;
import com.woowaSisters.woowaSisters.domain.park.Parks;
import com.woowaSisters.woowaSisters.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class BookmarkSaveDto {
    private UUID userUuid;
    private UUID parkUuid;

    @Builder
    public BookmarkSaveDto(UUID userUuid, UUID parkUuid) {
        this.userUuid = userUuid;
        this.parkUuid = parkUuid;
    }

    //dto -> entity
    public Bookmark toEntity(User user, Parks parks) {
        return Bookmark.builder()
                .user(user)
                .parks(parks)
                .build();
    }
}