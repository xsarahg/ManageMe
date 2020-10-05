package com.novi.ManageMe;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@SpringBootApplication
public class ManageMeApplication {
	@Value("${novi.app.mailSecret}")
	String secret;

	public static void main(String[] args) {
		SpringApplication.run(ManageMeApplication.class, args);
	}

	// mail properties needed for specifying the SMTP server
	@Bean // annotates a method that produces a bean to be managed by the Spring container
	public JavaMailSender getJavaMailSender() {

		// instantiate JavaMailSenderImpl
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		// set SMTP server settings for Gmail
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("mm.manageme@gmail.com");
		mailSender.setPassword(secret);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		return mailSender;
	}

}
