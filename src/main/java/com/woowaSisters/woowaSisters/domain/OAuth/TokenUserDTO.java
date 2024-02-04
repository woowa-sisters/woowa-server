package com.woowaSisters.woowaSaisters.domain.OAuth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenUserDTO {
    private String email;
    private String nickname;
}