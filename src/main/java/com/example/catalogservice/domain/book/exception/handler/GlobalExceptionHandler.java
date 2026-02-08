package com.example.catalogservice.domain.book.exception.handler;

import com.example.catalogservice.domain.book.exception.BookAlreadyExistsException;
import com.example.catalogservice.domain.book.exception.BookNotFoundException;
import com.example.catalogservice.openapi.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String message = exception.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("; "));
        return new ErrorResponse(message);
    }

    @ExceptionHandler @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleBookNotFoundException(BookNotFoundException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleBookAlreadyExistsException(BookAlreadyExistsException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception exception) {
        return new ErrorResponse(exception.getMessage());
    }

}
