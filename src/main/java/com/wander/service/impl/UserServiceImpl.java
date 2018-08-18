package com.wander.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wander.model.User;
import com.wander.repository.UserRepository;
import com.wander.service.UserService;

/**
 * 
 * @author mrsagar
 *
 */

@Service("userService")
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public void saveUser(User user) {
		userRepository.save(user);
	}

}