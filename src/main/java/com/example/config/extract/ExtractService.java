package com.example.config.extract;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtractService {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	public static final String queueName = "customersQueue";
	private Queue queue = new Queue(queueName, true);
	
	public void sendToQueue(Customer customer) throws Exception {
	   rabbitTemplate.convertAndSend(queue.getName(), customer);
    }

	public List<Customer> loadCustomersFromFile(final String fileName) throws IOException {
		List<Customer> list = new ArrayList<>();
		Files.lines(Paths.get(fileName))
				.map(l -> l.split(","))
				.forEach(a -> list.add(new Customer(a[0], a[1], Customer.Category.valueOf(a[2]))));
		return list;
	}

}
