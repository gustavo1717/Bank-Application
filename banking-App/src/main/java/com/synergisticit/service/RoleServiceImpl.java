package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Role;
import com.synergisticit.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired RoleRepository roleRepository;
	
	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role findById(long roleId) {
		Optional<Role> optRole = roleRepository.findById(roleId);
		if(optRole.isPresent()) {
			return optRole.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

	@Override
	public void deleteById(long roleId) {
		Optional<Role> optRole = roleRepository.findById(roleId);
		if(optRole.isPresent()){
			roleRepository.delete(optRole.get());
		}

	}

}
