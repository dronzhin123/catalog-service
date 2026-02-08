package com.example.catalogservice.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter @Setter @Component
@ConfigurationProperties(prefix = "amqp")
public class ProducersProperties {

    private String exchangeName;

    private MessageProperties bookBorrowedMessageProperties;
    private MessageProperties bookReturnedMessageProperties;

    @Getter @Setter
    public static class MessageProperties {
        private String routingKey;
    }

}
