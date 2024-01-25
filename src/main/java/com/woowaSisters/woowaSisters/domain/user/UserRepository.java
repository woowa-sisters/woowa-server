package com.woowaSisters.woowaSisters.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByUserId(String userId);

    boolean existsByNickname(String nickname);

    Optional<User> findByUserId(String userId);

    User findByUserUuid(UUID userUuid);
    Optional<User> findByNickname(String nickname);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

}
