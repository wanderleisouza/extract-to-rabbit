package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Customer;
import com.example.service.ExtractService;
import com.example.service.LoadService;

@RestController
public class ExtractController {

	@Autowired
	private ExtractService extractService;
	@Autowired
	private LoadService loadService;

	@PostMapping("/")
	public void readFiles() throws Exception {
		extractService.offersFromFileToQueue("data_offer_aggregate.csv");
		extractService.customersFromFileToQueue("data_customer_offer.csv");
	}

	@GetMapping("/{id}/offers")
	public Customer findById(@PathVariable final String id) {
		return loadService.findCustomerById(id, true);
	}

}