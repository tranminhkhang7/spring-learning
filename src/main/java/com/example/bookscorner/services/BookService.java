package com.example.bookscorner.services;

import com.example.bookscorner.entities.Book;

import java.util.List;

public interface BookService {

    public List<Book> getBooks();

    public void addNewBook(Book book);

    public Book updateBook(Book book);

    public Book deleteBook(Book book);
}
