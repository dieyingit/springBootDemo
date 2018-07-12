package com.example.demo.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.email.EmailService;

/**
 * If you need to run some specific code once the SpringApplicationhas started,
 * you can implement the ApplicationRunneror CommandLineRunnerinterfaces
 * 
 * @author lijin
 *
 */

@Component
public class CommandRunner implements CommandLineRunner {
	Logger log = LoggerFactory.getLogger(CommandRunner.class);

	@Override
	public void run(String... args) throws Exception {
		if (args != null && args.length > 0)
			log.info("XX Get CommandRunner with first paramter : {}", args[0]);
		else
			log.info("XX Get CommandRunner with no paramter ");
	}

}
