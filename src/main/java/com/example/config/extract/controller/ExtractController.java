package com.example.config.extract.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.extract.domain.Customer;
import com.example.config.extract.service.ExtractService;
import com.example.config.extract.service.LoadService;

@RestController
public class ExtractController {

	@Autowired
	private ExtractService extractService;
	@Autowired
	private LoadService loadService;

	@GetMapping("/")
	public void send() throws Exception {
		List<Customer> list = extractService.loadCustomersFromFile("data.csv");
		for (Customer customer : list) {
			extractService.sendToQueue(customer);
		}
	}

	@GetMapping("/{id}")
	public Customer findById(@PathVariable final String id) {
		return loadService.findById(id);
	}

}