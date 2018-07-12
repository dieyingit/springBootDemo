package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;

/**
 * 用户Service接口
 * 
 * @author lijin
 *
 */
public interface UserService {
	public User findById(Long id);

	public List<User> getAllUsers();

	public List<User> getAllUsers(Integer pageNum);
}
