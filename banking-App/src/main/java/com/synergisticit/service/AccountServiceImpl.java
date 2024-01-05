package com.synergisticit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Account;
import com.synergisticit.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public Account save(Account account) {
		// TODO Auto-generated method stub
		return accountRepository.save(account);
	}

	@Override
	public Account findById(long accountId) {
		// TODO Auto-generated method stub
		var opt = accountRepository.findById(accountId);
		if(opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return accountRepository.findAll();
	}

	@Override
	public void deleteById(long accountId) {
		// TODO Auto-generated method stub
		accountRepository.deleteById(accountId);
	}

}
