package com.woowaSisters.woowaSisters.security;

import com.woowaSisters.woowaSisters.domain.user.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/*
* UserDetails
* 사용자 정보 담는 인터페이스 */
@Data
public class PrincipalDetails implements UserDetails {
//    private static final Logger logger = LoggerFactory.getLogger(PrincipalDetails.class);
    private User user;

    public PrincipalDetails(User user) {
        this.user = user;
//        logger.info("PrincipalDetails created with User: {}", user);
    }

    // 권한 작업을 위한 role return
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collections = new ArrayList<>();
        collections.add(() -> {
            return user.getRole().name();
        });
//        logger.debug("User role: {}", user.getRole());
        return collections;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // get Username 메서드 (계정의 고유한 값을 넘겨줌 - PK로)
    @Override
    public String getUsername() {
        return user.getUserId();
    }

    // 계정이 만료 되었는지 (true: 만료X)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겼는지 (true: 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호가 만료되었는지 (true: 만료X)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 활성화(사용가능)인지 (true: 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
