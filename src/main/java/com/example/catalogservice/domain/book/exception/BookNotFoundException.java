package com.example.catalogservice.domain.book.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        super("Книга не найдена");
    }
}
