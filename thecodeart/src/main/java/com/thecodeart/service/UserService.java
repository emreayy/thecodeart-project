package com.thecodeart.service;

import java.util.List;

import com.thecodeart.model.User;

public interface UserService {
	User findById(Long id);

	User findByUsername(String username);

	List<User> findAll();

	void changePassword(String oldPassword, String newPassword);
}
