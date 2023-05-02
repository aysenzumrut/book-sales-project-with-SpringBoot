package com.book.project.booksales.elasticsearchRepository;

import com.book.project.booksales.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends ElasticsearchRepository<Book, String> {

    List<Book> findByName(String name);

    List<Book> findByAuthor(String author);

    List<Book> findByCategory(String category);
}
