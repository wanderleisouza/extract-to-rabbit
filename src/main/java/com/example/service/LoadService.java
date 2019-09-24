package com.example.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Customer;

@Service
public class LoadService {

	private String CUSTOMER_KEY_PREFIX = "x:";
	
    @Autowired
    private RedisService redisService;

    public Customer save(Customer customer) {
    	Map<String, Object> modelMap = new HashMap<String, Object>();
    	modelMap.put(customer.getId(), customer);
        redisService.setKey(CUSTOMER_KEY_PREFIX.concat(customer.getId()), modelMap);
        return customer;
    }
    
    public Customer findById(String id) {
        return (Customer) redisService.getValue(CUSTOMER_KEY_PREFIX.concat(id), id);
    }
    
}
