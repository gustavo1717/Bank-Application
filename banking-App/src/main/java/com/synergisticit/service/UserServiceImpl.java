package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.User;
import com.synergisticit.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{

@Autowired UserRepository userRepository;
	
	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public User findById(long userId) {
		// TODO Auto-generated method stub
		Optional<User> optUser = userRepository.findById(userId);
		if(optUser.isPresent()) {
			return optUser.get();
		} else {
			return null;
		}
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

//	@Override
//	public User updateById(long userId) {
//		if(userRepository.findById(userId)) {
//			userRepository.save()
//		}
//		return ;
//	}

	@Override
	public void deleteById(long userId) {
		Optional<User> optUser = userRepository.findById(userId);
		if(optUser.isPresent()){
			userRepository.delete(optUser.get());
		}
	}

	@Override
	public User findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findUserByUsername(username);
	}

}
