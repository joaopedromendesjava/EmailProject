package br.com.email.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import br.com.email.project.model.Email;
import br.com.email.project.util.logUtil;

@Stateless
public class emailService extends Thread {
	
	private List<Email> emails;
	
	public static final String HEADER_CONTEXT = "text/html; charset=utf-8";
	
	public void enviar(Email email) {
	
		emails = new ArrayList<>();
		emails.add(email);
		send();
		
		}
	public void enviar(List<Email> emails) {
		this.emails  = emails;
		send();
		
	}
	
	private emailService copy() {
		emailService emailService = new emailService();
		emailService.emails = emails;
		return emailService;
		
	}
	
	private void send() {
		new Thread(this.copy()).start();
	}
	
	@Override
	public void run() {
		
		Properties properties = new Properties();
		
		properties.put("mail.smpt.starttls.enable", true);
		properties.put("mail.smtp.host", System.getProperty("email-project.mail.smtp.host"));
		properties.put("mail.smtp.port",System.getProperty("email-project.mail.smtp.port"));
		
		Session session = Session.getInstance(properties);
		session.setDebug(false);
		
		for (Email email : emails) {
			
			try {
				
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(System.getProperty("email-project.mail.smtp.from")));
				
			
				if(email.getDestinatario().contains("/")) {
					List<InternetAddress> emailsLocal = new ArrayList<>();
					
					for (String e: email.getDestinatario().split("/")) {
						emailsLocal.add(new InternetAddress(e));
					}
					message.addRecipients(Message.RecipientType.TO, emailsLocal.toArray(new InternetAddress[0]));
				}else {
					InternetAddress para = new InternetAddress(email.getDestinatario());
					message.addRecipient(Message.RecipientType.TO, para);
				}
				
				message.setSubject(email.getAssunto());
				MimeBodyPart textPart = new MimeBodyPart();
				textPart.setHeader("Content - type ", HEADER_CONTEXT);
				textPart.setContent(email.getTexto(), HEADER_CONTEXT);
				Multipart mp = new MimeMultipart();
				mp.addBodyPart(textPart);
				message.setContent(mp);
				Transport.send(message);
			
			} catch (MessagingException e) {
				
				logUtil.getlogger(emailService.class).error("Erro ao enviar email " + e.getMessage());
			}
		} 	
	}	
	
}
