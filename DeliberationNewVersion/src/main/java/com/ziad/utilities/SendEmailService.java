package com.ziad.utilities;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ziad.ServerConfiguration;
import com.ziad.utilities.beans.HtmlMessage;

@Service
public class SendEmailService {
	@Autowired
	private JavaMailSender mailer;
	
	
	private static final String FROM_EMAIL = "deliberation.sms@gmail.com";
	
	@Autowired
	private ServerConfiguration configLink;
	
	public static String SERVERLINK = "";

	
	
	public void sendEmail(HtmlMessage htmlmessage) throws MessagingException {
		if(SERVERLINK.length() == 0)
			SERVERLINK = configLink.getApiUrl();
		MimeMessage message = mailer.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		
		helper.setFrom(FROM_EMAIL);
		helper.setTo(htmlmessage.getTo());
		helper.setSubject(htmlmessage.getTopic());
		helper.setText(htmlmessage.generateMessage(), true);
		
		mailer.send(message);

	}

}
