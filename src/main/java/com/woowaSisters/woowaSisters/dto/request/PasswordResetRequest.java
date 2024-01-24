package com.woowaSisters.woowaSisters.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PasswordResetRequest {

    private String userId;
    private String newPassword;
}
