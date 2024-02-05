package com.woowaSisters.woowaSisters.domain.subBook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubBookRepository extends JpaRepository<SubBook, UUID> {
    @Query("SELECT s FROM SubBook s")
    List<SubBook> findByUserId(Long userId);
    // where userId = :userId

    @Query("delete from SubBook s where s.userId = :userId and s.bookId = :bookId")
    void deleteByBookId(Long userId, Long bookId);
}
