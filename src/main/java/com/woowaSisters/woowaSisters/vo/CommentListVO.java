package com.woowaSisters.woowaSisters.vo;

import com.woowaSisters.woowaSisters.domain.comment.Comment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentListVO {
    private Long id;
    private LocalDateTime datetime = LocalDateTime.now();
    private Long postId;
    private Long userId;
    private Long relationCm;

    public CommentListVO(Comment entity){
        this.id = id;
        this.datetime = datetime;
        this.postId = postId;
        this.userId = userId;
        this.relationCm = relationCm;
    }
}
