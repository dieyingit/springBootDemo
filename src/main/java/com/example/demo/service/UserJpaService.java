package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;

/**
 * JPA实现
 * 
 * @author lijin
 *
 */
@Service
public class UserJpaService implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findById(Long id) {
		Optional<User> user = this.userRepository.findById(id);
		return user.get();
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = this.userRepository.findAll();
		return users;
	}

	@Override
	public List<User> getAllUsers(Integer pageNum) {
		// pageNum是从0开始的
		Pageable pageable = PageRequest.of(pageNum, 2);
		Page<User> users = this.userRepository.findAll(pageable);
		return users.getContent();
	}
}
