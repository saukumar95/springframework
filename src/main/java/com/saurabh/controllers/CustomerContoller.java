package com.saurabh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.saurabh.services.CustomerService;

@Controller
public class CustomerContoller {

	private CustomerService customerService;

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@RequestMapping("/customers")
	public String listAllCustomers(Model model) {
		model.addAttribute("customers", customerService.listAllCustomers());
		return "customerList";
	}
}
