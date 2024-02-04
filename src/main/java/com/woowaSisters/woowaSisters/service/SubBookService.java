package com.woowaSisters.woowaSisters.service;

import com.woowaSisters.woowaSisters.domain.subBook.SubBook;
import com.woowaSisters.woowaSisters.dto.SubBookListDTO;
import com.woowaSisters.woowaSisters.dto.SubBookSaveDTO;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Lazy
public interface SubBookService {

    List<SubBookListDTO> findByUserId(Long userId);

    SubBook saveSubBook(SubBookSaveDTO subBook);
}
