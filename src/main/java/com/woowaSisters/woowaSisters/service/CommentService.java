package com.woowaSisters.woowaSisters.service;

import com.woowaSisters.woowaSisters.domain.comment.Comment;
import com.woowaSisters.woowaSisters.dto.CommentSaveDTO;
import com.woowaSisters.woowaSisters.vo.CommentListVO;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Lazy
public interface CommentService {
    List<CommentListVO> findAllDesc();

    Comment saveComment(CommentSaveDTO entity);
}
