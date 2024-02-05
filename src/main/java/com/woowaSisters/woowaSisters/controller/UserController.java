package com.woowaSisters.woowaSisters.controller;

import com.woowaSisters.woowaSisters.domain.user.User;
import com.woowaSisters.woowaSisters.domain.userInfo.UserInfo;
import com.woowaSisters.woowaSisters.dto.UserInfoDTO;
import com.woowaSisters.woowaSisters.dto.UserSignupDTO;
import com.woowaSisters.woowaSisters.service.UserService;
import com.woowaSisters.woowaSisters.service.token.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.woowaSisters.woowaSisters.service.UserService;
import com.woowaSisters.woowaSisters.service.token.JwtTokenService;


import java.util.Map;

@RestController
@RequestMapping("/v1")
public class UserController {
    private final UserService userService;
    private final JwtTokenService jwtTokenService ;


    @Autowired
    public UserController(UserService userService, JwtTokenService jwtTokenService) {
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserInfoDTO userInfoDTO) {
        // 토큰으로부터 사용자 정보를 받아옴
        Map<String, Object> userInfo = jwtTokenService.getUserInfo(userInfoDTO.getToken());
        if (userInfo == null || userInfo.isEmpty()) {
            return ResponseEntity.badRequest().body("Failed to fetch user info from Google");
        }

        String email = (String) userInfo.get("email");

        // 사용자 정보 및 장르 선택 정보를 DB에 저장
        try {
            UserInfo newUser = userService.saveUserInfo(userInfoDTO, email);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred: " + e.getMessage());
        }
    }
}
