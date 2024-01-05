package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.AccountType;
import com.synergisticit.domain.BankTransaction;
import com.synergisticit.service.AccountService;
import com.synergisticit.service.BankTransactionService;
import com.synergisticit.service.BranchService;
import com.synergisticit.service.CustomerService;

import jakarta.validation.Valid;

@Controller
public class AccountController {
	@Autowired AccountService accountService;
	@Autowired CustomerService customerService;
	@Autowired BranchService branchService;
	@Autowired BankTransactionService bankTransactionService;
	
	private Model modelAttributes(Model model) {
		model.addAttribute("accounts", accountService.findAll());
		model.addAttribute("customers", customerService.findAll());
		model.addAttribute("branches", branchService.findAll());
		model.addAttribute("accountTypes", AccountType.values());
		return model;
	}
	
	
	@GetMapping("accountForm")
	public String accountForm(Account account, Model model) {
		model = modelAttributes(model);
		return "accountForm";
	}
	@PostMapping("saveAccount")
	public String saveAccount(@Valid @ModelAttribute Account account, BindingResult br, Model model) {
		if(!br.hasFieldErrors()) {
			accountService.save(account);
		} else {
			for(var fe: br.getFieldErrors()) {
				System.out.println(fe.getDefaultMessage());
			}
		}
		model = modelAttributes(model);
		return "accountForm";
	}
	@RequestMapping("updateAccount")
	public String updateAccount(Account account, Model model) {
		
		model.addAttribute("account", accountService.findById(account.getAccountId()));
		model = modelAttributes(model);
		return "accountForm";
	}
	@RequestMapping("deleteAccount")
	public String deleteAccount(Account account, Model model) {
		Account toDelete = accountService.findById(account.getAccountId());
		for (BankTransaction t : toDelete.getSourceTransactions()) {
		    t.setBankTransactionFromAccount(null);
		}

		for (BankTransaction t : toDelete.getDestinationTransactions()) {
		    t.setBankTransactionToAccount(null);
		}

		accountService.deleteById(toDelete.getAccountId());
		
		model = modelAttributes(model);
		return "redirect:accountForm";
	}
}
