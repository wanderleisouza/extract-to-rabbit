package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Customer;
import com.example.exception.DataNotFoundException;

@Service
public class LoadService {
	
	public final String CUSTOMER_KEY_PREFIX = "c:";
	
    @Autowired
    private RedisService redisService;
    
    public Customer save(final Customer customer) {
    	var o = redisService.getValue(CUSTOMER_KEY_PREFIX.concat(customer.getId())); 
    	if (o == null) {
        	redisService.setKey(CUSTOMER_KEY_PREFIX.concat(customer.getId()), customer);
        	return customer;
    	} else {
    		Customer c = (Customer)o;
    		c.getOfferIds().addAll(customer.getOfferIds());
        	redisService.setKey(CUSTOMER_KEY_PREFIX.concat(customer.getId()), c);
        	return c;
    	}
    }
    
    public Customer findCustomerById(final String id) {
    	var o = redisService.getValue(CUSTOMER_KEY_PREFIX.concat(id)); 
    	return Optional.ofNullable((Customer)o).orElseThrow(DataNotFoundException::new);
    }

}
