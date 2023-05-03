package com.book.project.booksales.elasticsearchRepository;

import com.book.project.booksales.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends ElasticsearchRepository<Book, String> {

   /* @Query("{\"match\":{\"name\":\"?0\"}}")
    List<Book> findByName(String name);

    @Query("{\"match\":{\"author\":\"?0\"}}")
    List<Book> findByAuthor(String author);

    @Query("{\"match\":{\"publisher\":\"?0\"}}")
    List<Book> findByPublisher(String publisher);

    @Query("{\"match\":{\"description\":\"?0\"}}")
    List<Book> findByDescription(String description);

    @Query("{\"bool\":{\"should\":[{\"match\":{\"name\":\"?0\"}},{\"match\":{\"author\":\"?0\"}},{\"match\":{\"publisher\":\"?0\"}},{\"match\":{\"description\":\"?0\"}}]}}")
    List<Book> findByQuery(String query);*/

}
