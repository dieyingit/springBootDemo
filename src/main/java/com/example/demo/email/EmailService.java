package com.example.demo.email;

import java.io.File;
import java.util.List;

import org.springframework.data.util.Pair;

/**
 * 邮件发送服务
 * 
 * @author lijin
 *
 */
public interface EmailService {
	/**
	 * 发送简单邮件
	 * 
	 * @param sendTo
	 *            收件人地址
	 * @param titel
	 *            邮件标题
	 * @param content
	 *            邮件内容
	 */
	public void sendSimpleMail(String sendTo, String titel, String content);

	/**
	 * 发送简单邮件
	 * 
	 * @param sendTo
	 *            收件人地址
	 * @param titel
	 *            邮件标题
	 * @param content
	 *            邮件内容
	 * @param attachments<文件名，附件>
	 *            附件列表
	 */
	public void sendAttachmentsMail(String sendTo, String titel, String content, List<Pair<String, File>> attachments);
}
