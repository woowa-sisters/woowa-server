package com.woowaSisters.woowaSisters.domain.meeting;

import com.woowaSisters.woowaSisters.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, UUID> {


    //title을 이용한 조회
    List<Meeting> findByMeetingTitle(String meetingTitle);

    //bookId를 이용한 조회
    Optional<Meeting> findByBookId(Long bookId);

    Optional<Meeting> findById(UUID meetingUuid);

/*    List<Meeting> findTop10ByOrderByMeetingCreatedAtDesc();

    List<Meeting> findAllByOrderByMeetingCreatedAtDesc();


    List<Meeting> findByMeetingTime(long meetingTime);


    List<Meeting> findAllByMeetingTime(long meetingTime);*/

    List<Meeting> findTop10ByOrderByMeetingCreatedAtDesc();// 최근 10개의 최신 모임 목록
    List<Meeting> findAllByOrderByMeetingCreatedAtDesc(); // 전체 최신 모임 목록

    @Query("SELECT m FROM Meeting m WHERE m.meetingTime > CURRENT_TIMESTAMP ORDER BY m.meetingTime ASC")
    List<Meeting> findUpcomingMeetingsTop10(); // 마감일이 임박한 모임 10개

    @Query("SELECT m FROM Meeting m WHERE m.meetingTime > CURRENT_TIMESTAMP ORDER BY m.meetingTime ASC")
    List<Meeting> findAllUpcomingMeetings(); // 전체 마감 임박한 모임 목록

}
