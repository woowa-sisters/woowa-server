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

//    @PostMapping("/save")
//    public ResponseEntity<JwtToken> saveToken(@RequestBody TokenValueDTO tokenValueDTO) {
//        System.out.println("==========================================확인===============" + tokenValueDTO.getTokenValue());
//        JwtToken jwtToken = jwtTokenService.saveToken(tokenValueDTO.getTokenValue());
//        return ResponseEntity.ok(jwtToken);
//    }

//    @PostMapping("/save")
//    public ResponseEntity<?> saveTokenAndUserInfo(@RequestBody TokenValueDTO tokenValueDTO) {
//        // Save the token
//        JwtToken jwtToken = jwtTokenService.saveToken(tokenValueDTO.getTokenValue());
//        if (jwtToken == null) {
//            return ResponseEntity.badRequest().body("Failed to save token");
//        }
//
//        // Use the token to fetch user info from Google and save it
//        try {
//            Map<String, Object> userInfo = jwtTokenService.getUserInfo(tokenValueDTO.getTokenValue());
//            if (userInfo == null || userInfo.isEmpty()) {
//                return ResponseEntity.badRequest().body("Failed to fetch user info from Google");
//            }
//
//            User savedUser = userService.saveGoogleUserInfo(userInfo);
//            if (savedUser == null) {
//                return ResponseEntity.internalServerError().body("Failed to save user info");
//            }
//
//            return ResponseEntity.ok(savedUser);
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body("An error occurred: " + e.getMessage());
//        }
//    }
//        @PostMapping("/save")
//        public ResponseEntity<?> saveTokenAndUserInfo(@RequestBody TokenValueDTO tokenValueDTO) {
//            // Save the token
//            JwtToken jwtToken = jwtTokenService.saveToken(tokenValueDTO.getTokenValue());
//            if (jwtToken == null) {
//                return ResponseEntity.badRequest().body("Failed to save token");
//            }
//
//            // Assume the tokenValueDTO contains some user info as a string for testing
//            String fakeUserInfo = tokenValueDTO.getTokenValue(); // 이 부분은 테스트 목적으로 추가된 부분입니다.
//
//            // Create a fake user map from the token value string
//            Map<String, Object> userInfo = Map.of(
//                    "name", fakeUserInfo,
//                    "email", fakeUserInfo + "@example.com",
//                    "id", UUID.randomUUID().toString()
//            );
//
//            // Save the fake user info
//            try {
//                User savedUser = userService.saveGoogleUserInfo(userInfo);
//                if (savedUser == null) {
//                    return ResponseEntity.internalServerError().body("Failed to save user info");
//                }
//
//                return ResponseEntity.ok(savedUser);
//            } catch (Exception e) {
//                return ResponseEntity.internalServerError().body("An error occurred: " + e.getMessage());
//            }
//        }
        @PostMapping("/save")
        public ResponseEntity<?> saveTokenAndUserInfo(@RequestBody TokenValueDTO tokenValueDTO) {
            // 1. 클라이언트에서 소셜 로그인 후 발급된 토큰을 DB에 저장
            JwtToken jwtToken = jwtTokenService.saveToken(tokenValueDTO.getTokenValue());
            if (jwtToken == null) {
                return ResponseEntity.badRequest().body("Failed to save token");
            }

            // 2. 토큰을 사용하여 해당 사용자가 이미 저장된 사용자인지 확인
            Map<String, Object> userInfo = null;
            boolean userExists = userService.existsByEmail((String) userInfo.get("email"));
            if (userExists) {
                // 이미 존재하는 사용자인 경우 true 반환
                return ResponseEntity.ok().body(true);
            } else {
                // 3. false일 경우 토큰을 사용하여 구글 서버에 요청해서 사용자 정보를 받아옴
                try {
                    userInfo = jwtTokenService.getUserInfo(tokenValueDTO.getTokenValue());
                    if (userInfo == null || userInfo.isEmpty()) {
                        return ResponseEntity.badRequest().body("Failed to fetch user info from Google");
                    }

                    // 받아온 사용자 정보를 DB에 저장
                    User savedUser = userService.saveGoogleUserInfo(userInfo);
                    if (savedUser == null) {
                        return ResponseEntity.internalServerError().body("Failed to save user info");
                    }

                    // 새로 저장된 사용자 정보와 함께 false 반환
                    return ResponseEntity.ok().body(false);
                } catch (Exception e) {
                    return ResponseEntity.internalServerError().body("An error occurred while fetching user info: " + e.getMessage());
                }
            }
        }




}
