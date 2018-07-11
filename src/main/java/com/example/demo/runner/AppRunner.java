package com.example.demo.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.example.demo.config.CtxUtil;
import com.example.demo.config.MyConfig;

/**
 * If you need to run some specific code once the SpringApplicationhas started,
 * you can implement the ApplicationRunneror CommandLineRunnerinterfaces
 * 
 * @author lijin
 *
 */

@Component
public class AppRunner implements ApplicationRunner {
	Logger log = LoggerFactory.getLogger(AppRunner.class);

	@Autowired
	private Environment env;

	@Autowired
	private MyConfig myConfig;

	@Value("${app.name}")
	private String name;

	@Value("${name:jin.li}")
	private String name2;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("XX Get ApplicationRunner: {}", args);
		log.info("@Autowired MyConfig:{}", myConfig);
		log.info("@Value name:{}", name);
		log.info("@Value name2:{}", name2);
		log.info("@PropertySource env-demo.url: {}", env.getProperty("demo.url"));
		log.info("ApplicationContextAware Get Obj: {}", CtxUtil.getCtx().getBean(MyConfig.class));
	}

}
