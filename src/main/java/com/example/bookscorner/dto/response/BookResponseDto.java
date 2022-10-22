package com.example.bookscorner.dto.response;

import com.example.bookscorner.entities.BookAuthor;
import com.example.bookscorner.entities.BookGenre;
import com.example.bookscorner.entities.Cart;
import com.example.bookscorner.entities.OrderDetail;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookResponseDto {
    private int bookId;
    private String title;
    private String publisher;
    private double price;
    private String imageLink;
    private String description;
    private double averageRate;
}
