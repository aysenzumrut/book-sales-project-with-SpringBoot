package com.book.project.booksales.serviceImpl;

import com.book.project.booksales.elasticsearchRepository.BookRepository;
import com.book.project.booksales.entity.Book;
import com.book.project.booksales.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository repository;

    public List<Book> getAllDocuments() {
        List<Book> documents = new ArrayList<>();
        repository.findAll().forEach(documents::add);
        return documents;
    }



/*
    @Override
    public List<Book> searchBooks(String keyword) {
        return null;
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        Optional<Book> bookControl = bookRepository.findById(book.getId());
        Book updated = new Book();
        if (bookControl.isPresent()) {
            updated = bookControl.get();
            updated.setName(book.getName());
            updated.setAuthor(book.getAuthor());
            updated.setCategory(book.getCategory());
            updated.setPrice(book.getPrice());
            updated.setStock(book.getStock());

        }
        return bookRepository.save(updated);
    }

    @Override
    @Transactional
    public void decreaseStockCount(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book Not Found"));
        int stockCount = book.getStock();
        if (stockCount > 0) {
            book.setStock(stockCount - 1);
        } else {
            throw new RuntimeException("Book is out of stock");
        }
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);

    }*/
}
