package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.AccountType;
import com.synergisticit.domain.BankTransaction;
import com.synergisticit.domain.TransactionType;
import com.synergisticit.service.AccountService;
import com.synergisticit.service.BankTransactionService;
import com.synergisticit.validation.TransactionValidator;

import jakarta.validation.Valid;

@Controller
public class BankTransactionController {
	
	@Autowired BankTransactionService bankTransactionService;
	@Autowired AccountService accountService;
	
	@Autowired TransactionValidator transactionValidator;
	
	@GetMapping("transactionForm")
	public String bankTransaction(@ModelAttribute BankTransaction bankTransaction, Model model) {
		model.addAttribute("transactions", bankTransactionService.findAll());
		model.addAttribute("transactionTypes", TransactionType.values());
		model.addAttribute("NEW_ACCOUNT", TransactionType.NEW_ACCOUNT);
		model.addAttribute("DEPOSIT", TransactionType.DEPOSIT);
		model.addAttribute("WITHDRAWAL", TransactionType.WITHDRAWAL);
		model.addAttribute("TRANSFER", TransactionType.TRANSFER);
		model.addAttribute("accounts", accountService.findAll());
		return "transactionForm";
	}
	@PostMapping("saveBankTransaction")
	public String saveBankTransaction(@Valid BankTransaction bankTransaction, BindingResult br, Model model) {
		transactionValidator.validate(bankTransaction, br);
		
		if(!br.hasErrors()) {
			if(bankTransaction.getTransactionType() == TransactionType.DEPOSIT) {
				Account toAccount = bankTransaction.getBankTransactionToAccount();
				double newBal = toAccount.getAccountBalance() + bankTransaction.getTransactionAmount();
				toAccount.setAccountBalance(newBal);
				accountService.save(toAccount);
				bankTransaction.setBankTransactionFromAccount(null);
				bankTransactionService.save(bankTransaction);
				
			} else if(bankTransaction.getTransactionType() == TransactionType.WITHDRAWAL) {
				Account fromAccount = bankTransaction.getBankTransactionFromAccount();
				double newBal = bankTransaction.getBankTransactionFromAccount().getAccountBalance() - bankTransaction.getTransactionAmount();
				if(newBal >= 0) {
					fromAccount.setAccountBalance(newBal);
					accountService.save(fromAccount);
					bankTransaction.setBankTransactionToAccount(null);
					bankTransactionService.save(bankTransaction);
				}
			} else if(bankTransaction.getTransactionType() == TransactionType.TRANSFER) {
				Account toAccount = bankTransaction.getBankTransactionToAccount();
				Account fromAccount = bankTransaction.getBankTransactionFromAccount();
				double newBal = fromAccount.getAccountBalance() - bankTransaction.getTransactionAmount();
				double newBalTo = toAccount.getAccountBalance() + bankTransaction.getTransactionAmount();
				if(newBal >= 0) {
					fromAccount.setAccountBalance(newBal);
					toAccount.setAccountBalance(newBalTo);
					accountService.save(toAccount);
					accountService.save(fromAccount);
					bankTransactionService.save(bankTransaction);
				}
			} else { //New Account
				Account newAccount = new Account();
				Account fromAccount = bankTransaction.getBankTransactionFromAccount();
				newAccount.setAccountHolder(fromAccount.getAccountHolder());
				newAccount.setAccountBranch(fromAccount.getAccountBranch());
				newAccount.setAccountCustomer(fromAccount.getAccountCustomer());
				newAccount.setAccountType(AccountType.CHECKING);
				newAccount.setAccountBalance(bankTransaction.getTransactionAmount());
				if(bankTransaction.getBankTransactionFromAccount().getAccountBalance() >= bankTransaction.getTransactionAmount()) {
					fromAccount.setAccountBalance(fromAccount.getAccountBalance()-bankTransaction.getTransactionAmount());
					accountService.save(fromAccount);
					accountService.save(newAccount);
					bankTransaction.setBankTransactionToAccount(null);
					bankTransactionService.save(bankTransaction);
				}
			}
			
			
			
			model.addAttribute("transactionTypes", TransactionType.values());
			model.addAttribute("NEW_ACCOUNT", TransactionType.NEW_ACCOUNT);
			model.addAttribute("DEPOSIT", TransactionType.DEPOSIT);
			model.addAttribute("WITHDRAWAL", TransactionType.WITHDRAWAL);
			model.addAttribute("TRANSFER", TransactionType.TRANSFER);
			model.addAttribute("accounts", accountService.findAll());
			model.addAttribute("transactions", bankTransactionService.findAll());

			
			return "redirect:transactionForm";
		} else {
			model.addAttribute("transactionTypes", TransactionType.values());
			model.addAttribute("NEW_ACCOUNT", TransactionType.NEW_ACCOUNT);
			model.addAttribute("DEPOSIT", TransactionType.DEPOSIT);
			model.addAttribute("WITHDRAWAL", TransactionType.WITHDRAWAL);
			model.addAttribute("TRANSFER", TransactionType.TRANSFER);
			model.addAttribute("accounts", accountService.findAll());
			model.addAttribute("transactions", bankTransactionService.findAll());
			return "transactionForm";
		}
		
		
	}
	
}
