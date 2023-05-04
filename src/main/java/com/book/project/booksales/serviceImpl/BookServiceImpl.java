package com.book.project.booksales.serviceImpl;

import com.book.project.booksales.dto.SearchDTO;
import com.book.project.booksales.elasticsearchRepository.BookRepository;
import com.book.project.booksales.entity.Book;
import com.book.project.booksales.service.BookService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;
    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    public List<Book> getAllDocuments() {
        List<Book> documents = new ArrayList<>();
        bookRepository.findAll().forEach(documents::add);
        return documents;
    }

    @Override
    public Book updateOrSaveBook(Book book) {
//        Optional<Book> bookControl = Optional.ofNullable(bookRepository.findByName(book.getName()));
//        Book updated = new Book();
//        if (bookControl.isPresent()) {
//            updated = bookControl.get();
//            updated.setName(book.getName());
//            updated.setAuthor(book.getAuthor());
//            updated.setCategory(book.getCategory());
//            updated.setPrice(book.getPrice());
//            updated.setStock(book.getStock());
//        }
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void decreaseStockCount(String name) {
        Book book = bookRepository.findByName(name);
        if (book == null) {
            new RuntimeException("Book Not Found");
        }
        Long stockCount = book.getStock();
        if (stockCount > 0) {
            book.setStock(stockCount - 1);
        } else {
            throw new RuntimeException("Book is out of stock");
        }
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(String id) {
        Optional<Book> bookControl = bookRepository.findById(id);
        if (bookControl.isPresent()) {
            bookRepository.deleteById(id);
        } else
            throw new RuntimeException();
    }

    @Override
    public List<Book> getBooksWithSearch(SearchDTO searchDTO) {
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if (searchDTO.getName() != null) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("name", searchDTO.getName()));
        }
        if (searchDTO.getAuthor() != null) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("author", searchDTO.getAuthor()));
        }
        if (searchDTO.getCategory() != null) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("category", searchDTO.getCategory()));
        }

        NativeSearchQuery nativeSearchQuery = searchQueryBuilder.withQuery(boolQueryBuilder).build();
        SearchHits<Book> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, Book.class);
        List<Book> books = new ArrayList<>();
        searchHits.forEach(searchHit -> books.add(searchHit.getContent()));
        return books;
    }


    /*  //EĞER KOŞULLARDAN HERHANGİ BİRİNİ SAĞLAYANLARIN HEPSİNİ GETİRMEK İSTERSEK METODU BU ŞEKİLDE KULLANABİLİRİZ
    //ÖRNEĞİN NAME:DOSTOYEVSKİ CATEGORY:BİLİM KURGU OLANLARI LİSTELE DERSEK BU METODA GÖRE BU İKİ KOŞULDAN HERHANGİ BİRİNİ SAĞLAYANLARIN HEPSİ GELİR.
    //FAKAT YUKARIDAKİ METOD NAME:DOSTOYEVSKİ VE CATEGORY:BİLİM KURGU OLANLARI GETİRİR YANİ İKİ KOŞULU DA SAĞLAYANLARI LİSTELEYECEKTİR.
    @Override
    public List<Book> getBooksWithSearch(SearchDTO searchDTO) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        if (searchDTO.getName() != null && !searchDTO.getName().isEmpty()) {
            boolQuery.should(QueryBuilders.matchQuery("name", searchDTO.getName()));
        }
        if (searchDTO.getAuthor() != null && !searchDTO.getAuthor().isEmpty()) {
            boolQuery.should(QueryBuilders.matchQuery("author", searchDTO.getAuthor()));
        }
        if (searchDTO.getCategory() != null && !searchDTO.getCategory().isEmpty()) {
            boolQuery.should(QueryBuilders.matchQuery("category", searchDTO.getCategory()));
        }

        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQuery)
                .build();

        SearchHits<Book> searchHits = elasticsearchOperations.search(searchQuery, Book.class);
        List<Book> books = new ArrayList<>();
        searchHits.forEach(searchHit -> books.add(searchHit.getContent()));

        return books;
    }
*/
}