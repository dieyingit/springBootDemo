package com.example.demo.email;

import java.io.File;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.util.Pair;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
	Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

	/**
	 * 发件邮箱
	 */
	@Value("${spring.mail.username}")
	private String emailFrom;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendSimpleMail(String sendTo, String titel, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(emailFrom);
		message.setTo(sendTo);
		message.setSubject(titel);
		message.setText(content);
		mailSender.send(message);
	}

	@Override
	public void sendAttachmentsMail(String sendTo, String titel, String content, List<Pair<String, File>> attachments) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(emailFrom);
			helper.setTo(sendTo);
			helper.setSubject(titel);
			helper.setText(content);

			for (Pair<String, File> pair : attachments) {
				helper.addAttachment(pair.getFirst(), new FileSystemResource(pair.getSecond()));
			}
		} catch (Exception e) {
			log.error("Send Email Error:", e);
			throw new RuntimeException(e);
		}
		mailSender.send(mimeMessage);
	}
}
