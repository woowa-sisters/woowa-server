package com.woowaSisters.woowaSisters.controller;
import com.woowaSisters.woowaSisters.domain.meeting.Meeting;
import com.woowaSisters.woowaSisters.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Lazy
@RestController
@RequestMapping("/api/meetings")
public class MeetingController {

    private final MeetingService meetingService;

    @Autowired
    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    // 모임 생성하기
    @PostMapping("/create")
    public ResponseEntity<Meeting> createMeeting(@RequestBody Meeting meeting) {
        Meeting createdMeeting = meetingService.createMeeting(meeting);
        return new ResponseEntity<>(createdMeeting, HttpStatus.CREATED);
    }

    // 모임 수정하기
    @PutMapping("/{id}")
    public ResponseEntity<Meeting> updateMeeting(@PathVariable Long id, @RequestBody Meeting updatedMeeting) {
        Optional<Meeting> existingMeetingOptional = meetingService.getMeetingById(id);

        if (existingMeetingOptional.isPresent()) {
            Meeting existingMeeting = existingMeetingOptional.get();
            existingMeeting.setMeetingTitle(updatedMeeting.getTitle());
            existingMeeting.setBookId(updatedMeeting.getBookId());
            // 필요한 다른 필드들도 업데이트
            Meeting savedMeeting = meetingService.updateMeeting(existingMeeting);
            return new ResponseEntity<>(savedMeeting, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 모임 리스트 보기 - 최신순 미리보기
    @GetMapping("/latest")
    public ResponseEntity<List<Meeting>> getLatestMeetings() {
        List<Meeting> latestMeetings = meetingService.getLatestMeetings();
        return new ResponseEntity<>(latestMeetings, HttpStatus.OK);
    }

    // 모임 리스트 보기 - 마감임박순 미리보기
    @GetMapping("/closing-soon")
    public ResponseEntity<List<Meeting>> getClosingSoonMeetings() {
        List<Meeting> closingSoonMeetings = meetingService.getClosingSoonMeetings();
        return new ResponseEntity<>(closingSoonMeetings, HttpStatus.OK);
    }

    // 모임 리스트 보기 - 전체 최신순
    @GetMapping("/all-latest")
    public ResponseEntity<List<Meeting>> getAllLatestMeetings() {
        List<Meeting> allLatestMeetings = meetingService.getAllLatestMeetings();
        return new ResponseEntity<>(allLatestMeetings, HttpStatus.OK);
    }

    // 모임 리스트 보기 - 전체 마감임박순
    @GetMapping("/all-closing-soon")
    public ResponseEntity<List<Meeting>> getAllClosingSoonMeetings() {
        List<Meeting> allClosingSoonMeetings = meetingService.getAllClosingSoonMeetings();
        return new ResponseEntity<>(allClosingSoonMeetings, HttpStatus.OK);
    }

    // 모임 상세보기
    @GetMapping("/{id}")
    public ResponseEntity<Meeting> getMeetingById(@PathVariable Long id) {
        Optional<Meeting> meetingOptional = meetingService.getMeetingById(id);
        return meetingOptional.map(meeting -> new ResponseEntity<>(meeting, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 모임 참여하기
    @PostMapping("/join/{id}")
    public ResponseEntity<String> joinMeeting(@PathVariable Long id) {
        boolean isJoined = meetingService.joinMeeting(id);
        if (isJoined) {
            return ResponseEntity.ok("모임 참여 신청 완료");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("모임 참여 실패. 모임을 찾을 수 없거나 참여가 마감되었습니다.");
        }
    }

    // 모임 참여 취소
    @PostMapping("/leave/{id}")
    public ResponseEntity<String> leaveMeeting(@PathVariable Long id) {
        boolean isLeft = meetingService.leaveMeeting(id);
        if (isLeft) {
            return ResponseEntity.ok("모임 참여 취소 성공");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("모임 참여 취소 실패. 모임을 찾을 수 없거나 이미 참여 취소된 상태입니다.");
        }
    }
}