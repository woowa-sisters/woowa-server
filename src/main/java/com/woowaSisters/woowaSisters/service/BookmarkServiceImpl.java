/*
package com.woowaSisters.woowaSisters.service;

import com.woowaSisters.woowaSisters.domain.bookmark.Bookmark;
import com.woowaSisters.woowaSisters.domain.bookmark.BookmarkRepository;
import com.woowaSisters.woowaSisters.domain.park.ParkRepository;
import com.woowaSisters.woowaSisters.domain.user.UserRepository;
import com.woowaSisters.woowaSisters.dto.BookmarkResponseDto;
import com.woowaSisters.woowaSisters.dto.BookmarkSaveDto;
import com.woowaSisters.woowaSisters.domain.user.User;
import com.woowaSisters.woowaSisters.domain.park.Parks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Component
@Transactional
public class BookmarkServiceImpl implements BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final ParkRepository parkRepository;

    @Autowired
    public BookmarkServiceImpl(
            BookmarkRepository bookmarkRepository, UserRepository userRepository,ParkRepository parkRepository )
    {
        this.bookmarkRepository = bookmarkRepository;
        this.userRepository = userRepository;
        this.parkRepository = parkRepository;
    }

    //북마크 추가
    @Override
    public Bookmark saveBookmark(UUID userUuid, UUID parkUuid) {
        User user = userRepository.findByUserUuid(userUuid);
        Parks parks = parkRepository.findByParkUuid(parkUuid);

        BookmarkSaveDto bookmarkSaveDto = BookmarkSaveDto.builder()
                .userUuid(userUuid)
                .parkUuid(parkUuid)
                .build();

        Bookmark bookmark = bookmarkSaveDto.toEntity(user, parks);
        return bookmarkRepository.save(bookmark);
    }

    //북마크 리스트 형식으로 조회
    @Override
    public List<BookmarkResponseDto> findAllBookmark(UUID userUuid) {
        return bookmarkRepository.findAllBookmark(userUuid).stream()
                .map(BookmarkResponseDto::new)
                .collect(Collectors.toList());
    }

    //북마크 삭제
    @Override
    public Bookmark deleteBookmark(UUID bookmarkUuid) {
        Bookmark bookmark = bookmarkRepository.findById(bookmarkUuid).orElseThrow(() ->
                new IllegalArgumentException("해당 북마크가 존재하지 않습니다. id=" + bookmarkUuid));
        bookmarkRepository.delete(bookmark);
        return bookmark;
    }



    //    // 북마크 추가
//    @Transactional
//    public void saveBookmark(UUID userUuid, UUID parkUuid) {
//        User user = userRepository.getReferenceById(userUuid);
//        Parks parks = parkRepository.getReferenceById(parkUuid);
//
//        BookmarkSaveDto bookmarkSaveDto = BookmarkSaveDto.builder()
//                .userUuid(userUuid)
//                .parkUuid(parkUuid)
//                .build();
//
//        bookmark bookmark = bookmarkSaveDto.toEntity(user, parks);
//        bookmarkRepository.save(bookmark);
//    }
//
//    //북마크 리스트 형식으로 조회
//    @Transactional(readOnly = true)
//    public List<BookmarkResponseDto> findAllBookmark(UUID userUuid) {
//        return bookmarkRepository.findAllBookmark(userUuid).stream()
//                .map(BookmarkResponseDto::new)
//                .collect(Collectors.toList());
//    }
//    //북마크 삭제
//    @Transactional
//    public void deleteBookmark(UUID bookmarkUuid) {
//        bookmark bookmark = bookmarkRepository.findById(bookmarkUuid).orElseThrow(() ->
//                new IllegalArgumentException("해당 북마크가 존재하지 않습니다. id=" + bookmarkUuid));
//        bookmarkRepository.delete(bookmark);
//    }

}
*/
