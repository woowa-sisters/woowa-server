package com.woowaSisters.woowaSisters.service.token;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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

    // Google의 공개키를 캐싱하기 위한 필드
    private Map<String, String> googlePublicKeys = new HashMap<>();

    public boolean verifyIdToken(String idToken) {
        // ID 토큰의 서명을 검증하기 위해 Google의 공개키 사용
        // 예제에서는 실제 검증 로직을 구현하지 않고, 항상 true를 반환
        // 실제 구현에는 JWT 라이브러리 사용하여 검증 필요
        return true;
    }

    public boolean isAccessTokenValid(String accessToken) {
        // 액세스 토큰이 유효한지 검증
        // 예제에서는 실제 HTTP 요청을 보내지 않고, 항상 true를 반환
        // 실제 구현에는 Google의 /tokeninfo 엔드포인트를 사용하여 검증 필요
        return true;
    }

    public Optional<JwtToken> findByAccessToken(String accessToken) {
        return jwtTokenRepository.findByAccessToken(accessToken);
    }

    public ResponseEntity<?> refreshAccessTokenUsingRefreshToken(String refreshToken) {
        String tokenEndpoint = "https://oauth2.googleapis.com/token";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "refresh_token");
        requestBody.add("refresh_token", refreshToken);
        requestBody.add("client_id", googleClientId);
        requestBody.add("client_secret", googleClientSecret);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(tokenEndpoint, requestEntity, Map.class);
            Map<String, Object> responseBody = response.getBody();

            if (responseBody != null && responseBody.containsKey("access_token")) {
                String newAccessToken = (String) responseBody.get("access_token");
                // 여기서 데이터베이스 업데이트 로직 구현 필요
                // 예: jwtTokenRepository.save(new JwtToken(...));
                return ResponseEntity.ok().body("Access token refreshed successfully. New access token: " + newAccessToken);
            } else {
                return ResponseEntity.badRequest().body("Failed to refresh access token.");
            }
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during token refresh: " + e.getMessage());
        }
    }

}
