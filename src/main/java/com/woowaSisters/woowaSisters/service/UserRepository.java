package com.woowaSisters.woowaSisters.service;

import com.woowaSisters.woowaSisters.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}