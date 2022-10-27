package com.example.bookscorner.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentResponseDto {
    public int commentId;
    public Timestamp timestamp;
    public String content;
    public int customerId;
    public int bookId;
}
