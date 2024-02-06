package com.MrFix30.ServiceImpl;

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
		
	}

