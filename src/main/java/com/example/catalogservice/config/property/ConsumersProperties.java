package com.example.catalogservice.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter @Component
@ConfigurationProperties(prefix = "amqp")
public class ConsumersProperties {

    private String exchangeName;

    private MessageProperties borrowBookMessageProperties;
    private MessageProperties returnBookMessageProperties;

    @Getter @Setter
    public static class MessageProperties {
        private String queueName;
        private String routingKey;
    }

}
