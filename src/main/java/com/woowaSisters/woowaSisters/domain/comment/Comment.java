
package com.woowaSisters.woowaSisters.domain.comment;
import lombok.*;
//import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="datetime")
    private LocalDateTime datetime;

    @Column(name="post_id")
    private Long postId;

    @Column(name="user_id")
    private Long userId;

    @Column(name="relation_cm")
    private Long relationCm;

    @Column(name="description")
    private String description;

   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "relationCm", insertable = false, updatable = false)
    private Comment parent;

    @OneToMany(mappedBy="parent", fetch = FetchType.LAZY)
    private List<Comment> children = new ArrayList<>();
    /*@JoinColumn(name = "{ColumnName}", referencedColumnName = "{ReferencedColumnName}")
    @Where(clause = "{Requirement}")
    private {Class} {variable};*/


    /*@Builder
    public Comment(LocalDateTime datetime, Long postId, Long userId, UUID relationCm, String description) {
        this.datetime = datetime;
        this.postId = postId;
        this.userId = userId;
        this.relationCm = relationCm;
        this.description = description;
    }*/

    /*@ManyToOne
    @JoinColumn(name = "user_uuid")
    private User user;

    @OneToOne
    @JoinColumn(name = "meeting_uuid")
    private Meeting meeting;*/
}
