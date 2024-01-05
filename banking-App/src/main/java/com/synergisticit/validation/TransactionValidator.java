package com.synergisticit.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.BankTransaction;

@Component
public class TransactionValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return BankTransaction.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		BankTransaction t = (BankTransaction) target;
		Account toAccount = t.getBankTransactionToAccount();
		Account fromAccount = t.getBankTransactionFromAccount();
		
		
		switch (t.getTransactionType()) {
		case DEPOSIT:
			if(toAccount == null) {
				errors.rejectValue("bankTransactionToAccount","to.account", "Select an account");
			}
			break;
		case NEW_ACCOUNT:
			if(fromAccount == null) {
				errors.rejectValue("bankTransactionFromAccount", "from.account", "Select an account");
			} else {
				if(fromAccount.getAccountBalance() < t.getTransactionAmount()) {
					errors.rejectValue("transactionAmount", "transaction.amount", "Transaction amount should be less than available balance.");
				}
			}
			break;
		case TRANSFER:
			if(toAccount == null) {
				errors.rejectValue("bankTransactionToAccount", "to.account", "Select an account");
			}
			if(fromAccount == null) {
				errors.rejectValue("bankTransactionFromAccount", "from.account", "Select an account");
				break;
			}

			if(fromAccount.getAccountBalance() < t.getTransactionAmount()) {
				errors.rejectValue("transactionAmount", "transaction.amount", "Transaction amount should be less than available balance.");
			}
			break;
		case WITHDRAWAL:
			if(fromAccount == null) {
				errors.rejectValue("bankTransactionFromAccount", "from.account", "Select an account");
				break;
			}
			if(fromAccount.getAccountBalance() < t.getTransactionAmount()) {
				errors.rejectValue("transactionAmount", "transaction.amount", "Transaction amount should be less than available balance.");
			}
			break;
		default:
			
			errors.rejectValue("unknown", "unexpected", "Unexpected error. Please try again.");
			
			break;
			
			
		
		
		}
	}

	

}
