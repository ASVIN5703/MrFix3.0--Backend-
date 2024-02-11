package com.MrFix30.ServiceImpl;

import java.io.File;
import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.MrFix30.Model.EmailDetails;
import com.MrFix30.Service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

// Annotation
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
//    @Autowired 
//    private AdminRepository adminrepo;

	@Value("${spring.mail.username}")
	private String sender;

	// Method 1
	// To send a simple email
	public String sendSimpleMail(EmailDetails details, String token) {

		// Try block to check for exceptions
		try {
//        	Random rand = new Random();
//        	String token = String.format("%04d", rand.nextInt(10000));
			// String token="1999";
			// Creating a simple mail message
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			// Getting the current server's address dynamically
			String serverAddress = InetAddress.getLocalHost().getHostAddress();
			// String ngrokserveraddress="https://fdfa-121-200-55-211.ngrok-free.app";
			System.out.println("serveraddress" + serverAddress);
			String body = "Dear " + details.getRecipient() + ",\n \t To Activate Your Account Click below  \n "
					+ "http://" + serverAddress + ":8080/adminaccount/verify?token=" + token;
			// Setting up necessary details
			// String body = "Dear " + details.getRecipient() + ",\n \t To Activate Your
			// Account Click below \n
			// "+ngrokserveraddress+"/adminaccount/verify?token="+token;
			mailMessage.setFrom(sender);
			mailMessage.setTo(details.getRecipient());
			mailMessage.setText(body);
			mailMessage.setSubject(details.getSubject());

			// Sending the mail
			System.out.println("everververv" + mailMessage);
			javaMailSender.send(mailMessage);
			// return "Mail Sent Successfully...";
			return token;
		}

		// Catch block to handle the exceptions
		catch (Exception e) {
			return "Error while Sending Mail";
		}
	}

	// Method 2
	// To send an email with attachment
	public String sendMailWithAttachment(EmailDetails details) {
		// Creating a mime message
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;

		try {

			// Setting multipart as true for attachments to
			// be send
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(details.getRecipient());
			mimeMessageHelper.setText(details.getMsgBody());
			mimeMessageHelper.setSubject(details.getSubject());

			// Adding the attachment
			FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));

			mimeMessageHelper.addAttachment(file.getFilename(), file);

			// Sending the mail
			javaMailSender.send(mimeMessage);
			return "Mail sent Successfully";
		}

		// Catch block to handle MessagingException
		catch (MessagingException e) {

			// Display message when exception occurred
			return "Error while sending mail!!!";
		}
	}

}