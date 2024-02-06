package com.MrFix30.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.MrFix30.Model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	@Query(value = "select * from  admin  where admin_name like %?1", nativeQuery = true)
	Optional<Admin> findByName(String admin_name);
	@Query(value = "SELECT * FROM admin WHERE admin_name = ?1 AND admin_pass = ?2 AND admin_enable = 1", nativeQuery = true)
	Optional<Admin> findByAdminNameAndAdminPass(String adminName, String adminPass);

	@Query(value = "select * from  admin  where admin_email like %?1", nativeQuery = true)
	public Admin findByEmailIdIgnoreCase(String admin_email);

	@Query(value = "select * from admin where verification_code like %?1", nativeQuery = true)
	public Admin findByCode(String code);
}
