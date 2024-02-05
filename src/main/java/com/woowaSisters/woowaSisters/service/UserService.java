package com.woowaSisters.woowaSisters.service;

import com.woowaSisters.woowaSisters.domain.user.User;
import com.woowaSisters.woowaSisters.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User saveGoogleUserInfo(Map<String, Object> userInfo) {
        if (!userRepository.existsByEmail((String) userInfo.get("email"))) {
            User user = User.builder()
                    .email((String) userInfo.get("email"))
                    .nickname((String) userInfo.get("name")) // Google의 'name'을 닉네임으로 사용
                    .provider("google")
                    .providerId((String) userInfo.get("id"))
                    .username((String) userInfo.get("name"))
                    .build();

            return userRepository.save(user);
        } else {
            return userRepository.findByEmail((String) userInfo.get("email")).orElse(null);
        }
    }
}

