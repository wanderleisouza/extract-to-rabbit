package com.example.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.domain.Customer;
import com.example.service.ExtractService;
import com.example.service.LoadService;

@Component
public class CustomerQueueConsumer {

	@Autowired
	LoadService loadService;

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerQueueConsumer.class);

	@RabbitListener(queues = ExtractService.queueName)
	public void receive(@Payload Customer customer) {
		LOGGER.info("Consuming {}", customer);
		loadService.save(customer);
	}

}
