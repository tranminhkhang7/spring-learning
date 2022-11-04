package com.example.bookscorner.mappers;

import com.example.bookscorner.dto.response.BookDetailResponseDto;
import com.example.bookscorner.dto.response.BookResponseDto;
import com.example.bookscorner.entities.Book;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookEntityAndBookDetailResponseDtoMapper {

    public BookDetailResponseDto mapToDetailResponseDto(Book bookEntity) {
        BookDetailResponseDto bookDetailResponseDto = new BookDetailResponseDto();
        BeanUtils.copyProperties(bookEntity, bookDetailResponseDto);
        return bookDetailResponseDto;
    }

    public List<BookDetailResponseDto> mapToDetailResponseDto(List<Book> bookEntityList) {
        List<BookDetailResponseDto> bookDetailResponseDtoList = new ArrayList<>();
        for (Book bookEntity: bookEntityList) {
            BookDetailResponseDto bookDetailResponseDto = new BookDetailResponseDto();
            BeanUtils.copyProperties(bookEntity, bookDetailResponseDto);

            bookDetailResponseDtoList.add(bookDetailResponseDto);
        }

        return bookDetailResponseDtoList;
    }
}
