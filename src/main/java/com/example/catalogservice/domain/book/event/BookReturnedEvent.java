package com.example.catalogservice.domain.book.event;

public record BookReturnedEvent(Long borrowingId, Boolean success) {}
