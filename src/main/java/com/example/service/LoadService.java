package com.example.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Customer;
import com.example.exception.DataNotFoundException;

@Service
public class LoadService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoadService.class);

	private String CUSTOMER_KEY_PREFIX = "c:";
	
    @Autowired
    private RedisService redisService;

    public Customer save(Customer customer) {
    	
    	Map<String, Object> modelMap = redisService.getMapValue(CUSTOMER_KEY_PREFIX.concat(customer.getId()));
    	if (modelMap.isEmpty()) {
	    	modelMap = new HashMap<String, Object>();
    	}
    	modelMap.put(customer.getOfferId(), customer);
    	
    	LOGGER.info("Saving {}", modelMap);
        redisService.setKey(CUSTOMER_KEY_PREFIX.concat(customer.getId()), modelMap);
        return customer;
        
    }
    
    public Customer findByOfferId(String customerId, String offerId) {
        Object o = redisService.getValue(CUSTOMER_KEY_PREFIX.concat(customerId), offerId);
        return Optional.ofNullable((Customer)o).orElseThrow(DataNotFoundException::new);
    }
    
}
