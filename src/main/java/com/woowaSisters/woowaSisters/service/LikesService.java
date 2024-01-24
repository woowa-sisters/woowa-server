package com.woowaSisters.woowaSisters.service;

import org.springframework.context.annotation.Lazy;

import java.util.UUID;

@Lazy
public interface LikesService {
    void addLikes(UUID communityUuid, UUID userUuid);
}

