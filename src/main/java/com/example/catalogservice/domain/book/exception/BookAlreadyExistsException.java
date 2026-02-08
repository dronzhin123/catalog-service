package com.example.catalogservice.domain.book.exception;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException() {
        super("Книга с таким названием уже существует");
    }
}
