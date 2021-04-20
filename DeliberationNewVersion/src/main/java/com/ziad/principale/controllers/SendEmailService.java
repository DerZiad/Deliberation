package com.ziad.principale.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {
	@Autowired
	private  JavaMailSender mailer;
	
	private static final String FROM_EMAIL = "ziadbougrine@gmail.com";
	public void sendEmail(String to,String body,String topic) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(FROM_EMAIL);
		message.setTo(to);
		message.setSubject(topic);
		message.setText(body);
	}
	
}
