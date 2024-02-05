package com.woowaSisters.woowaSisters.service.token;


import com.woowaSisters.woowaSisters.domain.token.JwtToken;
import com.woowaSisters.woowaSisters.domain.token.JwtTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class JwtTokenService {

    private final JwtTokenRepository jwtTokenRepository;

    @Autowired
    public JwtTokenService(JwtTokenRepository jwtTokenRepository) {
        this.jwtTokenRepository = jwtTokenRepository;
    }

    public JwtToken saveToken(String tokenValue) {
        JwtToken jwtToken = JwtToken.builder()
                .tokenValue(tokenValue)
                .build();
        return jwtTokenRepository.save(jwtToken);
    }
}
