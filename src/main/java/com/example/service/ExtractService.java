package com.example.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Customer;
import com.example.domain.Offer;

@Service
public class ExtractService {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public static final String CUSTOMERS_QUEUE_NAME = "extractCustomersQueue";
	public static final String OFFERS_QUEUE_NAME = "extractOffersQueue";
	
	private Queue customersQueue = new Queue(CUSTOMERS_QUEUE_NAME, true);
	private Queue offersQueue = new Queue(OFFERS_QUEUE_NAME, true);
	
	public void customersFromFileToQueue(final String fileName) throws IOException {
		Files.lines(Paths.get(fileName))
			.map(l -> l.split(","))
				.forEach(a -> {
					var offers = new HashSet<Offer>();
					var offer  = new Offer(a[3],null,null);
					offers.add(offer);
					var customer = new Customer(a[0], a[1], Customer.Category.valueOf(a[2]), offers);
					rabbitTemplate.convertAndSend(customersQueue.getName(), customer);
				});
	}
	
	public void offersFromFileToQueue(final String fileName) throws IOException {
		Files.lines(Paths.get(fileName))
			.map(l -> l.split(","))
				.forEach(a -> {
					var offer = new Offer(a[0],a[1],new BigDecimal(a[2]));
					rabbitTemplate.convertAndSend(offersQueue.getName(), offer);
				});
	}

}
