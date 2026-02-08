package com.example.catalogservice.domain.book.mapper;

import com.example.catalogservice.domain.book.entity.Book;
import com.example.catalogservice.openapi.model.BookResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookResponse toBookResponse(Book book);
    List<BookResponse> toBookPageResponse(List<Book> bookPage);
}
