package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @PropertySource指定加载配置文件，属性可通过Environment获取
 * @author lijin
 *
 */
@Component
@PropertySources(value = {
		@PropertySource(value = "classpath:config/propertySourceTest1.properties", ignoreResourceNotFound = true),
		@PropertySource(value = "classpath:config/propertySourceTest2.properties", ignoreResourceNotFound = true) })
@PropertySource(value = {
		"classpath:config/propertySourceTest.properties" }, ignoreResourceNotFound = false, encoding = "UTF-8")
public class PropertySourceConfig {

}
