package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Branch;
import com.synergisticit.repository.BranchRepository;

import jakarta.validation.Valid;

@Service
public class BranchServiceImpl implements BranchService {

	@Autowired BranchRepository branchRepository;
	
	@Override
	public Branch save(Branch branch) {
		return branchRepository.save(branch);
	}

	@Override
	public Branch findById(long branchId) {
		// TODO Auto-generated method stub
		Optional<Branch> optBranch = branchRepository.findById(branchId);
		if(optBranch.isPresent()) {
			return optBranch.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Branch> findAll() {
		// TODO Auto-generated method stub
		return branchRepository.findAll();
	}

	@Override
	public void deleteById(long branchId) {
		// TODO Auto-generated method stub
		branchRepository.deleteById(branchId);
	}

}
