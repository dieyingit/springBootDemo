package com.example.demo.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * 实现ApplicationContextAware，获取容器引用
 * 
 * @author lijin
 *
 */
@Configuration
public class CtxUtil implements ApplicationContextAware {
	private static ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		this.setCtx(ctx);
	}

	public static ApplicationContext getCtx() {
		return ctx;
	}

	public void setCtx(ApplicationContext ctx) {
		this.ctx = ctx;
	}
}
