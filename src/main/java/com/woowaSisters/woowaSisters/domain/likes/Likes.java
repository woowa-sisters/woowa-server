/*
package com.woowaSisters.woowaSisters.domain.likes;

import com.woowaSisters.woowaSisters.domain.community.Community;
import com.woowaSisters.woowaSisters.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "likes") //이거 이름 안바꿔 주면 충돌남 SQL 예약어라서..
@Setter
@Getter
@NoArgsConstructor
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "likes_uuid",columnDefinition = "BINARY(16)")
    private UUID likesUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uuid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "community_uuid")
    private Community community;

//    public Likes(User user, Community community) {
//        this.user = user;
//        this.community = community;
//
//    }

    @Builder
    public Likes(User user, Community community) {
        this.user = user;
        this.community = community;

    }

}
*/
