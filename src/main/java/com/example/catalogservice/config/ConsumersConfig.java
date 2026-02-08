package com.example.catalogservice.config;

import com.example.catalogservice.config.property.ConsumersProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ConsumersConfig {

    private final ConsumersProperties consumersProperties;

    @Bean
    public DirectExchange borrowingExchange() {
        return new DirectExchange(consumersProperties.getExchangeName());
    }

    @Bean
    public Queue borrowBookMessageQueue() {
        return new Queue(consumersProperties.getBorrowBookMessageProperties().getQueueName(), true);
    }

    @Bean
    public Queue returnBookMessageQueue() {
        return new Queue(consumersProperties.getReturnBookMessageProperties().getQueueName(), true);
    }

    @Bean
    public Binding borrowBookMessageBinding(Queue borrowBookMessageQueue, DirectExchange borrowingExchange) {
        return createBinding(
                borrowBookMessageQueue, borrowingExchange,
                consumersProperties.getBorrowBookMessageProperties().getRoutingKey()
        );
    }

    @Bean
    public Binding returnBookMessageBinding(Queue returnBookMessageQueue, DirectExchange borrowingExchange) {
        return createBinding(
                returnBookMessageQueue, borrowingExchange,
                consumersProperties.getReturnBookMessageProperties().getRoutingKey()
        );
    }

    private Binding createBinding(Queue queue, DirectExchange exchange, String routingKey) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

}
