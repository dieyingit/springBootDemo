package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserJdbcTemplateService;

/**
 * jdbc user controller 访问路径localhost:8080/jdbc
 * 
 * @author lijin
 *
 */
@RestController
@RequestMapping("/jdbc")
public class UserJdbcTemplateController {
	@Autowired
	private UserJdbcTemplateService userJdbcTemplateService;

	// user为cache名称
	@Cacheable(value = "user", key = "#id")
	@GetMapping("/user/{id}")
	public User findById(@PathVariable Long id) {
		return this.userJdbcTemplateService.findById(id);
	}

	@Cacheable(value = "user", key = "#root.method")
	@GetMapping("/userList")
	public List<User> getAllUsers() {
		return this.userJdbcTemplateService.getAllUsers();
	}
}
