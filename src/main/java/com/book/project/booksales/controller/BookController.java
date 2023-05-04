package com.book.project.booksales.controller;

import com.book.project.booksales.entity.Book;
import com.book.project.booksales.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/allbooks")
    public ResponseEntity<List<Book>> getAllDocuments() {
        List<Book> documents = bookService.getAllDocuments();
        if (documents.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @PutMapping("/decreaseStock")
    public ResponseEntity<?> decreaseStockCount(@RequestParam("id") String id) {
        try {
            bookService.decreaseStockCount(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

