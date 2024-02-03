package com.woowaSisters.woowaSisters.controller;

import com.woowaSisters.woowaSisters.domain.comment.Comment;
import com.woowaSisters.woowaSisters.dto.CommentListDTO;
import com.woowaSisters.woowaSisters.dto.CommentSaveDTO;
import com.woowaSisters.woowaSisters.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

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
    public List<CommentListDTO> getCommentList(){
        return commentService.findAllDesc();
    }

    @PostMapping("/save")
    public Comment saveComment(@RequestBody CommentSaveDTO comment){
        return commentService.saveComment(comment);
    }
}
