package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {}
