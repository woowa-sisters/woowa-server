package com.woowaSisters.woowaSisters.controller.token;

import com.woowaSisters.woowaSisters.domain.token.JwtToken;
import com.woowaSisters.woowaSisters.domain.token.JwtTokenRepository;
import com.woowaSisters.woowaSisters.domain.user.User;
import com.woowaSisters.woowaSisters.dto.TokenValueDTO;
import com.woowaSisters.woowaSisters.service.UserService;
import com.woowaSisters.woowaSisters.service.token.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/v1/token")
public class JwtTokenController {
    private final JwtTokenService jwtTokenService;
    private final UserService userService;

    @Autowired
    public JwtTokenController(JwtTokenService jwtTokenService, UserService userService) {
        this.jwtTokenService = jwtTokenService;
        this.userService = userService;

    }

    @PostMapping("/save")
    public ResponseEntity<?> saveTokenAndUserInfo(@RequestBody TokenValueDTO tokenValueDTO) {

        JwtToken jwtToken = jwtTokenService.saveToken(tokenValueDTO.getAccessToken(), tokenValueDTO.getRefreshToken());

        Map<String, Object> userInfo;
        try {
            userInfo = jwtTokenService.getUserInfo(tokenValueDTO.getAccessToken());
            if (userInfo == null || userInfo.isEmpty()) {
                return ResponseEntity.badRequest().body("Failed to fetch user info from Google");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while fetching user info: " + e.getMessage());
        }

        boolean userExists = userService.existsByEmail((String) userInfo.get("email"));
        if (userExists) {
            // 이미 존재하는 사용자인 경우
            return ResponseEntity.ok().body(true);
        } else {
            // 새로운 사용자 정보를 저장
            User savedUser = userService.saveGoogleUserInfo(userInfo);
            if (savedUser == null) {
                return ResponseEntity.internalServerError().body("Failed to save user info");
            }
            return ResponseEntity.ok().body(false); // 새로운 사용자 정보 저장 성공
        }
    }
package com.woowaSisters.woowaSisters.service.token;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;


import com.woowaSisters.woowaSisters.domain.token.JwtToken;
import com.woowaSisters.woowaSisters.domain.token.JwtTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


    @Service
    public class JwtTokenService {

        private final JwtTokenRepository jwtTokenRepository;
        private final RestTemplate restTemplate;

        @Value("${app.google.clientId}")
        private String googleClientId;

        @Value("${app.google.secret}")
        private String googleClientSecret;

        @Autowired
        public JwtTokenService(JwtTokenRepository jwtTokenRepository, RestTemplate restTemplate) {
            this.jwtTokenRepository = jwtTokenRepository;
            this.restTemplate = restTemplate;
        }

        public JwtToken saveToken(String accessToken, String refreshToken) {
            if (refreshToken == null) {
                System.out.println("Received null refreshToken, proceeding without it.");
            }

            JwtToken jwtToken = JwtToken.builder()
                    .accessToken(accessToken)

                    .refreshToken(refreshToken)
                    .build();
            return jwtTokenRepository.save(jwtToken);
        }

        public String refreshAccessToken(String refreshToken) {
            // 구글 OAuth2 토큰 엔드포인트
            String tokenEndpoint = "https://oauth2.googleapis.com/token";

            // 요청 본문 설정
            MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
            requestBody.add("client_id", googleClientId); // application.properties에서 설정된 값으로 대체
            requestBody.add("client_secret", googleClientSecret); // application.properties에서 설정된 값으로 대체
            requestBody.add("refresh_token", refreshToken);
            requestBody.add("grant_type", "refresh_token");

            // 요청 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            // HttpEntity 객체 생성
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

            // REST 요청 실행
            ResponseEntity<Map> responseEntity = restTemplate.exchange(tokenEndpoint, HttpMethod.POST, requestEntity, Map.class);

            // 새로 발급받은 액세스 토큰 반환
            return (String) responseEntity.getBody().get("access_token");

        }

        public Map<String, Object> getUserInfo(String accessToken) {
            String userInfoEndpoint = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + accessToken;
            return restTemplate.getForObject(userInfoEndpoint, Map.class);
        }

        public void logout(UUID tokenUuid) {
            jwtTokenRepository.findById(tokenUuid).ifPresent(jwtToken -> {
                jwtToken.setDeletedAt(LocalDateTime.now());
                jwtTokenRepository.save(jwtToken);
            });
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        // Extract the token from the Authorization header
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Bearer token is missing.");
        }
        String accessToken = authorizationHeader.substring(7); // Remove "Bearer " prefix

        try {
            // Attempt to logout using the access token
            jwtTokenService.logoutUsingAccessToken(accessToken);
            return ResponseEntity.ok().body("Logged out successfully.");
        } catch (Exception e) {
            // Handle any errors, such as token not found
            return ResponseEntity.internalServerError().body("An error occurred during logout: " + e.getMessage());
        }
    }




}

