package com.woowaSisters.woowaSisters.oauth;

import com.woowaSisters.woowaSisters.oauth.model.PrincipalDetails;
import com.woowaSisters.woowaSisters.user.UserProvider;
import com.woowaSisters.woowaSisters.util.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class PrincipalDetailsService implements UserDetailsService {

    private final UserProvider userProvider;

    @Autowired
    public PrincipalDetailsService(UserProvider userProvider) {
        this.userProvider = userProvider;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            return new PrincipalDetails(userProvider.retrieveByEmail(email));
        } catch (BaseException e) {
            throw new UsernameNotFoundException("User not found with email: " + email, e);
        }
    }

}
