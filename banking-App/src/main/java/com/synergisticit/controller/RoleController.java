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

import com.synergisticit.domain.Role;
import com.synergisticit.service.RoleService;

import jakarta.validation.Valid;

@Controller
public class RoleController {
	
	@Autowired RoleService roleService;
	
	@GetMapping("roleForm")
	public String roleForm(Role role, Model model) {
		model.addAttribute("roles", roleService.findAll());
		return "roleForm";
	}
	@PostMapping("saveRole")
	public String saveRole(@Valid Role role, BindingResult br, Model model) {
		if(!br.hasFieldErrors()) {
			roleService.save(role);
		}
		model.addAttribute("roles", roleService.findAll());
		return "roleForm";
	}
	@RequestMapping("updateRole")
	public String updateRole(Role role, Model model) {
		
		model.addAttribute("role", roleService.findById(role.getRoleId()));
		model.addAttribute("roles", roleService.findAll());
		return "roleForm";
		
	}
	@RequestMapping("deleteRole")
	public String deleteRole(Role role, Model model) {
		roleService.deleteById(role.getRoleId());
		model.addAttribute("roles", roleService.findAll());
		return "roleForm";
	}
	
}
