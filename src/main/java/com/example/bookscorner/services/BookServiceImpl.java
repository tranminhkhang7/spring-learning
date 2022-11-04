package com.example.bookscorner.services;

import com.example.bookscorner.dto.request.BookRequestDto;
import com.example.bookscorner.dto.request.BookRequestWithIdDto;
import com.example.bookscorner.dto.response.BookDetailResponseDto;
import com.example.bookscorner.dto.response.BookResponseDto;
import com.example.bookscorner.dto.response.ResponseDto;
import com.example.bookscorner.entities.Book;
import com.example.bookscorner.entities.BookGenre;
import com.example.bookscorner.entities.Genre;
import com.example.bookscorner.exceptions.NotFoundException;
import com.example.bookscorner.mappers.BookEntityAndBookDetailResponseDtoMapper;
import com.example.bookscorner.mappers.BookEntityAndBookResponseDtoMapper;
import com.example.bookscorner.mappers.ObjectMapperUtils;
import com.example.bookscorner.repositories.BookGenreRepository;
import com.example.bookscorner.repositories.BookRepository;
import com.example.bookscorner.repositories.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final GenreRepository genreRepository;

    @Autowired
    private final BookGenreRepository bookGenreRepository;
    @Autowired
    private final ModelMapper mapper;

    @Autowired
    private ObjectMapperUtils objectMapperUtils; //final?

    @Autowired
    private BookEntityAndBookResponseDtoMapper bookEntityAndBookResponseDtoMapper;

    @Autowired
    private BookEntityAndBookDetailResponseDtoMapper bookDetailResponseMapper;

//    public BookServiceImpl(BookRepository bookRepository,
//                           GenreRepository genreRepository,
//                           BookGenreRepository bookGenreRepository,
//                           ModelMapper mapper,
//                           BookEntityAndBookResponseDtoMapper bookEntityAndBookResponseDtoMapper,
//                           BookEntityAndBookDetailResponseDtoMapper bookDetailResponseMapper) {
//        this.bookRepository = bookRepository;
//        this.genreRepository = genreRepository;
//        this.bookGenreRepository = bookGenreRepository;
//        this.mapper = mapper;
//        this.bookEntityAndBookResponseDtoMapper = bookEntityAndBookResponseDtoMapper;
//        this.bookDetailResponseMapper = bookDetailResponseMapper;
//    }

    public List<BookResponseDto> getBooksWithActiveStatus(String query, List<String> genre) {
        if (null == query && null == genre) {
            List<Book> bookList = bookRepository.findAllByStatus("active");

            List<BookResponseDto> bookResponseDtoList =
                    bookEntityAndBookResponseDtoMapper.mapToResponseDto(bookList);

            return bookResponseDtoList;
        }

        //Query and genre list are not null

        List<Integer> newList = new ArrayList<>();
        for(String s : genre) {
            try {
                newList.add(Integer.valueOf(s));
            } catch (NumberFormatException e){
                System.out.println(e + " Parse a alphabet string to a number gets error.");
            }
        }

        List<Book> listBooks = bookRepository.searchBooks(query, newList, "active");
        List<BookResponseDto> bookResponseDtoList =
                bookEntityAndBookResponseDtoMapper.mapToResponseDto(listBooks);
        return bookResponseDtoList;
    }

    public List<BookResponseDto> getAllBooks() {
        List<Book> listBooks = bookRepository.findAll();

        List<BookResponseDto> bookResponseDtoList =
                bookEntityAndBookResponseDtoMapper.mapToResponseDto(listBooks);

        return bookResponseDtoList;
    }



    public BookDetailResponseDto getBook(int bookId) {
        boolean exists = bookRepository.existsById(bookId);
        if (!exists) {
            throw new NotFoundException("The book you want to retrieve does not exist");
        }

        Book book = bookRepository.findBookByBookId(bookId);

        BookDetailResponseDto bookDetailResponseDto = bookDetailResponseMapper.mapToDetailResponseDto(book);

        return bookDetailResponseDto;
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

    public ResponseEntity<ResponseDto> deleteBook(int bookId) {
        boolean exists = bookRepository.existsById(bookId);
        if (!exists) {
            throw new NotFoundException("The book you want to delete does not exist");
        }

        Book bookOld = bookRepository.findById(bookId).get();

        bookOld.setStatus("disabled");

        bookRepository.save(bookOld);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(null, "Successfully change the status of the book.","200"));

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
