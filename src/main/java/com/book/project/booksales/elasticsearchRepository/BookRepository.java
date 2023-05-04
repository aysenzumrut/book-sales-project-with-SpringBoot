package com.book.project.booksales.elasticsearchRepository;

import com.book.project.booksales.entity.Book;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends ElasticsearchRepository<Book, String> {

    @Query("{\"match\":{\"name\":\"?0\"}}")
    Book findByName(String name);

    List<Book> findByNameContainingIgnoreCase(String name);
    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByCategoryContainingIgnoreCase(String category);

/*
    @Query("{\"bool\":{\"should\":[{\"match\":{\"name\":\"?0\"}},{\"match\":{\"author\":\"?0\"}},{\"match\":{\"publisher\":\"?0\"}},{\"match\":{\"description\":\"?0\"}}]}}")
    List<Book> findByQuery(String query);
*/

}
