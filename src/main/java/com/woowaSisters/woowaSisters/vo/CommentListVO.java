package com.woowaSisters.woowaSisters.vo;

import com.woowaSisters.woowaSisters.domain.comment.Comment;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CommentListVO {
    private Long id;
    private LocalDateTime datetime = LocalDateTime.now();
    private Long postId;
    private Long userId;
    private Long relationCm;
    private String description;

    public CommentListVO(Comment entity){
        this.id = entity.getId();
        this.datetime = entity.getDatetime();
        this.postId = entity.getPostId();
        this.userId = entity.getUserId();
        this.relationCm = entity.getRelationCm();
        this.description = entity.getDescription();
    }
}
