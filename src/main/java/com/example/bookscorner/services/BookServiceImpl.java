package com.example.bookscorner.services;

import com.example.bookscorner.dto.request.BookRequestDto;
import com.example.bookscorner.dto.request.BookRequestWithIdDto;
import com.example.bookscorner.dto.response.BookDetailResponseDto;
import com.example.bookscorner.dto.response.BookResponseDto;
import com.example.bookscorner.dto.response.GenreResponseDto;
import com.example.bookscorner.dto.response.ResponseDto;
import com.example.bookscorner.entities.Book;
import com.example.bookscorner.entities.BookGenre;
import com.example.bookscorner.entities.Comment;
import com.example.bookscorner.entities.Genre;
import com.example.bookscorner.exceptions.NotFoundException;
import com.example.bookscorner.mappers.BookEntityAndBookDetailResponseDtoMapper;
import com.example.bookscorner.mappers.BookEntityAndBookResponseDtoMapper;
import com.example.bookscorner.mappers.GenreEntityAndGenreResponseDtoMapper;
import com.example.bookscorner.mappers.ObjectMapperUtils;
import com.example.bookscorner.repositories.BookGenreRepository;
import com.example.bookscorner.repositories.BookRepository;
import com.example.bookscorner.repositories.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private GenreEntityAndGenreResponseDtoMapper genreEntityAndGenreResponseDtoMapper;

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
        List<Book> listBooks = new ArrayList<>();
        if (genre != null) {
            for (String s : genre) {
                try {
                    newList.add(Integer.valueOf(s));
                } catch (NumberFormatException e) {
                    System.out.println(e + " Parse a alphabet string to a number gets error.");
                }
            }
            listBooks = bookRepository.searchBooks(query, newList, "active");
        } else {
            listBooks = bookRepository.searchBooks(query, "active");
        }

//        List<Book> listBooks = bookRepository.searchBooks(query, newList, "active");
        List<BookResponseDto> bookResponseDtoList =
                bookEntityAndBookResponseDtoMapper.mapToResponseDto(listBooks);
        return bookResponseDtoList;
    }

    public List<BookResponseDto> getAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Book> listBooks = bookRepository.findAllByOrderByBookIdAsc(pageable);

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

        List<Comment> commentList = book.getBookComments();
//        System.out.println(commentList);
        float sum = 0;
        for (Comment comment: commentList) {
            sum += comment.getRating();
        }
        int average = Math.round(sum / commentList.size());

        List<BookGenre> bookGenreList = bookGenreRepository.findAllByBook_BookId(bookId);

        List<GenreResponseDto> genreResponseDtoList = new ArrayList<>();
        if (bookGenreList != null && !bookGenreList.isEmpty()) {
            for (BookGenre bookGenre: bookGenreList) {
                Genre genre = genreRepository.findGenreByGenreId(bookGenre.getGenre().getGenreId());
                GenreResponseDto genreResponseDto = genreEntityAndGenreResponseDtoMapper.mapToDto(genre);
                genreResponseDtoList.add((genreResponseDto));
            }
        }

        BookDetailResponseDto bookDetailResponseDto = bookDetailResponseMapper.mapToDetailResponseDto(book);
        bookDetailResponseDto.setAverageRating(average);
        bookDetailResponseDto.setGenreList(genreResponseDtoList);

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

        if (bookOld.getStatus().trim().equals("active")) {
            bookOld.setStatus("disabled");
        } else {
            bookOld.setStatus("active");
        }


        bookRepository.save(bookOld);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(null, "Successfully change the status of the book.","200"));

    }

    public int countAllBooks() {
        return bookRepository.countAllByBookIdGreaterThan(-1);
    }

}
