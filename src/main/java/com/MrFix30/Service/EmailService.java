package com.MrFix30.Service;


import org.springframework.stereotype.Service;

import com.MrFix30.Model.EmailDetails;

public interface EmailService {
	    String sendSimpleMail(EmailDetails details,String token);
	 
	    // Method
	    // To send an email with attachment
	    String sendMailWithAttachment(EmailDetails details);
}
