package com.saurabh.controllers;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.saurabh.domains.Customer;
import com.saurabh.services.CustomerRepository;

public class CustomerControllerTest {

	@Mock
	private CustomerRepository customerReporsitory;

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

		when(customerReporsitory.findAll()).thenReturn((List) customers);

		mockMvc.perform(get("/customers")).andExpect(status().isOk()).andExpect(view().name("customerList"))
				.andExpect(model().attribute("customers", hasSize(2)));
	}

//	@Test
//	public void testShow() throws Exception {
//		String id = "5cb2c7eeede4ed8eef2725d4";
//
//		Customer cs = new Customer();
//
//		when(customerReporsitory.findById(id)).thenReturn(Optional.of(cs));
//
//		mockMvc.perform(get("/customer/5cb2c7eeede4ed8eef2725d4")).andExpect(status().isOk())
//				.andExpect(view().name("showcustomer"))
//				.andExpect(model().attribute("attribute", instanceOf(Optional.class)));
//	}

	@Test
	public void testNewCustomer() throws Exception {
		verifyZeroInteractions(customerReporsitory);

		mockMvc.perform(get("/customer/new")).andExpect(status().isOk()).andExpect(view().name("createcustomer"));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testSave() throws Exception {
		String id = "5cb2c7eeede4ed8eef2725d4";

		String firstName = "wcvgss";
		String lastName = "sfsfsd";
		String email = "ememe@gmail.com";
		String phoneNumber = "3342234";
		String city = "wers";
		String state = "ssewrw";
		String zipCode = "234234";

		Customer customer = new Customer();

		customer.set_id(id);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmail(email);
		customer.setCity(city);
		customer.setState(state);
		customer.setPhoneNumber(phoneNumber);
		customer.setZipCode(zipCode);

		when(customerReporsitory.save(Matchers.<Customer>any())).thenReturn(customer);

		mockMvc.perform(post("/save").param("id", id).param("firstName", firstName).param("lastName", lastName)
				.param("email", email).param("phoneNumber", phoneNumber).param("city", city).param("state", state)
				.param("zipCode", zipCode)).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/customer/5cb2c7eeede4ed8eef2725d4"));
	}

}
