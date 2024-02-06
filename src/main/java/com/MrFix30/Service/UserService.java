package com.MrFix30.Service;

import java.util.List;

import com.MrFix30.Model.Admin;
import com.MrFix30.Model.User;

public interface UserService {
   String userregister(User user);
    boolean usercheck(User user);
    List<User>getAll();
	void deleteUser(int userId);
	void updateUser(int userId, User updatedUserData);
	
}
