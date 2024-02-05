package com.woowaSisters.woowaSisters.service;

import com.woowaSisters.woowaSisters.domain.user.User;
import com.woowaSisters.woowaSisters.domain.user.UserRepository;
import com.woowaSisters.woowaSisters.domain.userInfo.UserInfo;
import com.woowaSisters.woowaSisters.dto.UserInfoDTO;
import com.woowaSisters.woowaSisters.dto.UserSignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserInfoRepository userInfoRepository) {
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
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

    public UserInfo saveUserInfo(UserInfoDTO userInfoDTO, String email) {
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(email); // 이메일 설정

        // 모든 장르 필드를 false로 초기화
        Arrays.stream(UserInfo.class.getDeclaredFields())
                .filter(field -> field.getType().equals(Boolean.class) && field.isAnnotationPresent(Column.class))
                .forEach(field -> {
                    try {
                        field.setAccessible(true);
                        field.set(userInfo, Boolean.FALSE);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });

        // 선택된 장르를 true로 설정
        userInfoDTO.getGenre().forEach((genre, isSelected) -> {
            try {
                String fieldName = genre.replace(" ", "_"); // 클라이언트에서 공백을 사용하는 경우, 필드 이름에 맞게 언더스코어로 변환
                Field field = UserInfo.class.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(userInfo, isSelected);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        return userInfoRepository.save(userInfo);
    }

}

