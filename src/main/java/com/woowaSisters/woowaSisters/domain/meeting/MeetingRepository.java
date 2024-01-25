package com.woowaSisters.woowaSisters.domain.meeting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
/*

    //title을 이용한 조회
    List<Meeting> findByTitle(String meetingTitle);

    //bookId를 이용한 조회
    Optional<Meeting> findByBookId(Long bookId);
*/

    List<Meeting> findTop10ByOrderByMeetingCreatedAtDesc();

    @Query("SELECT m FROM Meeting m WHERE m.meetingTime < :currentTime")
    List<Meeting> findClosingSoonMeetings(@Param("currentTime") long currentTime);


    List<Meeting> findAllByOrderByMeetingCreatedAtDesc();

    /*List<Meeting> findAllClosingSoonMeetings(long meetingTime);*/
}
