package com.woowaSisters.woowaSisters.dto.request;

import com.woowaSisters.woowaSisters.domain.user.UserRole;
//import jakarta.validation.constraints.NotBlank;
import com.woowaSisters.woowaSisters.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class JoinRequest {

    @NotBlank(message = "로그인 아이디를 입력하세요")
    private String userId;

    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;
    private String passwordCheck;

    @NotBlank(message = "이메일을 입력하세요")
    private String email;

    @NotBlank(message = "닉네임을 입력하세요")
    private String nickname;

    public User toEntity(String encodedPassword) {
        return User.builder()
                .userId(this.userId)
                .password(encodedPassword)
                .nickname(this.nickname)
                .email(this.email)
                .role(UserRole.USER)
                .build();
    }
}
