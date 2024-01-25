
package com.woowaSisters.woowaSisters.domain.bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark,UUID>{
    //해당하는 유저 id만 북마크 조회
    @Query("SELECT b FROM Bookmark b JOIN FETCH b.user WHERE b.user.userUuid = :userUuid ORDER BY b.parks.parkName DESC")
    List<Bookmark> findAllBookmark(@Param("userUuid") UUID Useruuid);
}

