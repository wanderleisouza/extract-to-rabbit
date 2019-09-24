package com.example.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Customer;
import com.example.exception.CustomerNotFoundException;

@Service
public class LoadService {

	private String CUSTOMER_KEY_PREFIX = "c:";
	
    @Autowired
    private RedisService redisService;

    public Customer save(Customer customer) {
    	Map<String, Object> modelMap = new HashMap<String, Object>();
    	modelMap.put(customer.getId(), customer);
        redisService.setKey(CUSTOMER_KEY_PREFIX.concat(customer.getId()), modelMap);
        return customer;
    }
    
    public Customer findById(String id) {
        Object o = redisService.getValue(CUSTOMER_KEY_PREFIX.concat(id), id);
        return Optional.ofNullable((Customer)o).orElseThrow(CustomerNotFoundException::new);
    }
    
}
