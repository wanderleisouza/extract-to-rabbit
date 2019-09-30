package com.example.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Customer;
import com.example.domain.Offer;
import com.example.exception.DataNotFoundException;

@Service
public class LoadService {
	
	public final String CUSTOMER_KEY_PREFIX = "c:";
	public final String OFFER_KEY_PREFIX = "o:";
	
    @Autowired
    private RedisService redisService;
    
    public Customer save(final Customer customer) {
    	var o = redisService.getValue(CUSTOMER_KEY_PREFIX.concat(customer.getId())); 
    	if (o == null) {
        	redisService.setKey(CUSTOMER_KEY_PREFIX.concat(customer.getId()), customer);
        	return customer;
    	} else {
    		var c = (Customer)o;
    		c.getOffers().addAll(customer.getOffers());
        	redisService.setKey(CUSTOMER_KEY_PREFIX.concat(customer.getId()), c);
        	return c;
    	}
    }
    
	public Offer save(Offer offer) {
		redisService.setKey(OFFER_KEY_PREFIX.concat(offer.getId()), offer);
		return offer;
	}
    
    public Customer findCustomerById(final String id, boolean fullyLoadOffers) {
    	if (fullyLoadOffers) {
    		var c = findCustomerById(id);
    		var offers = fullyLoadOffers(c.getOffers());
    		c.setOffers(offers);
    		return c;
    	} else {
        	var o = redisService.getValue(CUSTOMER_KEY_PREFIX.concat(id)); 
        	return Optional.ofNullable((Customer)o).orElseThrow(DataNotFoundException::new);
    	}
    }
    
    private Customer findCustomerById(final String id) {
    	return findCustomerById(id, false);
    }
    
    public Offer findOfferById(final String id) {
    	var o = redisService.getValue(OFFER_KEY_PREFIX.concat(id)); 
    	return Optional.ofNullable((Offer)o).orElseThrow(DataNotFoundException::new);
    } 
    
    private Set<Offer> fullyLoadOffers(Set<Offer> offers) {
    	//TODO fully load
    	return offers;
    }

}
