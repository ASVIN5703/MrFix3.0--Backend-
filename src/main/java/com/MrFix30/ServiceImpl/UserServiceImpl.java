package com.MrFix30.ServiceImpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MrFix30.Model.User;
import com.MrFix30.Repository.UserRepository;
import com.MrFix30.Service.UserService;

import CustomExceptions.DataAlreadyExists;
import lombok.extern.slf4j.Slf4j;





@Service
@Slf4j 
public class UserServiceImpl implements UserService {
	 private final UserRepository userrepo;
    @Autowired
     public UserServiceImpl(UserRepository userrepo) {
    	 this.userrepo=userrepo;
    }
  
	@Override
	public String userregister(User user) {
	    Optional<User> existingUser = userrepo.findByUserName(user.getUser_name());

	    if (existingUser.isPresent()) {
	        throw new DataAlreadyExists("Username already exists");
	    }

	    existingUser = Optional.ofNullable(userrepo.findByEmailIdIgnoreCase(user.getUser_email()));

	    if (existingUser.isPresent()) {
	        throw new DataAlreadyExists("Email already exists");
	    }

	    userrepo.save(user);
	    return "User registered successfully";
	    
	}
	@Override
	public boolean usercheck(User user) {
		
		return userrepo.findByUserName(user.getUser_name()).isPresent();
	}
	@Override
	public List<User> getAll() {
		return userrepo.findAll();
	}
	@Override
    public void deleteUser(int userId) {
        Optional<User> userOptional = userrepo.findById(userId);
        userOptional.ifPresentOrElse(
            user -> userrepo.delete(user),
            () -> {
                throw new NoSuchElementException("User not found with ID: " + userId);
               
            }
        );
    }
	@Override
     public Object updateUser(int  userId, User updatedUserData) {
    	User user = userrepo.findById(userId)
                .orElseThrow(NoSuchElementException::new);

        // Update only the fields that are present in the updatedUserData
        if (updatedUserData.getUser_name() != null) {
            user.setUser_name(updatedUserData.getUser_name());
        }
        if (updatedUserData.getUser_email()!= null) {
            user.setUser_email(updatedUserData.getUser_email());
        }
        if(updatedUserData.getUser_pass()!=null) {
        	user.setUser_pass(updatedUserData.getUser_pass());
        }
        if(updatedUserData.getUser_contact()!=null){
        	user.setUser_contact(updatedUserData.getUser_contact());
        }  
      
     
        return userrepo.save(user);
    }
	@Override
	public Object usersearch(String user_name) {
		
		return userrepo.findByUserName(user_name);
	}
	@Override
	public boolean verifycredential(String user_name, String user_pass) {
		System.out.println(user_name+" "+user_pass);
		boolean check=userrepo.findByUserNameAndUserPass(user_name, user_pass).isPresent();
        

		return check;
		
	}
	@Override
	public Object userSearchId(int user_id) {
		
		return userrepo.findById(user_id);
	}
 
}
