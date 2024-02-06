package com.woowaSisters.woowaSisters.filter;

import com.woowaSisters.woowaSisters.domain.token.JwtToken;
import com.woowaSisters.woowaSisters.service.token.JwtTokenService;
import io.jsonwebtoken.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, java.io.IOException {
        String idToken = request.getHeader("ID-Token");
        String accessToken = request.getHeader("Authorization");

        // ID 토큰 유효성 검증
        if (idToken != null && !jwtTokenService.verifyIdToken(idToken)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid ID Token");
            return;
        }

        // 액세스 토큰 갱신 및 유효성 검증
        if (accessToken != null && !jwtTokenService.isAccessTokenValid(accessToken)) {
            Optional<JwtToken> optionalJwtToken = jwtTokenService.findByAccessToken(accessToken);
            if (optionalJwtToken.isPresent() && optionalJwtToken.get().getRefreshToken() != null) {
                ResponseEntity<?> refreshResponse = jwtTokenService.refreshAccessTokenUsingRefreshToken(optionalJwtToken.get().getRefreshToken());
                if (!refreshResponse.getStatusCode().is2xxSuccessful()) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Failed to refresh Access Token");
                    return;
                }
                // 갱신된 토큰으로 요청 헤더를 업데이트 (선택적)
                // accessToken = 갱신된 토큰;
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Token Expired");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
