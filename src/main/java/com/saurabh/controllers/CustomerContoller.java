package com.saurabh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.saurabh.domains.Customer;
import com.saurabh.services.CustomerRepository;
import com.saurabh.vo.CustomerVO;

@Controller
public class CustomerContoller {

	private CustomerRepository customerRepository;

	@Autowired
	public void setCustomerService(CustomerRepository customerService) {
		this.customerRepository = customerService;
	}

	@RequestMapping("/customers")
	public String listAllCustomers(Model model) {
		model.addAttribute("customers", customerRepository.findAll());
		return "customerList";
	}

	@RequestMapping("/customer/{id}")
	public String show(@PathVariable String id, Model model) {
		customerRepository.findById(id).ifPresent(customerDetail -> model.addAttribute("customer", customerDetail));
		return "showcustomer";
	}

	@RequestMapping("/customer/new")
	public String create(Model model) {
		return "createcustomer";
	}

	@RequestMapping("/save")
	public String save(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email,
			@RequestParam String phoneNumber, @RequestParam String city, @RequestParam String state,
			@RequestParam String zipCode) {
		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmail(email);
		customer.setPhoneNumber(phoneNumber);
		customer.setCity(city);
		customer.setState(state);
		customer.setZipCode(zipCode);
		customerRepository.save(customer);

		return "redirect:/customer/" + customer.getId();
	}

}
