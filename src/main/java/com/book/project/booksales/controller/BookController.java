package com.book.project.booksales.controller;

import com.book.project.booksales.entity.Book;
import com.book.project.booksales.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

/*    @PutMapping("/decreaseStock")
    public ResponseEntity<?> decreaseStockCount(@RequestParam("id") Long id) {
        try {
            bookService.decreaseStockCount(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }*/

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return (ResponseEntity<List<Book>>) bookService.getAllBooks();
    }
}
