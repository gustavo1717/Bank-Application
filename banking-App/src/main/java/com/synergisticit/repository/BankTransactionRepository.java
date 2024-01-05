package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synergisticit.domain.BankTransaction;

@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransaction, Long>{
	
}
