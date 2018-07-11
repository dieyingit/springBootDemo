package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dao.JdbcTemplateDao;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JdbcTemplateDao jdbcTemplateDao;

	@GetMapping("/{id}")
	public User findById(@PathVariable Long id) {
		Optional<User> user = this.userRepository.findById(id);
		return user.get();
	}

	@GetMapping("/jdbcTemplate/{id}")
	public User findByIdJdbcTemplate(@PathVariable Long id) {
		User user = this.jdbcTemplateDao.findById(id);
		return user;
	}

	@GetMapping("/userList")
	public List<User> getAllUsers() {
		return this.jdbcTemplateDao.getAllUsers();
	}

	@GetMapping("/userList/{pageNum}")
	public List<User> getAllUsers(@PathVariable Integer pageNum) {
		// pageNum是从0开始的
		Pageable pageable = PageRequest.of(pageNum, 2);
		Page<User> users = this.userRepository.findAll(pageable);
		return users.getContent();
	}
}
