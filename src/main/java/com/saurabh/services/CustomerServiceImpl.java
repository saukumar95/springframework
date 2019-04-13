package com.saurabh.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.saurabh.domains.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	private Map<Integer, Customer> customers = null;

	public CustomerServiceImpl() {
		loadAllCustomers();
	}

	@Override
	public List<Customer> listAllCustomers() {

		return new ArrayList<>(customers.values());
	}

	private Map<Integer, Customer> loadAllCustomers() {

		customers = new HashMap<>();

		Customer customer1 = new Customer();
		customer1.setFirstName("saurabh");
		customer1.setLastName("Kumar");
		customer1.setId(1);
		customer1.setPhoneNumber("8888888888");
		customer1.setEmail("abc@gmail.com");
		customer1.setCity("abc");
		customer1.setState("xgd");
		customer1.setZipCode("1224568");

		customers.put(1, customer1);

		Customer customer2 = new Customer();
		customer2.setFirstName("saurabh");
		customer2.setLastName("Kumar");
		customer2.setId(2);
		customer2.setPhoneNumber("8888888888");
		customer2.setEmail("abc@gmail.com");
		customer2.setCity("abc");
		customer2.setState("xgd");
		customer2.setZipCode("1224568");

		customers.put(2, customer2);

		Customer customer3 = new Customer();
		customer3.setFirstName("saurabh");
		customer3.setLastName("Kumar");
		customer3.setId(3);
		customer3.setPhoneNumber("8888888888");
		customer3.setEmail("abc@gmail.com");
		customer3.setCity("abc");
		customer3.setState("xgd");
		customer3.setZipCode("1224568");

		customers.put(3, customer3);

		Customer customer4 = new Customer();
		customer4.setFirstName("saurabh");
		customer4.setLastName("Kumar");
		customer4.setId(4);
		customer4.setPhoneNumber("8888888888");
		customer4.setEmail("abc@gmail.com");
		customer4.setCity("abc");
		customer4.setState("xgd");
		customer4.setZipCode("1224568");

		customers.put(4, customer4);

		Customer customer5 = new Customer();
		customer5.setFirstName("saurabh");
		customer5.setLastName("Kumar");
		customer5.setId(5);
		customer5.setPhoneNumber("8888888888");
		customer5.setEmail("abc@gmail.com");
		customer5.setCity("abc");
		customer5.setState("xgd");
		customer5.setZipCode("1224568");

		customers.put(5, customer5);

		return customers;
	}

}
