package com.woowaSisters.woowaSisters.service;

import com.woowaSisters.woowaSisters.domain.comment.Comment;
import com.woowaSisters.woowaSisters.domain.comment.CommentRepository;
import com.woowaSisters.woowaSisters.dto.CommentListDTO;
import com.woowaSisters.woowaSisters.dto.CommentSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Component
@Transactional
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<CommentListDTO> findAllDesc(){
        List<CommentListDTO> collect = commentRepository.findAllDesc().stream()
                .map(CommentListDTO::new)
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public Comment saveComment(CommentSaveDTO entity){
        Comment comment = entity.toEntity();
        return commentRepository.save(comment);
    }
}
