package com.woowaSisters.woowaSisters.user;

import com.woowaSisters.woowaSisters.user.dto.GetUserRes;
import com.woowaSisters.woowaSisters.user.model.User;
import com.woowaSisters.woowaSisters.util.BaseException;
import com.woowaSisters.woowaSisters.util.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController3 {

    private final UserProvider userProvider;

    @Autowired
    public UserController3(UserProvider userProvider) {
        this.userProvider = userProvider;
    }

    @GetMapping("")
    public BaseResponse<GetUserRes> getProfile(HttpServletRequest request) {
        try {
            Long user_id = Long.parseLong(request.getAttribute("user_id").toString());
            User user = userProvider.retrieveById(user_id);
            GetUserRes getUserRes = new GetUserRes(user.getNickname(), user.getIntroduce(), user.getEmail());
            return new BaseResponse<>(getUserRes);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
