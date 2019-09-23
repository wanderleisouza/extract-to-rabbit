package com.example.config.extract;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExtractController {

    @Autowired
    private ExtractService extractService;
    
    @GetMapping("/")
    public ResponseEntity<Void> send() throws Exception {
    	
    	List<Customer> list = extractService.loadCustomersFromFile("data.csv");
		for (Customer customer : list) {
	        extractService.sendToQueue(customer);
		}
		
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
}
