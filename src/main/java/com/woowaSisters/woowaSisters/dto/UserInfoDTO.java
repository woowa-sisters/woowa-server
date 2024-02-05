package com.woowaSisters.woowaSisters.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class UserInfoDTO {
    private String token;
    private String nickname;
    private Map<String, Boolean> genre;

    // getters and setters
}
