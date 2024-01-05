package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.User;

public interface UserService {
	public User save(User user);
	public User findById(long userId);
	public List<User> findAll();
	public User findUserByUsername(String username);
	public void deleteById(long userId);
}
