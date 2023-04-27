package com.book.project.booksales.service;

import com.book.project.booksales.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    List<Book> searchBooks(String keyword);

    Book saveBook(Book book);

    Book updateBook(Book book);

    void decreaseStockCount(Long id);

    void deleteBook(Long id);


}