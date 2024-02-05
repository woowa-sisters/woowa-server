package com.woowaSisters.woowaSisters.dto;

import com.woowaSisters.woowaSisters.domain.subBook.SubBook;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SubBookSaveDTO {
    private Long id;
    private LocalDateTime datetime;
    private Long userId;
    private Long bookId;

    @Builder
    public SubBookSaveDTO(Long userId, Long bookId){
        this.userId = userId;
        this.bookId = bookId;
    }

    public SubBook toEntity(){
        return SubBook.builder()
                .datetime(LocalDateTime.now())
                .userId(userId)
                .bookId(bookId)
                .build();
    }
}
