/*
package com.woowaSisters.woowaSisters.service;

import com.woowaSisters.woowaSisters.domain.community.Community;
import com.woowaSisters.woowaSisters.domain.park.Parks;
import com.woowaSisters.woowaSisters.domain.user.User;
import com.woowaSisters.woowaSisters.dto.*;
import com.woowaSisters.woowaSisters.dto.CommunityResponseDto;
import com.woowaSisters.woowaSisters.dto.CommunitySaveDto;
import com.woowaSisters.woowaSisters.dto.CommunityUpdateRequestDto;
import com.woowaSisters.woowaSisters.vo.CommunityListParkVO;
import com.woowaSisters.woowaSisters.vo.CommunityListVO;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.UUID;

@Lazy
public interface CommunityService {

    Community saveCommunity(User user, Parks parks, CommunitySaveDto article);

    Community updateCommunity(UUID communityUuid, CommunityUpdateRequestDto article);

    Community deleteCommunity(UUID communityUuid);

    CommunityResponseDto findCommunity(UUID communityUuid);

    List<CommunityListVO> findAllDesc();

    List<CommunityListParkVO> findAllPark(String parkName);
}
*/
