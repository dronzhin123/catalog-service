package com.example.catalogservice.domain.book.event;

public record BookBorrowedEvent(Long borrowingId, Boolean success) {}
