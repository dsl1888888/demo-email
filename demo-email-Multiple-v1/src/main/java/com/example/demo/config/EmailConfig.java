package com.example.demo.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmailConfig { 
 
	
	@Value("${gmailAccount}")
	private String gmailAccount;
	@Value("${gmailPassword}")
	private String gmailPassword;
	
	@Value("${email163Account}")
	private String email163Account;
	@Value("${email163Password}")
	private String email163Password;
	
	
	@Bean
	public JavaMailSender getJavaMailSenderGmail() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    
//	    mailSender.setUsername("dorosdebby@gmail.com");
//	    mailSender.setPassword("xxx");
	    mailSender.setUsername(gmailAccount);
	    mailSender.setPassword(gmailPassword);
	    
	    log.info("####################################################################################");
	    log.info("###################配置的值gmailAccount="+gmailAccount+",gmailPassword="+ gmailPassword);
	    log.info("####################################################################################");
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    return mailSender;
	}
		
	@Bean
	public JavaMailSender getJavaMailSender163() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.163.com");
	    mailSender.setPort(25);
	    
//	    mailSender.setUsername("fsr_lucky@163.com");
//	    mailSender.setPassword("xxxxxx");
	    mailSender.setUsername(email163Account);
	    mailSender.setPassword(email163Password);
	    log.info("####################################################################################");
	    log.info("###################配置的值email163Account="+email163Account+",email163Password=" ,email163Password);
	    log.info("####################################################################################");
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    return mailSender;
	}
}
