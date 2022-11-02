package com.example.bookscorner.mappers;

import com.example.bookscorner.dto.response.BookResponseDto;
import com.example.bookscorner.dto.response.CartResponseDto;
import com.example.bookscorner.entities.Book;
import com.example.bookscorner.entities.Cart;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookEntityAndBookResponseDtoMapper {

    public BookResponseDto mapToResponseDto(Book bookEntity) {
        BookResponseDto bookResponseDto = new BookResponseDto();
        BeanUtils.copyProperties(bookEntity, bookResponseDto);
        return bookResponseDto;
    }

    public List<BookResponseDto> mapToResponseDto(List<Book> bookEntityList) {
        List<BookResponseDto> bookResponseDtoList = new ArrayList<>();
        for (Book bookEntity: bookEntityList) {
            BookResponseDto bookResponseDto = new BookResponseDto();
            BeanUtils.copyProperties(bookEntity, bookResponseDto);

            bookResponseDtoList.add(bookResponseDto);
        }

        return bookResponseDtoList;
    }
}
