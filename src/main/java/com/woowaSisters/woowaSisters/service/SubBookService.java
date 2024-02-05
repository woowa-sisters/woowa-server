package com.woowaSisters.woowaSisters.service;

import com.woowaSisters.woowaSisters.domain.subBook.SubBook;
import com.woowaSisters.woowaSisters.dto.SubBookListDTO;
import com.woowaSisters.woowaSisters.dto.SubBookSaveDTO;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.UUID;

@Lazy
public interface SubBookService {

    List<SubBookListDTO> findByUserId(Long userId);
    SubBook saveSubBook(SubBookSaveDTO subBook);
    void deleteSubBook(UUID id);
    void deleteSubBook(Long userId, Long bookId);
}
