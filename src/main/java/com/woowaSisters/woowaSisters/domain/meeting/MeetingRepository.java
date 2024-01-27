package com.woowaSisters.woowaSisters.domain.meeting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {


    //title을 이용한 조회
    List<Meeting> findByMeetingTitle(String meetingTitle);

    //bookId를 이용한 조회
    Optional<Meeting> findByBookId(Long bookId);

    List<Meeting> findTop10ByOrderByMeetingCreatedAtDesc();

    List<Meeting> findAllByOrderByMeetingCreatedAtDesc();


    List<Meeting> findByMeetingTime(long meetingTime);


    List<Meeting> findAllByMeetingTime(long meetingTime);



}
