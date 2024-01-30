package com.woowaSisters.woowaSisters.controller;

import com.woowaSisters.woowaSisters.service.MeetingService;
import com.woowaSisters.woowaSisters.service.SubscribeMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4000")
@Lazy
@RestController
@RequestMapping("/api/subscriptions")
public class SubscribeMeetingController {

    private final SubscribeMeetingService subscribeMeetingService;
    @Autowired
    public SubscribeMeetingController(SubscribeMeetingService subscribeMeetingService) {
        this.subscribeMeetingService = subscribeMeetingService;
    }

    // 모임 구독
    @PostMapping("/")
    public ResponseEntity<String> subscribeToMeeting(@RequestParam UUID userUuid, @RequestParam UUID meetingUuid) {
        subscribeMeetingService.subscribeToMeeting(userUuid, meetingUuid);
        return ResponseEntity.status(HttpStatus.OK).body("모임 구독 성공");
    }

    // 모임 구독 취소
    @PostMapping("/{id}")
    public ResponseEntity<String> cancelSubscription(@RequestParam UUID userUuid, @RequestParam UUID meetingUuid) {
        subscribeMeetingService.cancelSubscription(userUuid, meetingUuid);
        return ResponseEntity.status(HttpStatus.OK).body("모임 구독 취소 성공");
    }
}
