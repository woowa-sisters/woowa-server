/*
package com.woowaSisters.woowaSisters.service;

import com.woowaSisters.woowaSisters.domain.community.Community;
import com.woowaSisters.woowaSisters.domain.community.CommunityRepository;
import com.woowaSisters.woowaSisters.domain.likes.Likes;
import com.woowaSisters.woowaSisters.domain.likes.LikesRepository;
import com.woowaSisters.woowaSisters.domain.user.User;
import com.woowaSisters.woowaSisters.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
//@RequiredArgsConstructor
@Component
@Transactional
public class LikesServiceImpl implements LikesService {
    private final LikesRepository likesRepository;
    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;

    @Autowired
    public LikesServiceImpl(LikesRepository likesRepository, CommunityRepository communityRepository, UserRepository userRepository) {
        this.likesRepository = likesRepository;
        this.communityRepository = communityRepository;
        this.userRepository = userRepository;
    }

    //좋아요 한번 누르면 +1, 다시 누르면 -1
    @Override
    public void addLikes(UUID communityUuid,UUID userUuid) {
        User user = userRepository.findByUserUuid(userUuid);
        Optional<Community> optionalCommunity = communityRepository.findById(communityUuid);
        Community community = optionalCommunity.orElse(null);
        if(!likesRepository.existsByUserAndCommunity(user,community)) {
            community.setLikeCount(community.getLikeCount()+1);
            likesRepository.save(new Likes(user, community));

        }else {
            community.setLikeCount(community.getLikeCount()-1);
            likesRepository.deleteByUserAndCommunity(user,community);
        }

    }
}
*/
