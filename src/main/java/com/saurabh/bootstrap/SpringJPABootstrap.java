package com.saurabh.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.saurabh.domains.Customer;
import com.saurabh.services.CustomerRepository;

@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private CustomerRepository customerRepository;

	@Autowired
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		loadCustomers();
	}

	public void loadCustomers() {
		Customer customer1 = new Customer();
		customer1.setFirstName("saurabh");
		customer1.setLastName("Kumar");
		customer1.setPhoneNumber("8888888888");
		customer1.setEmail("abc@gmail.com");
		customer1.setCity("abc");
		customer1.setState("xgd");
		customer1.setZipCode("1224568");

		customerRepository.save(customer1);

		Customer customer2 = new Customer();
		customer2.setFirstName("saurabh");
		customer2.setLastName("Kumar");
		customer2.setPhoneNumber("8888888888");
		customer2.setEmail("abc@gmail.com");
		customer2.setCity("abc");
		customer2.setState("xgd");
		customer2.setZipCode("1224568");

		customerRepository.save(customer2);

		Customer customer3 = new Customer();
		customer3.setFirstName("saurabh");
		customer3.setLastName("Kumar");
		customer3.setPhoneNumber("8888888888");
		customer3.setEmail("abc@gmail.com");
		customer3.setCity("abc");
		customer3.setState("xgd");
		customer3.setZipCode("1224568");

		customerRepository.save(customer3);

		Customer customer4 = new Customer();
		customer4.setFirstName("saurabh");
		customer4.setLastName("Kumar");
		customer4.setPhoneNumber("8888888888");
		customer4.setEmail("abc@gmail.com");
		customer4.setCity("abc");
		customer4.setState("xgd");
		customer4.setZipCode("1224568");

		customerRepository.save(customer4);

		Customer customer5 = new Customer();
		customer5.setFirstName("saurabh");
		customer5.setLastName("Kumar");
		customer5.setPhoneNumber("8888888888");
		customer5.setEmail("abc@gmail.com");
		customer5.setCity("abc");
		customer5.setState("xgd");
		customer5.setZipCode("1224568");

		customerRepository.save(customer5);
	}
}
