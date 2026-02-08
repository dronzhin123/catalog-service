package com.example.catalogservice.messaging.borrowing.dto;

public record BookReturnedMessage(Long borrowingId, Boolean success) {}
