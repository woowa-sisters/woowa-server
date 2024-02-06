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

    public void logoutUsingAccessToken(String accessToken) {
        JwtToken jwtToken = jwtTokenRepository.findByAccessToken(accessToken)
                .orElseThrow(() -> new IllegalArgumentException("토큰을 찾을 수 없습니다"));
        jwtToken.setDeletedAt(LocalDateTime.now());
        jwtTokenRepository.save(jwtToken);
    }


}
