package com.example.bookscorner.services;

import com.example.bookscorner.dto.request.CommentRequestDto;
import com.example.bookscorner.dto.response.CommentResponseDto;

public interface CommentService {
    CommentResponseDto addComment(CommentRequestDto commentRequestDto);
}
