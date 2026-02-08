package com.example.catalogservice.messaging.borrowing.dto;

public record BookBorrowedMessage(Long borrowingId, Boolean success) {}
