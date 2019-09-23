package com.example.config.extract.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.config.extract.domain.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {}
