package com.example.bookscorner.services;

import com.example.bookscorner.entities.Book;
import com.example.bookscorner.entities.Genre;
import com.example.bookscorner.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public void addNewBook(Book book) {
        boolean exists = bookRepository.existsById(book.getBookId());
        if (exists) {
            throw new IllegalStateException("The book is existing");
        }
        bookRepository.save(book);
    }

    public Book updateBook(Book book) {
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
        return bookOld;
    }

    public Book deleteBook(Book book) {
        boolean exists = bookRepository.existsById(book.getBookId());
        if (!exists) {
            throw new IllegalStateException("The book you want to delete does not exist");
        }

        Book bookOld = bookRepository.findById(book.getBookId()).get();

        bookOld.setStatus("disabled");

        bookRepository.save(bookOld);
        return bookOld;
    }
}
