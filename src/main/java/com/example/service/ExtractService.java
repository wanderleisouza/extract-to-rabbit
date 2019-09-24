package com.example.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Customer;

@Service
public class ExtractService {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public static final String queueName = "customersQueue";
	private Queue queue = new Queue(queueName, true);
	
	public void fromFileToQueue(final String fileName) throws IOException {
		Files.lines(Paths.get(fileName))
			.map(l -> l.split(","))
			.forEach(a -> rabbitTemplate.convertAndSend(queue.getName(), new Customer(a[0], a[1], Customer.Category.valueOf(a[2]))));
	}

}
