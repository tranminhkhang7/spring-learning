package com.example.bookscorner.services;

import com.example.bookscorner.dto.request.CommentRequestDto;
import com.example.bookscorner.dto.response.CommentResponseDto;

import java.util.List;

public interface CommentService {
    CommentResponseDto addComment(CommentRequestDto commentRequestDto);

    List<CommentResponseDto> getCommentsOfABook(int bookId);
}
