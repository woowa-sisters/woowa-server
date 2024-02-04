
package com.woowaSisters.woowaSisters.domain;
import com.woowaSisters.woowaSisters.domain.park.Parks;
import com.woowaSisters.woowaSisters.domain.user.User;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
//import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID bookmarkUuid;

    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private User user;

    @OneToOne
    @JoinColumn(name = "park_uuid")
    private Parks parks;

    @CreationTimestamp
    private LocalDateTime createAt = LocalDateTime.now();

    @Builder
    public Bookmark(User user, Parks parks) {
        this.user = user;
        this.parks = parks;
    }
}
