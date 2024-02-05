package com.woowaSisters.woowaSisters.controller.token;

import com.woowaSisters.woowaSisters.domain.token.JwtToken;
import com.woowaSisters.woowaSisters.service.token.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<JwtToken> saveToken(@RequestBody String tokenValue) {
        JwtToken jwtToken = jwtTokenService.saveToken(tokenValue);
        return ResponseEntity.ok(jwtToken);
    }
}
