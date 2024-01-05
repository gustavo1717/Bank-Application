package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Role;

public interface RoleService {
	public Role save(Role role);
	public Role findById(long roleId);
	public List<Role> findAll();

	public void deleteById(long roleId);
}
