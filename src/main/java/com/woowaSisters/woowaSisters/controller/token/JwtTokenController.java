package com.woowaSisters.woowaSisters.controller.token;

import com.woowaSisters.woowaSisters.domain.token.JwtToken;
import com.woowaSisters.woowaSisters.domain.user.User;
import com.woowaSisters.woowaSisters.dto.TokenValueDTO;
import com.woowaSisters.woowaSisters.service.UserService;
import com.woowaSisters.woowaSisters.service.token.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

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
        if (jwtToken == null) {
            return ResponseEntity.badRequest().body("Failed to save token");
        }

        Map<String, Object> userInfo; // userInfo를 여기서 선언합니다.

//        // 2. 토큰을 사용하여 구글 서버에 요청해서 사용자 정보를 받아옴
//        try {
//            userInfo = jwtTokenService.getUserInfo(tokenValueDTO.getTokenValue());
//            if (userInfo == null || userInfo.isEmpty()) {
//                return ResponseEntity.badRequest().body("Failed to fetch user info from Google");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body("An error occurred while fetching user info: " + e.getMessage());
//        }
//
//        // 3. 해당 사용자가 이미 저장된 사용자인지 확인
//        boolean userExists = userService.existsByEmail((String) userInfo.get("email"));
//        if (userExists) {
//            // 이미 존재하는 사용자인 경우 true 반환
//            return ResponseEntity.ok().body(true);
//        } else {
//            // 받아온 사용자 정보를 DB에 저장
//            User savedUser = userService.saveGoogleUserInfo(userInfo);
//            if (savedUser == null) {
//                return ResponseEntity.internalServerError().body("Failed to save user info");
//            }
//
//            // 새로 저장된 사용자 정보와 함께 false 반환
            return ResponseEntity.ok().body(false);
        }
    }




