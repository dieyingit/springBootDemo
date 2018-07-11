package com.example.demo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 监听APP启动事件（亦可监听HTTP请求）
 * @author lijin
 *
 */
public class AppListener implements ApplicationListener {
	Logger log = LoggerFactory.getLogger(AppListener.class);

	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		log.info("XX Get ApplicationEvent:{}", arg0);
	}

}
