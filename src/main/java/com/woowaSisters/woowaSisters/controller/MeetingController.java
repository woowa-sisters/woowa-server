package com.woowaSisters.woowaSisters.controller;
import com.woowaSisters.woowaSisters.domain.meeting.Meeting;
import com.woowaSisters.woowaSisters.domain.user.User;
import com.woowaSisters.woowaSisters.domain.user.UserRepository;
import com.woowaSisters.woowaSisters.dto.meeting.*;
import com.woowaSisters.woowaSisters.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4000")
@Lazy
@RestController
@RequestMapping("/api/meetings")
public class MeetingController {

    private final MeetingService meetingService;
    private final UserRepository userRepository;

    @Autowired
    public MeetingController(MeetingService meetingService, UserRepository userRepository) {
        this.meetingService = meetingService;
        this.userRepository = userRepository;
    }

    // 모임 생성하기 - dto 이용
    @PostMapping("/")
    public void createMeeting(@RequestBody MeetingSaveDto meetingSaveDto) {
        User user = userRepository.getReferenceById(meetingSaveDto.getUserUuid());
        meetingService.createMeeting(user, meetingSaveDto);
    }

    // 모임 수정하기
    @PutMapping("/{meetingUuid}")
    public ResponseEntity<Meeting> updateMeeting(@PathVariable UUID meetingUuid,
                                                 @RequestBody MeetingResponseDto updateDto) {
        // 사용자 인증 정보를 얻는 예시 코드 - 수정해야 함
        /*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserUuid = (String) authentication.getPrincipal();

        // 현재 사용자 정보 가져오기
        User user = userRepository.getReferenceById(UUID.fromString(currentUserUuid));

         */

        Meeting updatedMeeting = meetingService.updateMeeting(meetingUuid, updateDto);
        return ResponseEntity.ok(updatedMeeting);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Meeting>> getMeetingList(@RequestParam(required = false, defaultValue = "latest") String sort) {
        MeetingListDto listDto = MeetingListDto.builder().sort(sort).build();
        List<Meeting> meetings = meetingService.getMeetings(listDto);
        return ResponseEntity.ok(meetings);
    }

    // 모임 상세보기
    @GetMapping("/{id}")
    public ResponseEntity<MeetingResponseDto> getMeetingById(@PathVariable UUID id) {
        Optional<MeetingResponseDto> meetingOptional = meetingService.getMeetingDetailById(id);
        return meetingOptional.map(meeting -> new ResponseEntity<>(meeting, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 모임 참여하기
    @PostMapping("/{meetingUuid}/member/join/{memberUuid}")
    public ResponseEntity<String> joinMeeting(@RequestBody MeetingJoinRequestDto requestDto) {
        boolean isJoined = meetingService.joinMeeting(requestDto);
        if (isJoined) {
            return ResponseEntity.ok("모임 참여 신청 완료");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("모임 참여 실패. 모임을 찾을 수 없거나 참여가 마감되었습니다.");
        }
    }

    // 모임 참여 취소
    @PostMapping("/{meeting-uuid}/member/cancel/{member-uuid}")
    public ResponseEntity<String> cancelJoinMeeting(@RequestBody MeetingJoinRequestDto requestDto) {
        boolean isCanceled = meetingService.cancelJoinMeeting(requestDto);
        if (isCanceled) {
            return ResponseEntity.ok("모임 참여 취소 완료");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("모임 참여 취소 실패. 모임을 찾을 수 없거나 사용자가 참여하지 않았습니다.");
        }
    }

    // 모임 구독
    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribeToMeeting(@RequestBody MeetingSubscribeDto subscribeDto) {
        boolean isSubscribed = meetingService.subscribeToMeeting(subscribeDto.getMeetingUuid(), subscribeDto.getUserUuid());
        if (isSubscribed) {
            return ResponseEntity.ok("모임 구독 완료");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("모임 구독 실패. 모임을 찾을 수 없거나 이미 구독 중입니다.");
        }
    }
    /// 모임 구독 취소
    @PostMapping("/unsubscribe")
    public ResponseEntity<String> unsubscribeFromMeeting(@RequestBody MeetingSubscribeDto subscribeDto) {
        boolean isUnsubscribed = meetingService.unsubscribeFromMeeting(subscribeDto.getMeetingUuid(), subscribeDto.getUserUuid());
        if (isUnsubscribed) {
            return ResponseEntity.ok("모임 구독 취소 완료");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("모임 구독 취소 실패. 모임을 찾을 수 없거나 구독 중이 아닙니다.");
        }
    }
    // 모임 구독 목록 조회
    @GetMapping("/subscribed-meetings/{userUuid}")
    public ResponseEntity<Set<Meeting>> getSubscribedMeetings(@PathVariable UUID userUuid) {
        Set<Meeting> subscribedMeetings = meetingService.getSubscribedMeetings(userUuid);
        return ResponseEntity.ok(subscribedMeetings);
    }
}
