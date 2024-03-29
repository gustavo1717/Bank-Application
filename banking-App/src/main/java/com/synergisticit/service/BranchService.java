package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Branch;

public interface BranchService {
	public Branch save(Branch branch);
	public Branch findById(long branchId);
	public List<Branch> findAll();

	public void deleteById(long branchId);
}
