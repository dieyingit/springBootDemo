package com.example.demo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;

/**
 * 实现ApplicationContextAware获取容器索引 ，实现ApplicationListener
 * <ContextRefreshedEvent>可以在spring容器启动完成后执行一些操作（CommandRunner亦可）
 * 
 * @author lijin
 *
 */
@Component
public class ApplicationUtil implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {
	@SuppressWarnings("unused")
	private static ApplicationContext applicationContext;

	@SuppressWarnings("unused")
	private static HazelcastInstance hazelcastInstance;

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static HazelcastInstance getHazelcastInstance() {
		return hazelcastInstance;
	}

	public static void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
		ApplicationUtil.hazelcastInstance = hazelcastInstance;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationUtil.applicationContext = applicationContext;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println(">>>>>> ApplicationListener: Spring started <<<<<<<");
	}
}
