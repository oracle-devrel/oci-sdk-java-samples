package com.oracle.cloud.sdk.samples;

import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
	
	@Value("${mail.from}")
	private String fromEmail;
	
	@Value("${mail.fromName}")
	private String fromEmailName;
	
	@Value("${mail.smtp.user}")
	private String userId;
	
	@Value("${mail.smtp.password}")
	private String password;
	
	@Value("${mail.smtp.host}")
	private String host;
	
	@Value("${mail.smtp.port}")
	private String port;
	
	public void sendMail(MailContent content) {
		System.out.println("TLSEmail Start");
		
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userId, password);
			}
		};
		
		Session session = Session.getInstance(props, auth);
		sendEmailLocal(session, content);
	}
	
	private void sendEmailLocal(Session session, MailContent content){
		try
	    {
	      MimeMessage msg = new MimeMessage(session);
	      //set message headers
	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      msg.addHeader("format", "flowed");
	      msg.addHeader("Content-Transfer-Encoding", "8bit");

	      msg.setFrom(new InternetAddress(fromEmail, fromEmailName));

	      msg.setReplyTo(InternetAddress.parse(fromEmail, false));

	      msg.setSubject(content.subject, "UTF-8");

	      msg.setText(content.body, "UTF-8");

	      msg.setSentDate(new Date());

	      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(content.to, false));
	      
	      System.out.println("Message is ready");
    	  Transport.send(msg);  

	      System.out.println("EMail Sent Successfully!!");
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	}
	
	public static class MailContent {
		String to;
		String subject;
		String body;
		
		public MailContent(String to, String subject, String body) {
			this.to = to;
			this.subject = subject;
			this.body = body;
		}
	}
}
