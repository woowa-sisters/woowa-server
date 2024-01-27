package com.woowaSisters.woowaSisters.controller;

import com.woowaSisters.woowaSisters.service.MeetingService;
import com.woowaSisters.woowaSisters.service.SubscribeMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Lazy
@RestController
@RequestMapping("/api/subscribe-meetings")
public class SubscribeMeetingController {

    private final SubscribeMeetingService subscribeMeetingService;
    @Autowired
    public SubscribeMeetingController(SubscribeMeetingService subscribeMeetingService) {
        this.subscribeMeetingService = subscribeMeetingService;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribeToMeeting(@RequestParam UUID userUuid, @RequestParam UUID meetingUuid) {
        subscribeMeetingService.subscribeToMeeting(userUuid, meetingUuid);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully subscribed to the meeting.");
    }

    @PostMapping("/cancel-subscription")
    public ResponseEntity<String> cancelSubscription(@RequestParam UUID userUuid, @RequestParam UUID meetingUuid) {
        subscribeMeetingService.cancelSubscription(userUuid, meetingUuid);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully canceled subscription to the meeting.");
    }
}
