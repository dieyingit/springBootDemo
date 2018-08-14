package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.rest.RestTemplateClientService;

/**
 * 通过RestTemplate来消费RestService
 * 
 * @author lijin
 *
 */
@RestController
@RequestMapping(value = "/restTemplate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RestTemplateClientController {
	@Autowired
	private RestTemplateClientService restTemplateClientService;

	@GetMapping("/{id}")
	public User findById(@PathVariable Long id) {
		return this.restTemplateClientService.findUserById(id);
	}
}
