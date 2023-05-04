package com.book.project.booksales.service;


import com.book.project.booksales.dto.SearchDTO;
import com.book.project.booksales.entity.Book;

import java.util.List;

public interface BookService {


    List<Book> getAllDocuments();
    Book updateOrSaveBook(Book book);
    void decreaseStockCount(String name);
    void deleteBook(String id);
    List<Book> getBooksWithSearch(SearchDTO searchDTO);

}
