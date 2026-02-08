package com.example.catalogservice.domain.book.event.listener;

import com.example.catalogservice.messaging.borrowing.producer.BorrowingMessageProducer;
import com.example.catalogservice.domain.book.event.BookBorrowedEvent;
import com.example.catalogservice.domain.book.event.BookReturnedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class BookEventListener {

    private final BorrowingMessageProducer borrowingMessageProducer;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleBookBorrowedEvent(BookBorrowedEvent event) {
        borrowingMessageProducer.produceBookBorrowedMessage(event.borrowingId(), event.success());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleBookReturnedEvent(BookReturnedEvent event) {
        borrowingMessageProducer.produceBookReturnedMessage(event.borrowingId(), event.success());
    }

}
