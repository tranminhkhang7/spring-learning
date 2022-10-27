package com.example.bookscorner.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentRequestDto {
    public Timestamp timestamp;
    public String content;
    public int customerId;
    public int bookId;
}
