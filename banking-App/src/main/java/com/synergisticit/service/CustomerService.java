package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Customer;

public interface CustomerService {
	public Customer save(Customer customer);
	public Customer findById(long customerId);
	public List<Customer> findAll();

	public void deleteById(long customerId);
}
