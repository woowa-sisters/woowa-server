package com.woowaSisters.woowaSisters.controller;


import com.woowaSisters.woowaSisters.domain.test.Test;
import com.woowaSisters.woowaSisters.dto.request.PasswordResetRequest;
import com.woowaSisters.woowaSisters.dto.test.TestDto;
import com.woowaSisters.woowaSisters.dto.test.TestRequestDto;
import com.woowaSisters.woowaSisters.service.test.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@CrossOrigin(origins = "http://localhost:4000")
@Lazy
@RestController
//@RequiredArgsConstructor
@RequestMapping("/v1")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    @Resource
    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }
    // 회원가입
    @PostMapping("/user")
    public ResponseEntity<Test> join(@Valid @RequestBody TestRequestDto joinRequest, BindingResult bindingResult) {
        try {

//            // id 중복체크
//            if (testService.checkUserIdDuplicate(joinRequest.getUserId())) {
//                bindingResult.addError(new FieldError("joinRequest", "userId", "중복된 아이디 입니다"));
//            }


//            // password와 passwordCheck가 같은지 확인
//            if (!joinRequest.getPassword().equals(joinRequest.getPasswordCheck())) {
//                bindingResult.addError(new FieldError("joinRequest", "passwordCheck", "비밀번호가 일치하지 않습니다"));
//            }
//
//            // 유효성 검사 실패시 에러발생
//            if (bindingResult.hasErrors()) {
//
//                return ResponseEntity.badRequest().build();
//            }

            Test newUser = testService.join(joinRequest);

            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            System.out.println("Register Error-------"+ e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    // 비밀번호 재설정
    @PutMapping("/resetPw")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetRequest passwordResetRequest) {
        boolean isReset = testService.resetPassword(passwordResetRequest.getUserId(), passwordResetRequest.getNewPassword());
        if (isReset) {
            return ResponseEntity.ok("비밀번호가 재설정되었습니다.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody TestDto loginRequest) {

        String userId = loginRequest.getUserId();
        String password = loginRequest.getPassword();

        if (userId == null || password == null || userId.isEmpty() || password.isEmpty()) {
            return ResponseEntity.badRequest().body("아이디와 비밀번호를 입력하세요.");
        }

        Test user = testService.login(loginRequest);

        if (user != null) {
            return ResponseEntity.ok("로그인에 성공하였습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인에 실패하였습니다. 아이디 또는 비밀번호를 확인하세요.");
        }
    }

    // 로그아웃
    @DeleteMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return ResponseEntity.ok("로그아웃되었습니다.");
    }

    // 회원정보 조회
//    @GetMapping("/user/{userUuid}")
//    public ResponseEntity<User> findByUserUuid(@Valid @PathVariable("userUuid") String userUuidString){
//        try {
//            UUID userUuid = UUID.fromString(userUuidString);
//            Test userProfile = testService.findByUserUuid(userUuid);
//            return ResponseEntity.ok(userProfile);
//        } catch (IllegalArgumentException e) {
//            // 잘못된 UUID 문자열인 경우 처리
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

}
