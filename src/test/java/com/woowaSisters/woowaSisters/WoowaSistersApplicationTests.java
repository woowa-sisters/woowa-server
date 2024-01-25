package com.woowaSisters.woowaSisters;
/*
import com.woowaSisters.woowaSisters.domain.bookmark.Bookmark;
import com.woowaSisters.woowaSisters.domain.bookmark.BookmarkRepository;*//*
import com.woowaSisters.woowaSisters.domain.community.Community;
import com.woowaSisters.woowaSisters.domain.community.CommunityRepository;*/
import com.woowaSisters.woowaSisters.domain.meeting.Meeting;
import com.woowaSisters.woowaSisters.domain.park.ParkRepository;
import com.woowaSisters.woowaSisters.domain.park.Parks;
import com.woowaSisters.woowaSisters.domain.test.TestRepository;
import com.woowaSisters.woowaSisters.domain.user.User;
import com.woowaSisters.woowaSisters.domain.user.UserRepository;
import com.woowaSisters.woowaSisters.service.MeetingService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.LocalDateTime;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootApplication
public class WoowaSistersApplicationTests {

	@Autowired
	private MeetingService meetingService;

	@Test
	public void createMeetingTest() {
		// 테스트용 Meeting 객체 생성
		Meeting meeting = new Meeting();
		meeting.setMeetingTitle("Test Meeting");
		meeting.setMeetingTime(Instant.parse("2024-02-01T10:00:00").toEpochMilli());

		// 모임 생성 메서드 호출
		Meeting createdMeeting = meetingService.createMeeting(meeting);

		// 생성된 모임이 null이 아닌지 확인
		assertNotNull(createdMeeting);

		// 생성된 모임의 ID가 null이 아닌지 확인
		assertNotNull(createdMeeting.getId());

		// 생성된 모임의 제목이 올바르게 설정되었는지 확인
		assertEquals("Test Meeting", createdMeeting.getMeetingTitle());
	}
}
