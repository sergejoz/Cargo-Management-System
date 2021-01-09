package com.msd.cms.service;

import com.msd.cms.entities.Customer;

import java.util.List;

public interface CustomerService {

	public List<Customer> findAllCustomers();

	public Customer findCustomerById(String id);

	public Customer createCustomer(Customer customer);

	public Customer updateCustomer(Customer customer);

	public void deleteCustomer(Customer customer);

	void deleteCustomerById(String id);

	void registerCustomer(Customer customer);
}
