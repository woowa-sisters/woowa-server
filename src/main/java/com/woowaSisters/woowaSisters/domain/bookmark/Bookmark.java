
package com.woowaSisters.woowaSisters.domain.bookmark;
import com.woowaSisters.woowaSisters.domain.user.User;
import com.woowaSisters.woowaSisters.domain.park.Parks;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
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
@Table(name = "bookmark")
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookmark_uuid",columnDefinition = "BINARY(16)")
    private UUID bookmarkUuid;

    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private User user;

    @OneToOne
    @JoinColumn(name = "park_uuid")
    private Parks parks;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder
    public Bookmark(User user, Parks parks) {
        this.user = user;
        this.parks = parks;
    }
}
