package com.woowaSisters.woowaSisters.dto;

import com.woowaSisters.woowaSisters.domain.comment.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class CommentSaveDTO {
    private Long postId;
    private Long userId;
    private Long relationCm;
    private String description;

    @Builder
    public CommentSaveDTO(Long postId, Long userId, Long relationCm, String description) {
        this.postId = postId;
        this.userId = userId;
        this.relationCm = relationCm;
        this.description = description;
    }

    //dto -> entity
    public Comment toEntity() {
        return Comment.builder()
                .datetime(LocalDateTime.now())
                .postId(postId)
                .userId(userId)
                .relationCm(relationCm)
                .description(description)
                .build();
    }
    /*public Comment toEntity(User user, Meeting metting, UUID relationCm, String description) {
        return Comment.builder()
                .user(user)
                .meeting(metting)
                .relationCm(relationCm)
                .description(description)
                .build();
    }*/
}
