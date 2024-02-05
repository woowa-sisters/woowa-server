package com.woowaSisters.woowaSisters.controller.token;

import com.woowaSisters.woowaSisters.domain.token.JwtToken;
import com.woowaSisters.woowaSisters.dto.TokenValueDTO;
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

    @Autowired
    public JwtTokenController(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/save")
    public ResponseEntity<JwtToken> saveToken(@RequestBody TokenValueDTO tokenValueDTO) {
        System.out.println("==========================================확인===============" + tokenValueDTO.getTokenValue());
        JwtToken jwtToken = jwtTokenService.saveToken(tokenValueDTO.getTokenValue());
        return ResponseEntity.ok(jwtToken);
    }

    @GetMapping("/userinfo")
    public ResponseEntity<?> getUserInfo(@RequestParam String accessToken) {
        Map<String, Object> userInfo = jwtTokenService.getUserInfo(accessToken);
        // 여기에서 사용자 정보를 데이터베이스에 저장하는 로직을 추가할 수 있습니다.
        return ResponseEntity.ok(userInfo);
    }
}
