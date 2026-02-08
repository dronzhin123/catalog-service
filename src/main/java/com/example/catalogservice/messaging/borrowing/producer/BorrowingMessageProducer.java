package com.example.catalogservice.messaging.borrowing.producer;

import com.example.catalogservice.messaging.borrowing.dto.BookBorrowedMessage;
import com.example.catalogservice.messaging.borrowing.dto.BookReturnedMessage;
import com.example.catalogservice.config.property.ProducersProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BorrowingMessageProducer {

    private final RabbitTemplate rabbitTemplate;
    private final ProducersProperties producersProperties;

    public void produceBookBorrowedMessage(Long borrowingId, Boolean success) {
        rabbitTemplate.convertAndSend(
                producersProperties.getExchangeName(),
                producersProperties.getBookBorrowedMessageProperties().getRoutingKey(),
                new BookBorrowedMessage(borrowingId, success)
        );
    }

    public void produceBookReturnedMessage(Long borrowingId, Boolean success) {
        rabbitTemplate.convertAndSend(
                producersProperties.getExchangeName(),
                producersProperties.getBookReturnedMessageProperties().getRoutingKey(),
                new BookReturnedMessage(borrowingId, success)
        );
    }

}
