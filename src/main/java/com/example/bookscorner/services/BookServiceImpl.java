package com.example.bookscorner.services;

import com.example.bookscorner.dto.request.BookRequestDto;
import com.example.bookscorner.dto.request.BookRequestWithIdDto;
import com.example.bookscorner.dto.response.BookResponseDto;
import com.example.bookscorner.entities.Book;
import com.example.bookscorner.entities.BookGenre;
import com.example.bookscorner.entities.Genre;
import com.example.bookscorner.exceptions.NotFoundException;
import com.example.bookscorner.repositories.BookGenreRepository;
import com.example.bookscorner.repositories.BookRepository;
import com.example.bookscorner.repositories.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final GenreRepository genreRepository;

    @Autowired
    private final BookGenreRepository bookGenreRepository;
    @Autowired
    private final ModelMapper mapper;

    public BookServiceImpl(BookRepository bookRepository, GenreRepository genreRepository, BookGenreRepository bookGenreRepository, ModelMapper mapper) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.bookGenreRepository = bookGenreRepository;
        this.mapper = mapper;
    }

    public List<BookResponseDto> getBooks(String query, List<String> genre) {
        if (null == query && null == genre) {
            List<Book> bookList = bookRepository.findAll();

            List<BookResponseDto> bookResponseDtoList = new ArrayList<>();
            for (Book book: bookList) {
                BookResponseDto bookResponseDto = mapper.map(book, BookResponseDto.class);
                bookResponseDtoList.add(bookResponseDto);
            }

            return bookResponseDtoList;
        }

//        if (genre == null || query == null) return null;

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

    public BookResponseDto getBook(int bookId) {
        boolean exists = bookRepository.existsById(bookId);
        if (!exists) {
            throw new IllegalStateException("The book you want to retrieve does not exist");
        }

        Book book = bookRepository.findBookByBookId(bookId);
        return null;
    }

    @Transactional
    public BookResponseDto addNewBook(BookRequestDto bookRequestDto) {
        Book book = mapper.map(bookRequestDto, Book.class);
        bookRepository.save(book);

        List<Integer> genreList = bookRequestDto.getBookGenres();
        for (int genreId: genreList) {
            boolean exists = genreRepository.existsById(genreId);
            if (!exists) {
                throw new NotFoundException("The genre does not exist");
            }
            Genre genre = genreRepository.findGenreByGenreId(genreId);

            BookGenre bookGenre = new BookGenre(book, genre);
            bookGenreRepository.save(bookGenre);
        }

        BookResponseDto bookResponseDto = mapper.map(book, BookResponseDto.class);
        return bookResponseDto;
    }

    @Transactional
    public BookResponseDto updateBook(BookRequestWithIdDto bookRequestWithIdDto) {
        boolean exists = bookRepository.existsById(bookRequestWithIdDto.getBookId());
        if (!exists) {
            throw new NotFoundException("The book you want to update does not exist");
        }

        Book bookOld = bookRepository.findById(bookRequestWithIdDto.getBookId()).get();

        bookOld.setTitle(bookRequestWithIdDto.getTitle());
        bookOld.setAuthor(bookRequestWithIdDto.getAuthor());
        bookOld.setPublisher(bookRequestWithIdDto.getPublisher());
        bookOld.setPrice(bookRequestWithIdDto.getPrice());
        bookOld.setImageLink(bookRequestWithIdDto.getImageLink());
        bookOld.setDescription(bookRequestWithIdDto.getDescription());
        bookOld.setQuantityLeft(bookRequestWithIdDto.getQuantityLeft());
        bookRepository.save(bookOld);

        bookGenreRepository.deleteBookGenresByBook_BookId(bookRequestWithIdDto.getBookId());
        List<Integer> genreList = bookRequestWithIdDto.getBookGenres();
        for (int genreId: genreList) {
            exists = genreRepository.existsById(genreId);
            if (!exists) {
                throw new NotFoundException("The genre does not exist");
            }
            Genre genre = genreRepository.findGenreByGenreId(genreId);

            BookGenre bookGenre = new BookGenre(bookOld, genre);
            bookGenreRepository.save(bookGenre);
        }

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

//    public List<BookResponseDto> searchBooks(String query, List<String> genre) {
//        if (genre == null || query == null) return null;
//        List<Integer> newList = new ArrayList<>();
//        for(String s : genre) {
//            try {
//                newList.add(Integer.valueOf(s));
//            } catch (NumberFormatException e){
//                System.out.println(e + " Parse a alphabet string to a number gets error.");
//            }
//        }
//
//        List<Book> listBooks = bookRepository.searchBooks(query, newList);
//
//        List<BookResponseDto> listBooksDto = new ArrayList<>();
//
//        for (Book bookEntity: listBooks) {
//            BookResponseDto bookResponseDto = mapper.map(bookEntity, BookResponseDto.class);
//            listBooksDto.add(bookResponseDto);
//        }
//
//        System.out.println(listBooksDto);
//
//        return listBooksDto;
//    }
}
