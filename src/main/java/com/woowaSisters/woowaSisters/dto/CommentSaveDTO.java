package com.woowaSisters.woowaSisters.dto;

import com.woowaSisters.woowaSisters.controller.meeting.Meeting;
import com.woowaSisters.woowaSisters.domain.comment.Comment;
import com.woowaSisters.woowaSisters.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class CommentSaveDTO {
    private UUID postId;
    private UUID userId;
    private UUID relationCm;
    private String description;

    @Builder
    public CommentSaveDTO(UUID postId, UUID userId, UUID relationCm, String description) {
        this.postId = postId;
        this.userId = userId;
        this.relationCm = relationCm;
        this.description = description;
    }

    //dto -> entity
    public Comment toEntity() {
        return Comment.builder()
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
