package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.Customer;
import com.synergisticit.domain.User;
import com.synergisticit.service.AccountService;
import com.synergisticit.service.CustomerService;
import com.synergisticit.service.UserService;

import jakarta.validation.Valid;

@Controller
public class CustomerController {
	@Autowired CustomerService customerService;
	@Autowired UserService userService;
	@Autowired AccountService accountService;
	
	@GetMapping("customerForm")
	public String customerForm(Customer customer, Model model) {
		
		model.addAttribute("user", new User());
		model.addAttribute("users", userService.findAll());
		model.addAttribute("customers", customerService.findAll());
		return "customerForm";
	}
	@PostMapping("saveCustomer")
	public String saveCustomer(@Valid Customer customer, BindingResult br, Model model) {
		if(!br.hasFieldErrors()) {
			model.addAttribute("user", new User());
			customerService.save(customer);
		}
		
		model.addAttribute("users", userService.findAll());
		model.addAttribute("customers", customerService.findAll());
		
		return "customerForm";
	}
	@RequestMapping("updateCustomer")
	public String updateCustomer(Customer customer, Model model) {
		
		model.addAttribute("user", new User());
		model.addAttribute("customer", customerService.findById(customer.getCustomerId()));
		model.addAttribute("users", userService.findAll());
		model.addAttribute("customers", customerService.findAll());

		return "customerForm";

	}
	@RequestMapping("deleteCustomer")
	public String deleteCustomer(Customer customer, Model model) {
		
		model.addAttribute("user", new User());
		var toDelete = customerService.findById(customer.getCustomerId());
		var customerAccounts = toDelete.getCustomerAccounts();
		if(customerAccounts!=null) {
			for(var account: customerAccounts) {
				account.setAccountCustomer(customerService.findById(71));
				accountService.save(account);
			}	
		}
		
		customerService.deleteById(toDelete.getCustomerId());
		model.addAttribute("users", userService.findAll());
		model.addAttribute("customers", customerService.findAll());

		
		return "customerForm";
	}
}
