package com.woowaSisters.woowaSisters.controller;

import com.woowaSisters.woowaSisters.domain.park.ParkRepository;
import com.woowaSisters.woowaSisters.domain.user.UserRepository;
import com.woowaSisters.woowaSisters.service.CommentService;
import com.woowaSisters.woowaSisters.service.CommunityService;
import com.woowaSisters.woowaSisters.vo.CommentListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4000")

@RestController
@Lazy
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/test")
    public String test(){
        return "test!";
    }

    @GetMapping("/list")
    public List<CommentListVO> getCommentList(){
        return commentService.findAllDesc();
    }
}
