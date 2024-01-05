package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synergisticit.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	
}
