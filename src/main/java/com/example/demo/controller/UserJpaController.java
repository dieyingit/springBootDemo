package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserJpaService;

/**
 * jpa user controller 访问路径localhost:8080/jpa
 * 
 * @author lijin
 *
 */
@RestController
@RequestMapping(value = "/jpa", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserJpaController {
	@Autowired
	private UserJpaService userJpaService;

	@GetMapping("/user/{id}")
	public User findById(@PathVariable Long id) {
		return this.userJpaService.findById(id);
	}

	@GetMapping("/userList/{pageNum}")
	public List<User> getAllUsers(@PathVariable Integer pageNum) {
		return this.userJpaService.getAllUsers(pageNum);
	}

	@GetMapping("/userList")
	public List<User> getAllUsers() {
		return this.userJpaService.getAllUsers();
	}
}
