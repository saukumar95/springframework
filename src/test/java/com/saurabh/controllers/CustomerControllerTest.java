package com.saurabh.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.saurabh.domains.Customer;
import com.saurabh.services.CustomerService;

public class CustomerControllerTest {

	@Mock
	private CustomerService customerService;

	@InjectMocks
	private CustomerContoller customerController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	public void testList() throws Exception {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer());
		customers.add(new Customer());

		when(customerService.listAllCustomers()).thenReturn((List) customers);

		mockMvc.perform(get("/customers")).andExpect(status().isOk()).andExpect(view().name("customerList"))
				.andExpect(model().attribute("customers", hasSize(2)));
	}

}
