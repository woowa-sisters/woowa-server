package com.woowaSisters.woowaSisters.domain.token;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jwt_token")
public class JwtToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "token_uuid", columnDefinition = "BINARY(16)")
    private UUID tokenUuid;

//    @Column(name = "token_value", nullable = false, unique = true, columnDefinition = "TEXT")
//    private String tokenValue;

    @Column(name = "access_token", columnDefinition = "TEXT")
    private String accessToken;

    @Column(name = "refresh_token", columnDefinition = "TEXT", nullable = true)
    private String refreshToken;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;


//    @JsonProperty("tokenValue")
//    public String getTokenValue() {return tokenValue;}

    @JsonProperty("accessToken")
    public String getAccessToken() {return accessToken;}

    @JsonProperty("refreshToken")
    public String getRefreshToken() {return refreshToken;}



}
