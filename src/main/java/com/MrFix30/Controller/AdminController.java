package com.MrFix30.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import com.MrFix30.Model.Admin;
import com.MrFix30.Model.EmailDetails;
import com.MrFix30.Model.Token;
import com.MrFix30.Model.User;
import com.MrFix30.Service.AdminService;
import com.MrFix30.Service.EmailService;
import com.MrFix30.Service.TokenService;
import com.MrFix30.Service.UserService;

import CustomExceptions.DataAlreadyExists;
import CustomExceptions.InvalidCredentialsException;
@CrossOrigin
@RestController
public class AdminController {
	
	@Autowired
	private UserService userservice;
	
	 @Autowired
	private TokenService tokenService;
	@PostMapping("/adduser")
	public ResponseEntity<String> addUsers(@RequestBody User user) {
		if(userservice.usercheck(user)){
			  throw new ResponseStatusException(HttpStatus.CONFLICT, "Data Exists Already");
		}
		userservice.userregister(user);
		return  ResponseEntity.ok("Successfully added User");
	}
	
	@Autowired
	private AdminService adminservice;
	@Autowired
	private EmailService  email;
	@PostMapping("/register")
	public String register(@RequestBody Admin admin) {
		System.out.println(admin);
		
		if (!adminservice.admincheck(admin)) {
			 Admin registeredadmin= adminservice.adminregister(admin);
			 Token token=tokenService.generateToken(registeredadmin);
			EmailDetails em = new EmailDetails();
			em.setSubject("MrFix Account Verification");
			em.setMsgBody("This is from Asvin");
			em.setRecipient(admin.getAdmin_email());
			
			 String senttoken=email.sendSimpleMail(em,token.getToken());
		     System.out.println("The generated token is "+senttoken);
		   // admin.setVerificationCode(senttoken);
		    
			return "Succesfully registered";
		}
		throw new DataAlreadyExists("Data Present Already");
	}
//    @GetMapping("/")
//    public String pr() {
//    	return "AniBro";
//    }
	@GetMapping("/login/{adminName}/{adminPass}")
	public String adminlogin(@PathVariable String adminName, @PathVariable String adminPass) {
		System.out.println("enetered login");
		if (!adminservice.verifycredential(adminName, adminPass)) {
			throw new InvalidCredentialsException("Invalid Credentials");
		}
		return "successfully logined";
	}

	@GetMapping("/adminaccount/verify")
	public  ResponseEntity<String>  adminenable(@RequestParam String token) {
		  if (tokenService.isTokenValid(token)) {
	            // Enable the admin after verifying the token
	            Admin admin = tokenService.getTokenByTokenString(token).getAdmin();
	            adminservice.enableAdmin(admin);
	            return ResponseEntity.ok("Token is valid. Admin enabled.");
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid or expired");
	        }
		// System.out.println("got token"+token);
//		try {
//		adminservice.activate(token);
//		}
//		catch(Exception e) {return "wrng token";}
//		return "Admin Account Activated";
	}
//	 @Scheduled(fixedDelay=24000)
	 public void executeTokenCleanup() {
	        // Clean up expired tokens or perform any other token-related tasks
	        tokenService.cleanUpExpiredTokens();
	    }
    
}
