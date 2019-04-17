package com.saurabh.controllers;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.saurabh.domains.Address;
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

	@Test
	public void testShow() throws Exception {
		String id = "1";

		when(customerReporsitory.findByObjectId(id)).thenReturn(new Customer());

		mockMvc.perform(get("/customer/1")).andExpect(status().isOk()).andExpect(view().name("showcustomer"))
				.andExpect(model().attribute("customer", instanceOf(Customer.class)));
	}

	@Test
	public void testNewCustomer() throws Exception {
		verifyZeroInteractions(customerReporsitory);

		mockMvc.perform(get("/customer/new")).andExpect(status().isOk()).andExpect(view().name("createcustomer"));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testSave() throws Exception {
		String _id = "1";

		String firstName = "wcvgss";
		String lastName = "sfsfsd";
		String email = "ememe@gmail.com";
		String phoneNumber = "3342234";
		String city = "wers";
		String state = "ssewrw";
		String zipCode = "234234";
		String addressLineOne = "dsfsdfdsfs";
		String addressLineTwo = "sdfsdfsdf";

		Customer customer = new Customer();
		Address address = new Address();

		customer.set_id(_id);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmail(email);
		customer.setCity(city);
		customer.setState(state);
		customer.setPhoneNumber(phoneNumber);
		customer.setZipCode(zipCode);
		address.setAddressLineOne(addressLineOne);
		address.setAddressLineTwo(addressLineTwo);
		customer.setAddress(address);

		when(customerReporsitory.save(Matchers.<Customer>any())).thenReturn(customer);

		mockMvc.perform(post("/save").param("_id", _id).param("firstName", firstName).param("lastName", lastName)
				.param("email", email).param("phoneNumber", phoneNumber).param("city", city).param("state", state)
				.param("zipCode", zipCode).param("address.addressLineOne", addressLineOne)
				.param("address.addressLineTwo", addressLineTwo)).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/customer/1"));

		ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
		verify(customerReporsitory).save(customerCaptor.capture());

		Customer boundCustomer = customerCaptor.getValue();

		assertEquals(firstName, boundCustomer.getFirstName());
		assertEquals(lastName, boundCustomer.getLastName());
		assertEquals(addressLineOne, boundCustomer.getAddress().getAddressLineOne());
		assertEquals(addressLineTwo, boundCustomer.getAddress().getAddressLineTwo());
		assertEquals(city, boundCustomer.getCity());
		assertEquals(state, boundCustomer.getState());
		assertEquals(zipCode, boundCustomer.getZipCode());
		assertEquals(email, boundCustomer.getEmail());
		assertEquals(phoneNumber, boundCustomer.getPhoneNumber());
	}

	@Test
	public void testEdit() throws Exception {
		String id = "1";

		when(customerReporsitory.findByObjectId(id)).thenReturn(new Customer());

		mockMvc.perform(get("/customer/edit/1")).andExpect(status().isOk()).andExpect(view().name("createcustomer"))
				.andExpect(model().attribute("customer", instanceOf(Customer.class)));
	}

	@Test
	public void testDelete() throws Exception {
		String _id = "1";

		mockMvc.perform(get("/customer/delete/1")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/customers"));

		verify(customerReporsitory, times(1)).deleteById(_id);
	}

}
