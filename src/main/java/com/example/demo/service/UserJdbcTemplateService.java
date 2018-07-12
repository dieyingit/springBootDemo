package com.example.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.JdbcTemplateDao;
import com.example.demo.email.EmailServiceImpl;
import com.example.demo.entity.User;

/**
 * jdbcTemplate实现
 * 
 * @author lijin
 *
 */
@Service
public class UserJdbcTemplateService implements UserService {
	Logger log = LoggerFactory.getLogger(UserJdbcTemplateService.class);

	@Autowired
	private JdbcTemplateDao jdbcTemplateDao;

	@Override
	public User findById(Long id) {
		log.info("UserJdbcTemplateService.findById({})", id);
		User user = this.jdbcTemplateDao.findById(id);
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		log.info("UserJdbcTemplateService.getAllUsers()");
		return this.jdbcTemplateDao.getAllUsers();
	}

	@Override
	public List<User> getAllUsers(Integer pageNum) {
		return null;
	}
}
