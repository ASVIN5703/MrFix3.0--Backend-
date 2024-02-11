package com.MrFix30.Service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.MrFix30.Model.Admin;

public interface AdminService {
	//register admin
	Admin adminregister(Admin admin);

	// admin check already present or not;
	boolean admincheck(Admin admin);

	boolean verifycredential(String adminName, String adminPass);

	// admin update
	Admin adminupdate(Admin admin);

	// admin delete
	Admin admindelete(Admin admin);

	default void adminservice() {
		System.out.println("admin service");
	}

	void activate(String token);

	void enableAdmin(Admin admin);

	Optional<Admin> getProfile(int id);

	Optional<Admin> updateprofile(Admin admin);

	Optional<Admin> getAdminDeatils(String adminName);
}
