package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.BankTransaction;
import com.synergisticit.domain.Branch;

public interface BankTransactionService {
	public BankTransaction save(BankTransaction bankTransaction);
	public BankTransaction findById(long bankTransactionId);
	public List<BankTransaction> findAll();
}
