package com.example.catalogservice.domain.book.controller;

import com.example.catalogservice.domain.book.entity.Book;
import com.example.catalogservice.domain.book.mapper.BookMapper;
import com.example.catalogservice.domain.book.service.BookService;
import com.example.catalogservice.openapi.api.BooksApi;
import com.example.catalogservice.openapi.model.BookResponse;
import com.example.catalogservice.openapi.model.CreateBookRequest;
import com.example.catalogservice.openapi.model.RestockBookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController implements BooksApi {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @Override
    public ResponseEntity<BookResponse> createBook(CreateBookRequest request) {
        Book book = bookService.createBook(request.getName(), request.getAuthor());
        BookResponse response = bookMapper.toBookResponse(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<BookResponse> restockBookById(Long id, RestockBookRequest restockBookRequest) {
        Book book = bookService.restockBookById(id, restockBookRequest.getQuantity());
        BookResponse response = bookMapper.toBookResponse(book);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<BookResponse> getBookById(Long id) {
        Book book = bookService.getBookById(id);
        BookResponse response = bookMapper.toBookResponse(book);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<BookResponse>> getBookPage(Integer page, Integer size, String name, String author) {
        List<Book> bookPage = bookService.getBookPage(page, size, name, author);
        List<BookResponse> response = bookMapper.toBookPageResponse(bookPage);
        return ResponseEntity.ok(response);
    }

}
