package com.woowaSisters.woowaSisters.domain.token;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JwtTokenRepository extends JpaRepository<JwtToken, UUID> {

    Optional<JwtToken> findByAccessToken(String accessToken);

}

