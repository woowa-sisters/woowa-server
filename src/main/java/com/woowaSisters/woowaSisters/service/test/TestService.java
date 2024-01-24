package com.woowaSisters.woowaSisters.service.test;

import com.woowaSisters.woowaSisters.domain.test.Test;
import com.woowaSisters.woowaSisters.dto.test.TestDto;
import com.woowaSisters.woowaSisters.dto.test.TestRequestDto;
import org.springframework.context.annotation.Lazy;

import java.util.UUID;

@Lazy
public interface TestService {

    // 회원가입
    Test join(TestRequestDto req);

    // 로그인
    Test login(TestDto req);

//    Test getLoginUserByUserUuid(UUID userUuid);

//    Test getLoginUserByUserId(String userId);

    boolean resetPassword(String userId, String newPassword);


    Test findByUserUuid(UUID userUuid);
}
