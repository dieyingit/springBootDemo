package com.example.demo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	// pageSize 见spring.jdbc.template.fetch-size
	Page<User> findAll(Pageable pageable);

	User findByNameAndAgeAllIgnoringCase(String name, Integer age);
}
