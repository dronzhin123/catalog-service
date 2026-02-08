package com.example.catalogservice.messaging.borrowing.dto;

public record ReturnBookMessage(Long borrowingId, Long bookId) {}
