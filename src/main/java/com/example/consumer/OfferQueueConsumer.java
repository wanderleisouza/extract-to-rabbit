package com.example.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.domain.Offer;
import com.example.service.ExtractService;
import com.example.service.LoadService;

@Component
public class OfferQueueConsumer {

	@Autowired
	LoadService loadService;

	private static final Logger LOGGER = LoggerFactory.getLogger(OfferQueueConsumer.class);

	@RabbitListener(queues = ExtractService.OFFERS_QUEUE_NAME)
	public void receive(@Payload Offer offer) {
		LOGGER.info("Consuming {} from rabbit", offer);
		loadService.save(offer);
	}

}
