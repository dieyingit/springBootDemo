package com.example.demo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.User;

/**
 * Calling REST Services with RestTemplate
 * 
 * @author lijin
 *
 */
@Service
public class RestTemplateClientService {
	Logger log = LoggerFactory.getLogger(RestTemplateClientService.class);

	private final RestTemplate restTemplate;

	// 构造方法注入
	public RestTemplateClientService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public User findUserById(Long id) {
		log.info("Use RestTemplate to consumer RestService http://localhost:8080/jpa/user/{id}");
		User user = this.restTemplate.getForObject("http://localhost:8080/jpa/user/" + id, User.class);
		log.info("Use RestTemplate get Result:{}", user);
		return user;
	}
}
