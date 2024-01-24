package com.woowaSisters.woowaSisters.domain.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TestRepository extends JpaRepository<Test, UUID> {

    boolean existsByUserId(String userId);



    Optional<Test> findByUserId(String userId);

    Test findByUserUuid(UUID userUuid);


}
