package com.example.catalogservice.messaging.borrowing.consumer;

import com.example.catalogservice.messaging.borrowing.dto.BorrowBookMessage;
import com.example.catalogservice.messaging.borrowing.dto.ReturnBookMessage;
import com.example.catalogservice.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BorrowingMessageConsumer {

    private final BookService bookService;

    @RabbitListener(queues = "#{@borrowBookMessageQueue.name}")
    public void consumeBorrowBookMessage(BorrowBookMessage message) {
        bookService.borrowBook(message.borrowingId(), message.bookId());
    }

    @RabbitListener(queues = "#{@returnBookMessageQueue.name}")
    public void consumeReturnBookMessage(ReturnBookMessage message) {
        bookService.returnBook(message.borrowingId(), message.bookId());
    }

}
