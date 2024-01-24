package com.woowaSisters.woowaSisters.service.test;

import com.woowaSisters.woowaSisters.domain.test.Test;
import com.woowaSisters.woowaSisters.domain.test.TestRepository;
import com.woowaSisters.woowaSisters.dto.test.TestDto;
import com.woowaSisters.woowaSisters.dto.test.TestRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;


//@Lazy
@Service
@Component
@Transactional
//@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final BCryptPasswordEncoder encoder;
    private final TestRepository testRepository;

    @Autowired
    public TestServiceImpl(@Qualifier("passwordEncoder") BCryptPasswordEncoder encoder,
                           TestRepository testRepository) {
        this.encoder = encoder;
        this.testRepository = testRepository;
    }


    // 회원가입
    @Override
    public Test join(TestRequestDto req) {
        Test newUser = Test.builder()
                .userId(req.getUserId())
                .password(encoder.encode(req.getPassword()))
                .build();

        return testRepository.save(newUser);
    }

    /*
     * 로그인
     * userId와 password가 일치하면 return User
     * userId 존재하지 않거나 password 일치하지 않으면 return null
     * */
    public Test login(TestDto req) {
        Test user = testRepository.findByUserId(req.getUserId())
                .orElse(null);

        if (user == null || !encoder.matches(req.getPassword(), user.getPassword())) {
            return null;
        }

        return user;
    }

    /*
     * 인증, 인가
     * userUuid 받아서 return User 없는 경우는 return null
     * */
//    @Override
//    public Test getLoginUserByUserUuid(UUID userUuid) {
//        return testRepository.findById(userUuid)
//                .orElse(null);
//    }

    /*
     * 인증, 인가
     * userId 받아서 return User 없는 경우는 return null
     * */
//    @Override
//    public Test getLoginUserByUserId(String userId) {
//        return testRepository.findByUserId(userId)
//                .orElse(null);
//    }

    /*
     * 비밀번호 재설정
     * userId와 newPassword를 받아서 비밀번호 재설정
     * */
    @Override
    public boolean resetPassword(String userId, String newPassword) {
        Test user = testRepository.findByUserId(userId)
                .orElse(null);

        if (user == null) {
            return false;
        }

        user.setPassword(encoder.encode(newPassword));
        testRepository.save(user);
        return true;
    }

//    /*
//     * 이메일을 기반으로 아이디 찾기
//     * */
//    @Override
//    public String findUserId(String email) {
//        User user = userRepository.findByEmail(email)
//                .orElse(null);
//
//        return user != null ? user.getUserId() : null;
//    }

    @Override
    public Test findByUserUuid(UUID userUuid) {
        return testRepository.findByUserUuid(userUuid);
    }

}







