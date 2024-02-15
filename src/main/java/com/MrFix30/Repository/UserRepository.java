package com.MrFix30.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.MrFix30.Model.Admin;
import com.MrFix30.Model.User;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	@Query(value = "select * from  user  where user_name like %?1", nativeQuery = true)
	Optional<User> findByUserName(String user_name);
	@Query(value = "SELECT * FROM user WHERE user_name = ?1 AND user_pass = ?2", nativeQuery = true)
	Optional<User> findByUserNameAndUserPass(String user_name, String user_pass);

	@Query(value = "select * from  user where user_email like %?1", nativeQuery = true)
	public User findByEmailIdIgnoreCase(String user_email);
    @Query(value="select * from user where user_name =?1 AND user_email=?2",nativeQuery=true)
    public User findByUserNameAndfindByuserEmail(String user_name,String user_email);
//	@Query(value = "select * from user where verification_code like %?1", nativeQuery = true)
//	public User findByCode(String code);
}
