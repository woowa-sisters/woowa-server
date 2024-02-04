package com.woowaSisters.woowaSisters.service;

import com.woowaSisters.woowaSisters.domain.OAuth.GoogleOAuth;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.woowaSisters.woowaSisters.domain.OAuth.GoogleOAuthTokenDto;
import com.woowaSisters.woowaSisters.domain.OAuth.GoogleUserInfoDto;
import com.woowaSisters.woowaSisters.domain.user.User;
import com.woowaSisters.woowaSisters.domain.user.UserRepository;
import com.woowaSisters.woowaSisters.wrapper.SingleResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
@Component
@Transactional
public class OAuthService {

    private final UserRepository userRepository;

    public OAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/google/login")
    public SingleResult<GoogleUserInfoDto> callback(
            @RequestParam(name="code") String code) throws IOException {
        return this.googlelogin(code);
    }

    GoogleOAuth googleOAuth;
    private GoogleUserInfoDto getGoogleUserInfoDto(String code) throws JsonProcessingException {



        ResponseEntity<String> accessTokenResponse = googleOAuth.requestAccessToken(code);
        GoogleOAuthTokenDto oAuthToken = googleOAuth.getAccessToken(accessTokenResponse);
        ResponseEntity<String> userInfoResponse = googleOAuth.requestUserInfo(oAuthToken);
        GoogleUserInfoDto googleUser = googleOAuth.getUserInfo(userInfoResponse);
        return googleUser;
    }

    public SingleResult<GoogleUserInfoDto> googlelogin(String code) throws IOException {
        GoogleUserInfoDto googleUser = getGoogleUserInfoDto(code);
        if (!userRepository.existsByEmail(googleUser.getEmail())) {
            userRepository.save(
                    User.builder()
                            .email(googleUser.getEmail())
                            .nickname(googleUser.getName())
                            .password("google")
//                            .serialNum(googleUser.getName() + LocalDateTime.now())
                            .build()
            );
            return getSingleResult(userRepository.findByEmail(
                    googleUser.getEmail()
            ).orElseThrow());

        }
        return getSingleResult(userRepository.findByEmail(
                googleUser.getEmail()
        ).orElseThrow());
    }

    private SingleResult<GoogleUserInfoDto> getSingleResult(User user) {
        GoogleUserInfoDto googleUserInfoDto = new GoogleUserInfoDto(
                user.getUserId().toString(), // GoogleUserInfoDto와 User 필드 매칭 가정
                user.getEmail(),
                true, // verified_email, 실제 사용 상황에 맞게 조정 필요
                user.getNickname(),
                null, // given_name, 필요에 따라 채우기
                null, // picture, 필요에 따라 채우기
                null  // locale, 필요에 따라 채우기
        );
        return new SingleResult<>(googleUserInfoDto);
    }

}
