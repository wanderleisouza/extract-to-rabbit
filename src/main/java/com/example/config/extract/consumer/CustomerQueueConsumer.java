package com.example.config.extract.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.config.extract.domain.Customer;
import com.example.config.extract.service.ExtractService;
import com.example.config.extract.service.LoadService;

@Component
public class CustomerQueueConsumer {
	
	@Autowired
	LoadService loadService;
	
	Logger logger = LoggerFactory.getLogger(CustomerQueueConsumer.class);

    @RabbitListener(queues = ExtractService.queueName)
    public void receive(@Payload Customer customer) {
       logger.info("Consuming {}", customer);
       loadService.save(customer);
    }

}
