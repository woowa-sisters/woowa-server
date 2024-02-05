
package com.woowaSisters.woowaSisters.domain.comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface CommentRepository extends JpaRepository<Comment,UUID>{

    // 작성 날짜 순 정렬
    @Query("SELECT c FROM Comment c ORDER BY c.datetime DESC")
    List<Comment> findAllDesc();
}

