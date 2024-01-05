package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Customer;
import com.synergisticit.repository.CustomerRepository;

import jakarta.validation.Valid;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired CustomerRepository customerRepository;
	
	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer findById(long customerId) {
		// TODO Auto-generated method stub
		Optional<Customer> optCustomer = customerRepository.findById(customerId);
		if(optCustomer.isPresent()) {
			return optCustomer.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public void deleteById(long customerId) {
		// TODO Auto-generated method stub
		customerRepository.deleteById(customerId);
	}

}
