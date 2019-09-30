package com.example.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;

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
	
	public void customersFromFileToQueue(final String fileName) throws IOException {
		Files.lines(Paths.get(fileName))
			.map(l -> l.split(","))
				.forEach(a -> {
					var offerIds = new HashSet<String>();
					offerIds.add(a[3]);
					var customer = new Customer(a[0], a[1], Customer.Category.valueOf(a[2]), offerIds);
					rabbitTemplate.convertAndSend(queue.getName(), customer);
				});
	}

}
