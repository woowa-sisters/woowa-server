
package com.woowaSisters.woowaSisters.domain.comment;
import com.woowaSisters.woowaSisters.controller.meeting.Meeting;
import com.woowaSisters.woowaSisters.domain.user.User;
import lombok.*;
//import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;
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
    private UUID id;

    @Column(name="datetime")
    private LocalDateTime datetime = LocalDateTime.now();

    @Column(name="post_id")
    private UUID postId;

    @Column(name="user_id")
    private UUID userId;

    @Column(name="relation_cm")
    private UUID relationCm;

    @Column(name="description")
    private String description;

    @Builder
    public Comment(LocalDateTime datetime, UUID postId, UUID userId, UUID relationCm, String description) {
        this.datetime = datetime;
        this.postId = postId;
        this.userId = userId;
        this.relationCm = relationCm;
        this.description = description;
    }

    /*@ManyToOne
    @JoinColumn(name = "user_uuid")
    private User user;

    @OneToOne
    @JoinColumn(name = "meeting_uuid")
    private Meeting meeting;*/
}
