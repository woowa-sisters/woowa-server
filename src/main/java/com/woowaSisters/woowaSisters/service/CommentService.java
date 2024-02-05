package com.woowaSisters.woowaSisters.service;

import com.woowaSisters.woowaSisters.domain.comment.Comment;
import com.woowaSisters.woowaSisters.dto.CommentListDTO;
import com.woowaSisters.woowaSisters.dto.CommentSaveDTO;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Lazy
public interface CommentService {
    List<CommentListDTO> findAllDesc();

    Comment saveComment(CommentSaveDTO entity);
}
