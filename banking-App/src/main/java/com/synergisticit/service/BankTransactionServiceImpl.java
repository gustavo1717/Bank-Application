package com.synergisticit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.BankTransaction;
import com.synergisticit.repository.BankTransactionRepository;

@Service
public class BankTransactionServiceImpl implements BankTransactionService {

	@Autowired BankTransactionRepository bankTransactionRepository;
	
	@Override
	public BankTransaction save(BankTransaction bankTransaction) {
		
		return bankTransactionRepository.save(bankTransaction);
	}

	@Override
	public BankTransaction findById(long bankTransactionId) {
		var optTrans = bankTransactionRepository.findById(bankTransactionId);
		if(optTrans.isPresent()) {
			return optTrans.get();
		} else {
			return null;
		}
	}

	@Override
	public List<BankTransaction> findAll() {
		// TODO Auto-generated method stub
		return bankTransactionRepository.findAll();
	}

	
	

	

}
