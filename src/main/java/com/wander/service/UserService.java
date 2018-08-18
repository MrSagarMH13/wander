package com.wander.service;

import com.wander.model.User;

/**
 * 
 * @author mrsagar
 *
 */

public interface UserService {

	public User findByEmail(String email);

	public void saveUser(User user);

}