package com.woowaSisters.woowaSisters;
import com.woowaSisters.woowaSisters.domain.user.User;
import com.woowaSisters.woowaSisters.domain.user.UserRepository;
import com.woowaSisters.woowaSisters.security.PrincipalDetails;
import com.woowaSisters.woowaSisters.service.PrincipalDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PrincipalDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PrincipalDetailsService principalDetailsService;

    private User user;
    @BeforeEach
    void setUp() {
        user = new User();
        user.setUserId("testUser");
        // Set other required fields
    }

    @Test
    void loadUserByUsername_UserExists_ReturnsUserDetails() {
        when(userRepository.findByUserId("testUser")).thenReturn(Optional.of(user));

        UserDetails userDetails = principalDetailsService.loadUserByUsername("testUser");

        assertThat(userDetails.getUsername()).isEqualTo("testUser");
    }

    @Test
    void loadUserByUsername_UserDoesNotExist_ThrowsUsernameNotFoundException() {
        when(userRepository.findByUserId("unknownUser")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> principalDetailsService.loadUserByUsername("unknownUser"))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining("해당 유저를 찾을 수 없습니다");
    }
}