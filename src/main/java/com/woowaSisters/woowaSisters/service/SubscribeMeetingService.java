package com.woowaSisters.woowaSisters.service;
import java.util.UUID;

public interface SubscribeMeetingService {

    void subscribeToMeeting(UUID userUuid, UUID meetingUuid);

    void cancelSubscription(UUID userUuid, UUID meetingUuid);
}
