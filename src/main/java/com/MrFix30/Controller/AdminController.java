package com.MrFix30.Controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
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

@CrossOrigin(origins = "http://localhost:3000", // Add your allowed origins
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH},
        allowedHeaders = "*")
@RestController
public class AdminController {

	@Autowired
	private UserService userservice;

	@Autowired
	private TokenService tokenService;

	@PostMapping("/adduser")
	public ResponseEntity<Object> addUsers(@RequestBody User user) {
		if (userservice.usercheck(user)) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Data Exists Already");
		}

		return ResponseEntity.ok(userservice.userregister(user));
	}

	@Autowired
	private AdminService adminservice;
	@Autowired
	private EmailService email;

	@PostMapping("/register")
	public String register(@RequestBody Map<String,String> admin) {
		System.out.println(admin);
		    Admin admindetail = new Admin();
		    admindetail.setAdmin_name(admin.get("name"));
		    admindetail.setAdmin_pass(admin.get("pass"));
		    admindetail.setAdmin_email(admin.get("email"));
		if (!adminservice.admincheck(admindetail)) {
			Admin registeredadmin = adminservice.adminregister(admindetail);
			Token token = tokenService.generateToken(registeredadmin);
			EmailDetails em = new EmailDetails();
			em.setSubject("MrFix Account Verification");
			em.setMsgBody("This is from Asvin");
			em.setRecipient(admindetail.getAdmin_email());

			String senttoken = email.sendSimpleMail(em, token.getToken());
			System.out.println("The generated token is " + senttoken);
			// admin.setVerificationCode(senttoken);

			return "Succesfully registered";
		}
		throw new DataAlreadyExists("Data Present Already");
	}
//    @GetMapping("/")
//    public String pr() {
//    	return "AniBro";
//    }

//	@GetMapping("/login/{adminName}/{adminPass}")
//	public Optional<Admin> adminlogin(@PathVariable String adminName, @PathVariable String adminPass) {
//		System.out.println("enetered login");
//		if (!adminservice.verifycredential(adminName, adminPass)) {
//			throw new InvalidCredentialsException("Invalid Credentials");
//		}
//		return adminservice.getAdminDeatils(adminName);
//	}

	@PostMapping("/login")
	public ResponseEntity<Object> loginAuthentication(@RequestBody Map<String, String> logindetails) {
		String role = logindetails.get("role");
		if ("admin".equals(role)) {
			System.out.println("enetered admin login");
			return handleAdminLogin(logindetails);

		} else if ("user".equals(role)) {
			System.out.println("entered user role");
			return handleUserLogin(logindetails);

		} else {
			return ResponseEntity.badRequest().body("Invalid role");
		}
	}

	private ResponseEntity<Object> handleAdminLogin(Map<String, String> admin) {
		if (!adminservice.verifycredential(admin.get("name"), admin.get("pass"))) {
			throw new InvalidCredentialsException("Invalid Credentials");
		}
		Optional<Admin> adminDetails = adminservice.getAdminDeatils(admin.get("name"));
		return ResponseEntity.ok(adminDetails.orElse(null));
	}
    private ResponseEntity<Object> handleUserLogin(Map<String,String>user){
    	if(!userservice.verifycredential(user.get("name"), user.get("pass"))) {
    		throw new InvalidCredentialsException("Invalid Credentials");
    	}
    	Optional<Object> userdetails=Optional.ofNullable(userservice.usersearch(user.get("name")));
    	return ResponseEntity.ok(userdetails.orElse(null));
    }
	@GetMapping("/adminaccount/verify")
	public ResponseEntity<String> adminenable(@RequestParam String token) {
		if (tokenService.isTokenValid(token)) {
			// Enable the admin after verifying the token
			Admin admin = tokenService.getTokenByTokenString(token).getAdmin();
			adminservice.enableAdmin(admin);
			tokenService.deleteusedToken(token);
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

	@GetMapping("/admin/profile")
	public Optional<Admin> adminprofile(@RequestParam int id) {
		System.out.println("entered");
		return adminservice.getProfile(id);
	}

	@PatchMapping("/admin/profile")
	public Optional<Admin> adminprofileupdate(@RequestBody Admin admin) {
		System.out.println("Entered admin profile editing" + admin);
		return adminservice.updateprofile(admin);
	}

//	 @Scheduled(fixedDelay=24000)
//	 public void executeTokenCleanup() {
//	        // Clean up expired tokens or perform any other token-related tasks
//	        tokenService.cleanUpExpiredTokens();
//	    }

}
