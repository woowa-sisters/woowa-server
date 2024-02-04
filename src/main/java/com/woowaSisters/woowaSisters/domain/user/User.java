package com.woowaSisters.woowaSisters.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
//import jakarta.persistence.*;
import com.woowaSisters.woowaSisters.domain.meeting.Meeting;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // or GenerationType.UUID
    @Column(name = "user_uuid", columnDefinition = "BINARY(16)")
    private UUID userUuid;

    @Column(name = "user_id", unique = true)
    private String userId;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "nickname", unique = true)
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "login_id")
    private String loginId;

    @Column(name="username")
    private String username;

    // 구글로그인
    @Column(name = "provider")
    private String provider;
    @Column(name = "provider_id")
    private String providerId;

    @Enumerated(EnumType.STRING)
    private UserRole role;

//    @JsonProperty("userUuid")
//    public UUID getUserUuid() {
//        return userUuid;
//    }

    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("nickname")
    public String getNickname() {
        return nickname;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @ManyToMany(mappedBy = "subscribers")
    private Set<Meeting> subscribedMeetings = new HashSet<>();


}