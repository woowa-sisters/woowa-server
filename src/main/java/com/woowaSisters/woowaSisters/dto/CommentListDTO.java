package com.woowaSisters.woowaSisters.dto;

import com.woowaSisters.woowaSisters.domain.comment.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentListDTO {
    private Long id;
    private LocalDateTime datetime;
    private Long postId;
    private Long userId;
    private Long relationCm;
    private String description;

    public CommentListDTO(Comment comment){
        id = comment.getId();
        datetime = comment.getDatetime();
        postId = comment.getPostId();
        userId = comment.getUserId();
        relationCm = comment.getRelationCm();
        description = comment.getDescription();
    }
}
