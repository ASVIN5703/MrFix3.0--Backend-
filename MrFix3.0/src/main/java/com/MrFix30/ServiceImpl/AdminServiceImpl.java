package com.MrFix30.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MrFix30.Model.Admin;
import com.MrFix30.Repository.AdminRepository;
import com.MrFix30.Service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminrepo;
    
	@Override
	public Admin adminregister(Admin admin) {
		
		return adminrepo.save(admin);
	}

	

	@Override
	public Admin adminupdate(Admin admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin admindelete(Admin admin) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean admincheck(Admin admin) {
		
		return adminrepo.findByName(admin.getAdmin_name()).isPresent();
	}



	@Override
	public boolean verifycredential(String adminName, String adminPass) {
	    
		System.out.println(adminName+" "+adminPass);
		boolean val=adminrepo.findByAdminNameAndAdminPass(adminName, adminPass).isPresent();
		System.out.println(val);
		return val;
	}



	@Override
	public void activate(String code) {
		Admin ad=adminrepo.findByCode(code);
		ad.setAdmin_enable(true);
		
		adminrepo.save(ad);
	}



	@Override
	public void enableAdmin(Admin admin) {
		admin.setAdmin_enable(true);
		adminrepo.save(admin);
		
	}



	@Override
	public Optional<Admin> getProfile(int id) {
		
		return adminrepo.findById(id);
	}



	@Override
	public Optional<Admin> updateprofile(Admin admin) {
		 // Check if admin with the given ID exists
		System.out.println(admin.getId());
	    Optional<Admin> existingAdminOptional = adminrepo.findById(admin.getId());
        System.out.println(existingAdminOptional);
	    if (existingAdminOptional.isPresent()) {
	        // Admin exists, update profile details
	        Admin existingAdmin = existingAdminOptional.get();
	        
	        // Update relevant fields with new values from the provided admin
	        existingAdmin.setAdmin_name(admin.getAdmin_name());
	        existingAdmin.setAdmin_pass(admin.getAdmin_pass());
	        existingAdmin.setAdmin_email(admin.getAdmin_email());
	        // Add more fields to update as needed
            
	        // Save the updated admin back to the repository
	        Admin updatedAdmin = adminrepo.save(existingAdmin);

	        return Optional.of(updatedAdmin);
	    } else {
	        // Admin with the given ID not found
	        return Optional.empty();
	    }
	}



	@Override
	public Optional<Admin> getAdminDeatils(String adminName) {
		
		return adminrepo.findByName(adminName);
	}
		
	}

