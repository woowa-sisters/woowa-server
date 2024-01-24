package com.woowaSisters.woowaSisters.domain.park;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

// @Repository : 해당 클래스를 레포지토리로 사용하겠다. @Component 포함
@Repository
public interface ParkRepository extends JpaRepository<Parks, UUID> {

    Parks findByParkUuid(UUID parkUuid);



}
