package com.example.config.extract;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CustomerQueueConsumer {
	
	Logger logger = LoggerFactory.getLogger(CustomerQueueConsumer.class);

    @RabbitListener(queues = ExtractService.queueName)
    public void receive(@Payload Customer customer) {
       logger.info("Consuming {}", customer);
    }

}
