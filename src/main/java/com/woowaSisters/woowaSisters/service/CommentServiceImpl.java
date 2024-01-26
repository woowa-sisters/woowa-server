package com.woowaSisters.woowaSisters.service;

import com.woowaSisters.woowaSisters.domain.comment.CommentRepository;
import com.woowaSisters.woowaSisters.vo.CommentListVO;
import com.woowaSisters.woowaSisters.vo.CommunityListVO;
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
    public List<CommentListVO> findAllDesc(){
        List<CommentListVO> collect = commentRepository.findAllDesc().stream()
                .map(CommentListVO::new)
                .collect(Collectors.toList());
        return collect;
    }
}
