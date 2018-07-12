package com.example.demo;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.demo.listener.AppListener;

@SpringBootApplication
@EnableCaching // 开启缓存，需要显示的指定
public class SpringBootDemoApplication {

	@Bean
	public ExitCodeGenerator exitCodeGenerator() {
		return () -> 44;
	}

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(SpringBootDemoApplication.class);
		application.setBannerMode(Mode.OFF);
		application.setAddCommandLineProperties(false);
		application.addListeners(new AppListener());
		ConfigurableApplicationContext cxt = application.run(args);
		// System.out.println("Custom Exist Code: " + application.exit(cxt));
	}
}
