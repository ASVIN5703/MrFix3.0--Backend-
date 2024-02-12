package com.MrFix30.ServiceImpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MrFix30.Model.User;
import com.MrFix30.Repository.UserRepository;
import com.MrFix30.Service.UserService;

import CustomExceptions.DataAlreadyExists;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userrepo;
    ///adding user details
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
		// TODO Auto-generated method stub
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
                // Handle this exception based on your application's requirements
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
        // Add other fields to update

        return userrepo.save(user);
    }
	@Override
	public Object usersearch(String user_name) {
		// TODO Auto-generated method stub
		return userrepo.findByUserName(user_name);
	}
	@Override
	public boolean verifycredential(String user_name, String user_pass) {
		System.out.println(user_name+" "+user_pass);
		boolean check=userrepo.findByUserNameAndUserPass(user_name, user_pass).isPresent();
		System.out.println(check);
		return check;
		
	}
	@Override
	public Object userSearchId(int user_id) {
		
		return userrepo.findById(user_id);
	}
 
}
