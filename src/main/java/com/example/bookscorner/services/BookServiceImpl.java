package com.example.bookscorner.services;

import com.example.bookscorner.dto.response.BookResponseDto;
import com.example.bookscorner.entities.Book;
import com.example.bookscorner.repositories.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final ModelMapper mapper;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    public List<BookResponseDto> getBooks() {
        List<Book> bookList = bookRepository.findAll();

        List<BookResponseDto> bookResponseDtoList = new ArrayList<>();
        for (Book book: bookList) {
            BookResponseDto bookResponseDto = mapper.map(book, BookResponseDto.class);
            bookResponseDtoList.add(bookResponseDto);
        }

        return bookResponseDtoList;
    }

    public BookResponseDto addNewBook(Book book) {
        boolean exists = bookRepository.existsById(book.getBookId());
        if (exists) {
            throw new IllegalStateException("The book is existing");
        }
        bookRepository.save(book);

        BookResponseDto bookResponseDto = mapper.map(book, BookResponseDto.class);
        return bookResponseDto;
    }

    public BookResponseDto updateBook(Book book) {
//        System.out.println("hellothere" + book.getBookId());
        boolean exists = bookRepository.existsById(book.getBookId());
        if (!exists) {
            throw new IllegalStateException("The book you want to update does not exist");
        }

        Book bookOld = bookRepository.findById(book.getBookId()).get();

        bookOld.setTitle(book.getTitle());
        bookOld.setPublisher(book.getPublisher());
        bookOld.setPrice(book.getPrice());
        bookOld.setImageLink(book.getImageLink());
        bookOld.setDescription(book.getDescription());
        bookOld.setQuantityLeft(book.getQuantityLeft());

        bookRepository.save(bookOld);

        BookResponseDto bookResponseDto = mapper.map(bookOld, BookResponseDto.class);
        return bookResponseDto;
    }

    public BookResponseDto deleteBook(Book book) {
        boolean exists = bookRepository.existsById(book.getBookId());
        if (!exists) {
            throw new IllegalStateException("The book you want to delete does not exist");
        }

        Book bookOld = bookRepository.findById(book.getBookId()).get();

        bookOld.setStatus("disabled");

        bookRepository.save(bookOld);
        BookResponseDto bookResponseDto = mapper.map(bookOld, BookResponseDto.class);
        return bookResponseDto;
    }

    public List<BookResponseDto> searchBooks(String query, List<String> genre) {
        if (genre == null || query == null) return null;
        List<Integer> newList = new ArrayList<>();
        for(String s : genre) {
            try {
                newList.add(Integer.valueOf(s));
            } catch (NumberFormatException e){
                System.out.println(e + " Parse a alphabet string to a number gets error.");
            }
        }

        List<Book> listBooks = bookRepository.searchBooks(query, newList);

        List<BookResponseDto> listBooksDto = new ArrayList<>();

        for (Book bookEntity: listBooks) {
            BookResponseDto bookResponseDto = mapper.map(bookEntity, BookResponseDto.class);
            listBooksDto.add(bookResponseDto);
        }

        System.out.println(listBooksDto);

        return listBooksDto;
    }
}
