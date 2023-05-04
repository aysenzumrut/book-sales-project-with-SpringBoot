package com.book.project.booksales.controller;

import com.book.project.booksales.dto.SearchDTO;
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

    @GetMapping("/allbooks") //ES DEKİ TÜM KİTAPLARI GETİRİR
    public ResponseEntity<List<Book>> getAllDocuments() {
        List<Book> documents = bookService.getAllDocuments();
        if (documents.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @PutMapping("/updateorsavebook") //VERİ VARSA GÜNCELLER, YOKSA YENİ KAYIT OLUŞTURUR
    public Book updateBook(@RequestBody Book book){
        return bookService.updateOrSaveBook(book);
    }

    @PutMapping("/decreaseStock") //KİTABIN STOK SAYISINI HER SEFERİDE 1 AZALTIR (SATIN ALMA İŞLEMİ İÇİN)
    public ResponseEntity<?> decreaseStockCount(@RequestParam("name") String name) {
        try {
            bookService.decreaseStockCount(name);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletebook") //KİTABI İD YE GÖRE SİLER
    public ResponseEntity<String> deletebook(@RequestParam String id){
        try {
            bookService.deleteBook(id);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The Book is Not Found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body("The Book is deleted");
    }

    @PostMapping("/searchbooks") //NAME-AUTHOR-CATEGORY'E GÖRE ARAMA YAPAR
    public List<Book> getBooksByNameOrAuthorOrCategory(@RequestBody SearchDTO searchDTO){
        return bookService.getBooksWithSearch(searchDTO);
    }
}

