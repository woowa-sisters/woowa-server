package com.woowaSisters.woowaSisters.domain.subscribeMeeting;

import com.woowaSisters.woowaSisters.domain.meeting.Meeting;
import com.woowaSisters.woowaSisters.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubscribeMeetingRepository extends JpaRepository<SubscribeMeeting, UUID> {

    // 사용자가 특정 모임을 구독했는지 확인
    boolean existsByUserAndMeeting(User user, Meeting meeting);

    // 특정 모임의 구독 취소
    void deleteByUserAndMeeting(User user, Meeting meeting);

    // 사용자가 구독한 모든 모임 조회
    @Query("SELECT sm FROM SubscribeMeeting sm JOIN FETCH sm.meeting WHERE sm.user.userUuid = :userUuid")
    List<SubscribeMeeting> findByUserUuid(@Param("userUuid") UUID userUuid);

}