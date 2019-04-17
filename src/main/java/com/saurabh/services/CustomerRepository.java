package com.saurabh.services;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.saurabh.domains.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

	@Query("{'lastName':?0}")
	List<Customer> findByLastName(String name);

	@Query("{'_id': ?0}")
	Customer findByObjectId(String id);

}
