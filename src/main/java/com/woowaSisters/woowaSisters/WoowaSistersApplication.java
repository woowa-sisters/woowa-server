package com.woowaSisters.woowaSisters;

import com.woowaSisters.woowaSisters.domain.bookmark.Bookmark;
import com.woowaSisters.woowaSisters.domain.bookmark.BookmarkRepository;
import com.woowaSisters.woowaSisters.domain.comment.Comment;
import com.woowaSisters.woowaSisters.domain.comment.CommentRepository;
import com.woowaSisters.woowaSisters.domain.community.Community;
import com.woowaSisters.woowaSisters.domain.community.CommunityRepository;
import com.woowaSisters.woowaSisters.domain.meeting.Meeting;
import com.woowaSisters.woowaSisters.domain.meeting.MeetingRepository;
import com.woowaSisters.woowaSisters.domain.park.ParkRepository;
import com.woowaSisters.woowaSisters.domain.subBook.SubBook;
import com.woowaSisters.woowaSisters.domain.subBook.SubBookRepository;
import com.woowaSisters.woowaSisters.domain.test.Test;
import com.woowaSisters.woowaSisters.domain.test.TestRepository;
import com.woowaSisters.woowaSisters.domain.token.JwtToken;
import com.woowaSisters.woowaSisters.domain.token.JwtTokenRepository;
import com.woowaSisters.woowaSisters.domain.user.UserRepository;
import com.woowaSisters.woowaSisters.domain.park.Parks;
import com.woowaSisters.woowaSisters.domain.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackageClasses = {Parks.class, User.class, Meeting.class, Bookmark.class, Community.class, Test.class, Comment.class, Meeting.class, SubBook.class, JwtToken.class})
@EnableJpaRepositories (basePackageClasses = {UserRepository.class, ParkRepository.class, MeetingRepository.class, BookmarkRepository.class, CommunityRepository.class, TestRepository.class, CommentRepository.class, MeetingRepository.class, SubBookRepository.class, JwtTokenRepository.class})

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
