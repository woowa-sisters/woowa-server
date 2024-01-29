package com.woowaSisters.woowaSisters.vo;

import com.woowaSisters.woowaSisters.domain.comment.Comment;
import com.woowaSisters.woowaSisters.domain.park.Parks;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@Setter
@Getter
public class CommentVO {
    private UUID id;
    private LocalDateTime datetime;
    private UUID postId;
    private UUID userId;
    private UUID relationCm;
    private String description;

    public CommentVO(Comment comment){
        this.id = comment.getId();
        this.datetime = comment.getDatetime();
        this.postId = comment.getPostId();
        this.userId = comment.getUserId();
        this.relationCm = comment.getRelationCm();
        this.description = comment.getDescription();
    }
}
