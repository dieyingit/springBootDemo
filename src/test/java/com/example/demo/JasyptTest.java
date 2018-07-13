package com.example.demo;

import javax.annotation.Resource;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试jasyptspringboot
 * 
 * @author lijin
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JasyptTest {
	@Autowired
	private Environment env;
	// 只在配置文件中定义一个jasyptPassword，不覆盖Bean jasyptStringEncryptor时
	// @Autowired
	// private StringEncryptor jasyptStringEncryptor;

	// Resource：先按名称，再按类型（指定了name，则必须按照name）
	@Resource(name = "jaspyptEncryptorBean")
	private StringEncryptor jaspyptEncryptorBean;

	@Value("${spring.mail.username:defaultValue}")
	private String emailUsername;

	private String encryptString;
	private String decryptString;

	@Before
	public void getEncryptorString() {
		encryptString = jaspyptEncryptorBean.encrypt(emailUsername);
		decryptString = jaspyptEncryptorBean.decrypt(encryptString);
	}

	@Test
	public void jasytp() {
		System.out.println("jaspyptEncryptorBean: " + jaspyptEncryptorBean.getClass().getName());
		System.out.println("emailPassword: " + emailUsername);
		System.out.println("encryptString: " + encryptString);
		System.out.println("decryptString: " + decryptString);
		Assert.assertTrue(emailUsername.equals(decryptString));
	}

}
