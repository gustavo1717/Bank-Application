package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Account;

public interface AccountService {
	public Account save(Account account);
	public Account findById(long accountId);
	public List<Account> findAll();

	public void deleteById(long accountId);
}
