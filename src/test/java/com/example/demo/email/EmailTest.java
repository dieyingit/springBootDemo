package com.example.demo.email;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailTest {
	Logger log = LoggerFactory.getLogger(EmailTest.class);

	@Autowired
	private EmailService emailService;

	@Test
	public void testSendSimpleMail() {
		emailService.sendSimpleMail("lijin@rquest.com.cn", "简单邮件测试", "hehe");
	}

	@Test
	public void testSendAttachmentsMail() {
		List<Pair<String, File>> attachments = new ArrayList<Pair<String, File>>();
		ClassLoader classLoader = getClass().getClassLoader();
		/**
		 * getResource()方法会去classpath下找这个文件，获取到url resource,
		 * 得到这个资源后，调用url.getFile获取到 文件 的绝对路径
		 * C:/Users/lijin/git/xx/target/classes/application. properties
		 */
		URL url = classLoader.getResource("application.properties");
		URL urlSecond = classLoader.getResource("templates/mailTemplate.html");
		log.info("ClassLoader.getResource获取到的文件路径: {}", url.getFile());
		log.info("ClassLoader.getResource获取到的文件路径: {}", urlSecond.getFile());
		Pair<String, File> pair = Pair.of("测试文件1", new File(url.getFile()));
		Pair<String, File> pair2 = Pair.of("测试文件2", new File(urlSecond.getFile()));
		attachments.add(pair);
		attachments.add(pair2);
		emailService.sendAttachmentsMail("lijin@rquest.com.cn", "附件邮件测试", "hehe", attachments);
	}

}
