package com.wander.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wander.model.Role;
import com.wander.repository.RoleRepository;
import com.wander.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role findByRole(String string) {
		return roleRepository.findByRole(string);
	}

}
