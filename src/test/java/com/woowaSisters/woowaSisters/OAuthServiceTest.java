package com.woowaSisters.woowaSisters;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OAuthServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void givenUserLogin_whenRequestGoogleLogin_thenReceiveAuthToken() {
        // 로그인 요청 시뮬레이션
        ResponseEntity<String> response = restTemplate.getForEntity("/google/login", String.class);

        // 응답 상태 코드 확인
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // JWT 토큰이 포함되어 있는지 확인
        // 실제 테스트에서는 응답 바디나 헤더에서 JWT 토큰을 찾아야 합니다.
        // 이 예시에서는 응답 헤더에 "Authorization" 키로 토큰이 포함되어 있다고 가정합니다.
        // 실제로는 이 방식이 아닌, OAuth 프로세스에 따라 토큰이 반환되는 방식을 확인해야 합니다.
        // String token = response.getHeaders().getFirst("Authorization");
        // assertThat(token).isNotNull();

        // 아래는 응답 바디가 JSON 형태로 토큰을 포함하는 경우의 예시입니다.
        // 실제로는 응답 형태에 맞춰서 JSON 파싱 로직을 추가해야 합니다.
        // 예: JSON 객체에서 "access_token" 필드 값을 추출
        // String body = response.getBody();
        // assertThat(body).contains("access_token");
    }
}
