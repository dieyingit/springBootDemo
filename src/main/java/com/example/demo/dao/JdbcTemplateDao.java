package com.example.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public class JdbcTemplateDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public User findById(Long id) {
		User user = this.jdbcTemplate.queryForObject("select * from user where id=?", new Object[] { id },
				new UserMapper());
		return user;
	}

	public List<User> getAllUsers() {
		return this.jdbcTemplate.query("select * from user", new UserMapper());
	}

	private static final class UserMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getLong("id"));
			user.setName(rs.getString("name"));
			user.setUsername(rs.getString("username"));
			user.setAge(rs.getInt("age"));
			user.setBalance(rs.getBigDecimal("balance"));
			return user;
		}
	}
}
