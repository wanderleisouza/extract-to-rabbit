package com.example.config.extract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.config.extract.domain.Customer;
import com.example.config.extract.repository.CustomerRepository;
import com.example.customer.exception.CustomerNotFoundException;

@Service
public class LoadService {

	@Autowired
	CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
    
    public Customer findById(String id) {
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }
    
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

}
