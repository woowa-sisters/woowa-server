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
    public ResponseEntity<JwtToken> saveToken(@RequestBody TokenValueDTO tokenValueDTO) {
        System.out.println("==========================================확인===============" + tokenValueDTO.getTokenValue());
        JwtToken jwtToken = jwtTokenService.saveToken(tokenValueDTO.getTokenValue());
        return ResponseEntity.ok(jwtToken);
    }

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
//    @PostMapping("/save")
//    public ResponseEntity<?> saveTokenAndUserInfo(@RequestBody TokenValueDTO tokenValueDTO) {
//        // Save the token
//        JwtToken jwtToken = jwtTokenService.saveToken(tokenValueDTO.getTokenValue());
//        if (jwtToken == null) {
//            return ResponseEntity.badRequest().body("Failed to save token");
//        }
//
//        // Assume the tokenValueDTO contains some user info as a string for testing
//        String fakeUserInfo = tokenValueDTO.getTokenValue(); // 이 부분은 테스트 목적으로 추가된 부분입니다.
//
//        // Create a fake user map from the token value string
//        Map<String, Object> userInfo = Map.of(
//                "name", fakeUserInfo,
//                "email", fakeUserInfo + "@example.com",
//                "id", UUID.randomUUID().toString()
//        );
//
//        // Save the fake user info
//        try {
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

}
