package com.saurabh.services;

import org.springframework.data.repository.CrudRepository;

import com.saurabh.domains.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String> {

}
