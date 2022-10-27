package com.example.bookscorner.controllers;

import com.example.bookscorner.dto.request.CommentRequestDto;
import com.example.bookscorner.dto.response.CommentResponseDto;
import com.example.bookscorner.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    CommentResponseDto addComment(@RequestBody CommentRequestDto commentRequestDto) {

        return commentService.addComment(commentRequestDto);
    }
}
