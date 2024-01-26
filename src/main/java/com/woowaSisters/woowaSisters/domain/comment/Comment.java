
package com.woowaSisters.woowaSisters.domain.comment;
import lombok.*;
//import jakarta.persistence.*;

import java.time.LocalDateTime;
import javax.persistence.*;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="datetime")
    private LocalDateTime datetime = LocalDateTime.now();

    @Column(name="post_id")
    private Long postId;

    @Column(name="user_id")
    private Long userId;

    @Column(name="relation_cm")
    private Long relationCm;

    @Builder
    public Comment(LocalDateTime datetime, Long postId, Long userId, Long relationCm) {
        this.datetime = datetime;
        this.postId = postId;
        this.userId = userId;
        this.relationCm = relationCm;
    }
}
