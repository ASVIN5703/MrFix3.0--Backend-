package com.MrFix30.Service;

import java.util.List;
import java.util.Optional;

import com.MrFix30.Model.Admin;
import com.MrFix30.Model.User;

public interface UserService {
	String userregister(User user);

	boolean usercheck(User user);

	List<User> getAll();

	void deleteUser(int userId);

	Object updateUser(int userId, User updatedUserData);

	Object usersearch(String user_name);

	boolean verifycredential(String user_name, String user_pass);

	Object userSearchId(int user_id);

}
