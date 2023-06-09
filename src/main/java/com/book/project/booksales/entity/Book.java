package com.book.project.booksales.entity;

import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "books")
public class Book {
    @Id
    private String id;
    @Field(type = FieldType.Keyword, name = "name")
    private String name;
    @Field(type = FieldType.Keyword, name = "author")
    private String author;
    @Field(type = FieldType.Keyword, name = "category")
    private String category;
    @Field(type = FieldType.Long, name = "price")
    private Long price;
    @Field(type = FieldType.Long, name = "stock")
    private Long stock;

}