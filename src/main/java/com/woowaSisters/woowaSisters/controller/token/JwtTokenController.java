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
            User existingUser = userService.findByEmail((String) userInfo.get("email"));
            if (existingUser.getDeletedAt() != null) {
                // 사용자가 로그아웃 상태
                return ResponseEntity.badRequest().body("User already logout");
            }
            return ResponseEntity.ok().body(true); // 기존 사용자 정보 확인
        } else {
            // 새로운 사용자 정보를 저장
            User savedUser = userService.saveGoogleUserInfo(userInfo);
            if (savedUser == null || savedUser.getDeletedAt() != null) {
                return ResponseEntity.internalServerError().body("Failed to save user info or user is deleted");
            }
            return ResponseEntity.ok().body(false); // 새로운 사용자 정보 저장 성공
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        // Authorization 헤더에서 토큰 추출
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Bearer 토큰이 누락되었습니다.");
        }
        String accessToken = authorizationHeader.substring(7); // "Bearer " 접두사 제거

        try {
            // 액세스 토큰을 사용하여 로그아웃 시도
            jwtTokenService.logoutUsingAccessToken(accessToken);
            return ResponseEntity.ok().body("성공적으로 로그아웃되었습니다.");
        } catch (Exception e) {
            // 토큰을 찾지 못하는 등의 에러 처리
            return ResponseEntity.internalServerError().body("로그아웃 중 오류가 발생했습니다: " + e.getMessage());
        }
    }



}

