package com.woowaSisters.woowaSisters.filter;

import com.woowaSisters.woowaSisters.domain.token.JwtToken;
import com.woowaSisters.woowaSisters.service.token.JwtTokenService;
import com.woowaSisters.woowaSisters.util.BaseResponse;
import com.woowaSisters.woowaSisters.util.BaseResponseStatus;
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
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(new BaseResponse<>(BaseResponseStatus.INVALID_JWT).toString());
            return;
        }

        if (accessToken != null) {
            if (!jwtTokenService.isAccessTokenValid(accessToken)) {
                Optional<JwtToken> optionalJwtToken = jwtTokenService.findByAccessToken(accessToken);
                if (optionalJwtToken.isPresent()) {
                    JwtToken jwtToken = optionalJwtToken.get();
                    if (jwtToken.getRefreshToken() != null) {
                        ResponseEntity<?> refreshResponse = jwtTokenService.refreshAccessTokenUsingRefreshToken(jwtToken.getRefreshToken());
                        if (refreshResponse.getStatusCode().is2xxSuccessful()) {
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.getWriter().write(new BaseResponse(BaseResponseStatus.REFRESH_TOKEN_SUCCESS, "New access token: " ).toString());
                        } else {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.getWriter().write(new BaseResponse(BaseResponseStatus.REFRESH_TOKEN_FAILED, "Failed to refresh access token.").toString());
                        }
                        return;
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write(new BaseResponse<>(BaseResponseStatus.INVALID_JWT).toString());
                    return;
                }
            } else {
                // 액세스 토큰이 이미 유효한 경우, 처리를 계속합니다.
                // 여기에는 추가적인 메시지를 클라이언트에게 전달하지 않습니다.
            }
        }

        filterChain.doFilter(request, response);
    }

}
