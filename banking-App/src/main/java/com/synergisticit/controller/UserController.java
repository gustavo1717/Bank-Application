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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.User;
import com.synergisticit.service.RoleService;
import com.synergisticit.service.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {
	@Autowired UserService userService;
	@Autowired RoleService roleService;
	
	@GetMapping("userForm")
	public String userForm(User user, Model model) {
		model.addAttribute("roles", roleService.findAll());
		model.addAttribute("users", userService.findAll());
		return "userForm";
		
	}
	@RequestMapping("saveUser")
	public String saveUser(@Valid @ModelAttribute User user, BindingResult br,Model model) {
		if(!br.hasFieldErrors()) {
			userService.save(user);
			model.addAttribute("roles", roleService.findAll());

			model.addAttribute("users", userService.findAll());
			return "userForm";
		} else {
			for(var fe : br.getFieldErrors()) {
				
			}
			model.addAttribute("roles", roleService.findAll());

			model.addAttribute("users", userService.findAll());
			return "userForm";
		}
	}
	@RequestMapping("saveUserModal")
	public String saveUserModal(User user, Model model) {
		
		userService.save(user);
		model.addAttribute("roles", roleService.findAll());

		model.addAttribute("users", userService.findAll());
		return "userForm";
	}
	@RequestMapping("updateUser")
	public String updateUser(User user, Model model) {
		model.addAttribute("roles", roleService.findAll());
		model.addAttribute("u", userService.findById(user.getUserId()));
		model.addAttribute("users", userService.findAll());
		return "userForm";
	}
	
	@RequestMapping("deleteUser")
	public String deleteUser(User user, Model model) {
		userService.deleteById(user.getUserId());
		model.addAttribute("roles", roleService.findAll());
		model.addAttribute("users", userService.findAll());
		return "userForm";
	}
	
	
}
