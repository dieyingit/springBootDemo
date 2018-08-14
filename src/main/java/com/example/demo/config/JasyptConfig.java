package com.example.demo.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 提供自定义的Encryptor，见https://github.com/ulisesbocchio/jasypt-spring-boot
 * 
 * @author lijin
 *
 */
@Configuration
public class JasyptConfig {
	@Value("${jasypt.encryptor.password}")
	private String password;

	// 如果不定义jasypt.encryptor.bean=encryptorBean，则BeanName必须为jasyptStringEncryptor
	// @Bean("jasyptStringEncryptor")
	@Bean("jaspyptEncryptorBean")
	public StringEncryptor stringEncryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword(password);
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setStringOutputType("base64");
		encryptor.setConfig(config);
		return encryptor;
	}
}
