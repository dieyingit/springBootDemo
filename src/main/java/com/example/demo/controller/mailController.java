package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.email.EmailService;

/**
 * 触发邮件发送
 * 
 * @author lijin
 *
 */
@RestController
@RequestMapping(value = "/email", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class mailController {
	@Autowired
	private EmailService emailService;

	@GetMapping("/")
	public String sendMail() {
		emailService.sendSimpleMail("lijin@rquest.com.cn", "简单邮件测试", "hehe");
		return "发送成功";
	}
}
