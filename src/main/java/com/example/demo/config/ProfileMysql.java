package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 根据spring.profiles.active指定的profile加载
 * 
 * @author lijin
 *
 */
@Configuration
@Profile("mysql")
public class ProfileMysql {

	public ProfileMysql() {
		System.out.println("ProfileMysql Load");
	}
}
