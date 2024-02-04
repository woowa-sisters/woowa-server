package com.woowaSisters.woowaSisters.security;

import com.woowaSisters.woowaSisters.domain.user.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/*
* UserDetails
* 사용자 정보 담는 인터페이스 */
@Data
public class PrincipalDetails implements UserDetails, OAuth2User {
    private final Map<String, Object> attributes;
    //    private static final Logger logger = LoggerFactory.getLogger(PrincipalDetails.class);
    private User user;

    // 전통적인 로그인을 위한 생성자
    public PrincipalDetails(User user) {
        this.user = user;
        this.attributes = null; // OAuth2 속성 없음
    }

    public PrincipalDetails(User user,  Map<String, Object> attributes) {
        this.user = user;
//        logger.info("PrincipalDetails created with User: {}", user);
        this.attributes = attributes;
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

    // 구글 로그인

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
