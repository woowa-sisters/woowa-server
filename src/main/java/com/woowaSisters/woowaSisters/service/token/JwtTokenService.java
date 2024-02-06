package com.woowaSisters.woowaSisters.service.token;


import org.springframework.web.client.RestTemplate;

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

    public Map<String, Object> getUserInfo(String accessToken) {
        String userInfoEndpoint = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + accessToken;
        return restTemplate.getForObject(userInfoEndpoint, Map.class);
    }

    public void markTokenAsDeleted(UUID tokenUuid) {
        jwtTokenRepository.findById(tokenUuid).ifPresent(jwtToken -> {
            jwtToken.setDeletedAt(LocalDateTime.now());
            jwtTokenRepository.save(jwtToken);
        });
    }
}
