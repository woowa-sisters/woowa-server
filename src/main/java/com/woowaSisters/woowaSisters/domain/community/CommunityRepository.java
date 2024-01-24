package com.woowaSisters.woowaSisters.domain.community;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository

public interface CommunityRepository extends JpaRepository<Community, UUID> {
    // 작성 날짜 순 정렬
    @Query("SELECT c FROM Community c ORDER BY c.createdAt DESC")
    List<Community> findAllDesc();

    // parkUuid에 해당하는 Community 목록 조회, 작성 날짜 순 정렬
    @Query("SELECT c FROM Community c JOIN FETCH c.parks WHERE c.parks.parkName = :parkName ORDER BY c.createdAt DESC")
    List<Community> findAllByParkNameWithFetchJoin(@Param("parkName") String parkName);

}
