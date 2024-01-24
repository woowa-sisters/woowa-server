package com.woowaSisters.woowaSisters.dto.test;

import com.woowaSisters.woowaSisters.domain.test.Test;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class TestRequestDto {

    @NotBlank(message = "로그인 아이디를 입력하세요")
    private String userId;

    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;
    private String passwordCheck;

    public Test toEntity(String encodedPassword) {
        return Test.builder()
                .userId(this.userId)
                .password(encodedPassword)
                .build();
    }
}
