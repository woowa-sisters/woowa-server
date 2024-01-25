package com.woowaSisters.woowaSisters.service;

import com.woowaSisters.woowaSisters.domain.community.Community;
import com.woowaSisters.woowaSisters.domain.community.CommunityRepository;
import com.woowaSisters.woowaSisters.domain.park.Parks;
import com.woowaSisters.woowaSisters.domain.user.User;
import com.woowaSisters.woowaSisters.dto.*;

import com.woowaSisters.woowaSisters.dto.CommunityResponseDto;
import com.woowaSisters.woowaSisters.dto.CommunitySaveDto;
import com.woowaSisters.woowaSisters.dto.CommunityUpdateRequestDto;
import com.woowaSisters.woowaSisters.vo.CommunityListParkVO;
import com.woowaSisters.woowaSisters.vo.CommunityListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
@Component
@Transactional

public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;

    @Autowired
    public CommunityServiceImpl(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    //게시글 작성
    @Override
    public Community saveCommunity(User user, Parks parks, CommunitySaveDto article) {
        Community community = article.toEntity(user, parks);
        return communityRepository.save(community);
    }

    //게시글 수정
    @Override
    public Community updateCommunity(UUID communityUuid, CommunityUpdateRequestDto article) {
        Community community = communityRepository.findById(communityUuid)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id =" + communityUuid));
        community.update(article.getArticle());
        return community;
    }

    //게시글 삭제
    @Override
    public Community deleteCommunity(UUID communityUuid) {
        Community community = communityRepository.findById(communityUuid).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + communityUuid));
        communityRepository.delete(community);
        return community;
    }

    //특정 게시글 1개 조회
    @Override
    public CommunityResponseDto findCommunity(UUID communityUuid) {
        Community entity = communityRepository.findById(communityUuid).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + communityUuid));
        return new CommunityResponseDto(entity);
    }

    //게시글 목록 최신순 조회
    @Override
    public List<CommunityListVO> findAllDesc() {
        List<CommunityListVO> collect = communityRepository.findAllDesc().stream()
                .map(CommunityListVO::new)
                .collect(Collectors.toList());
        return collect;
    }

    //게시글 목록 카테고리별 조회
    @Override
    public List<CommunityListParkVO> findAllPark(String parkName) {
        return communityRepository.findAllByParkNameWithFetchJoin(parkName).stream()
                .map(CommunityListParkVO::new)
                .collect(Collectors.toList());
    }


}



//    private final CommunityRepository communityRepository;
//
//    //게시글 작성
//    @Transactional
//    public UUID save(User user, Parks parks, CommunitySaveDto article) {
//        Community community = article.toEntity(user, parks);
//        communityRepository.save(community);
//        return community.getCommunityUuid();
//    }
//
//    //게시글 수정
//    @Transactional
//    public UUID update(UUID communityUuid, CommunityUpdateRequestDto article) {
//        Community community = communityRepository.findById(communityUuid)
//                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id ="+communityUuid));
//        community.update(article.getArticle());
//        return communityUuid;
//    }
//
//    //게시글 삭제
//    @Transactional
//    public UUID delete(UUID communityUuid) {
//        Community community = communityRepository.findById(communityUuid).orElseThrow(() ->
//                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + communityUuid));
//
//        communityRepository.delete(community);
//        return communityUuid;
//    }
//
//    //특정 게시글 1개 조회
//
//    @Transactional(readOnly = true)
//    public CommunityResponseDto findCommunity(UUID communityUuid){
//        Community entity = communityRepository.findById(communityUuid).orElseThrow(() ->
//                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id="+ communityUuid));
//        return new CommunityResponseDto(entity);
//    }
//
//    //게시글 목록 최신순 조회
//    @Transactional(readOnly = true)
//    public List<CommunityListDto> findAllDesc() {
//        return communityRepository.findAllDesc().stream()
//                .map(CommunityListDto::new)
//                .collect(Collectors.toList());
//    }
//
//    //게시글 목록 카테고리별 조회
//    @Transactional(readOnly = true)
//    public List<CommunityListParkDto> findAllPark(String parkName) {
//        return communityRepository.findAllByParkNameWithFetchJoin(parkName).stream()
//                .map(CommunityListParkDto::new)
//                .collect(Collectors.toList());
//    }


