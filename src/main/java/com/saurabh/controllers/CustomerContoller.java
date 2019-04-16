package com.saurabh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.saurabh.domains.Customer;
import com.saurabh.services.CustomerRepository;

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
		model.addAttribute("customer", new Customer());
		return "createcustomer";
	}

	@RequestMapping("/save")
	public String save(Customer customer) {
		Customer newCustomer = customerRepository.save(customer);
		return "redirect:/customer/" + newCustomer.get_id();
	}

	@RequestMapping("/customer/edit/{id}")
	public String edit(@PathVariable String id, Model model) {
		customerRepository.findById(id).ifPresent(customerDetail -> model.addAttribute("customer", customerDetail));
		return "createcustomer";
	}

	@RequestMapping("/customer/delete")
	public String delete(@RequestParam String id) {
		customerRepository.deleteById(id);
		return "redirect:/customers";
	}

}
