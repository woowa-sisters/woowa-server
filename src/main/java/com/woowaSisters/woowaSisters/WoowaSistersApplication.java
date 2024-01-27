package com.woowaSisters.woowaSisters;

import com.woowaSisters.woowaSisters.domain.bookmark.Bookmark;
import com.woowaSisters.woowaSisters.domain.bookmark.BookmarkRepository;
import com.woowaSisters.woowaSisters.domain.community.Community;
import com.woowaSisters.woowaSisters.domain.community.CommunityRepository;
import com.woowaSisters.woowaSisters.domain.meeting.Meeting;
import com.woowaSisters.woowaSisters.domain.meeting.MeetingRepository;
import com.woowaSisters.woowaSisters.domain.park.ParkRepository;
import com.woowaSisters.woowaSisters.domain.subscribeMeeting.SubscribeMeeting;
import com.woowaSisters.woowaSisters.domain.subscribeMeeting.SubscribeMeetingRepository;
import com.woowaSisters.woowaSisters.domain.test.Test;
import com.woowaSisters.woowaSisters.domain.test.TestRepository;
import com.woowaSisters.woowaSisters.domain.user.UserRepository;
import com.woowaSisters.woowaSisters.domain.park.Parks;
import com.woowaSisters.woowaSisters.domain.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackageClasses = {Parks.class, User.class, Meeting.class, Bookmark.class, Community.class, Test.class, SubscribeMeeting.class})
@EnableJpaRepositories (basePackageClasses = {UserRepository.class, ParkRepository.class, MeetingRepository.class, BookmarkRepository.class, CommunityRepository.class, TestRepository.class, SubscribeMeetingRepository.class})

public class WoowaSistersApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(WoowaSistersApplication.class, args);
		} catch (Exception e) {
			System.out.println("Error" + e);
		}
	}

//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

}
