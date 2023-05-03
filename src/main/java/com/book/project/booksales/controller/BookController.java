package com.book.project.booksales.controller;

import com.book.project.booksales.entity.Book;
import com.book.project.booksales.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService myDocumentService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllDocuments() {
        List<Book> documents = myDocumentService.getAllDocuments();
        if (documents.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }
}
