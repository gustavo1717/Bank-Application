package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.Branch;
import com.synergisticit.service.AccountService;
import com.synergisticit.service.BranchService;

import jakarta.validation.Valid;

@Controller
public class BranchController {
	@Autowired BranchService branchService;
	@Autowired AccountService accountService;
	
	@GetMapping("branchForm")
	public String branchForm(Branch branch, Model model) {
		
		model.addAttribute("branches", branchService.findAll());
		return "branchForm";
	}
	
	@PostMapping("saveBranch")
	public String saveBranch(@Valid Branch branch, BindingResult br, Model model) {
		if(!br.hasFieldErrors())
			branchService.save(branch);
		model.addAttribute("branches", branchService.findAll());
		return "branchForm";
	}
	@RequestMapping("updateBranch")
	public String updateBranch(Branch branch, Model model) {
		model.addAttribute("branch", branchService.findById(branch.getBranchId()));
		model.addAttribute("branches", branchService.findAll());
		return "branchForm";
	}
	@RequestMapping("deleteBranch")
	public String deleteBranch(Branch branch, Model model) {
		Branch toDelete = branchService.findById(branch.getBranchId());
		for(var a : toDelete.getBranchAccount()) {
			accountService.findById(a.getAccountId());
			a.setAccountBranch(branchService.findById(36));
			accountService.save(a);
		}
		branchService.deleteById(branch.getBranchId());
		model.addAttribute("branches", branchService.findAll());

		return "branchForm";
	}
	
}
