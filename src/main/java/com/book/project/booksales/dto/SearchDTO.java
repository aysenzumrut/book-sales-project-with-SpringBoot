package com.book.project.booksales.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDTO {
    @Field(type = FieldType.Keyword, name = "name")
    private String name;
    @Field(type = FieldType.Keyword, name = "author")
    private String author;
    @Field(type = FieldType.Keyword, name = "category")
    private String category;
}
