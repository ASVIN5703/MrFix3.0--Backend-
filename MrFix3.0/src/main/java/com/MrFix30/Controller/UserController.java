package com.MrFix30.Controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MrFix30.Model.User;
import com.MrFix30.Service.UserService;

@CrossOrigin

@RestController
public class UserController {
	   @Autowired
	   private UserService userservice;
       @GetMapping("/viewusers")
       public List<User>getAllUsers(){
    	   System.out.print(userservice.getAll());
    	   return userservice.getAll();
       }
       @DeleteMapping("/delete/user/{userId}")
       public ResponseEntity<String> deleteUser(@PathVariable int userId) {
           try {
               userservice.deleteUser(userId);
               return ResponseEntity.ok("User deleted successfully");
           } catch (Exception e) {
               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user");
           }
       } //
       @PostMapping("/user/post")
       public ResponseEntity<String> postcomplaints(@RequestBody Map<String,String> users){
    	   try {
    		   User userdata=new User();
    		   userdata.setUser_contact(users.get("contact"));
    		   userdata.setUser_email(users.get("email"));
    		   userdata.setUser_name(users.get("name"));
    		   userdata.setUser_pass(users.get("pass"));
    	       userservice.userregister(userdata);
    	      
	          return ResponseEntity.ok("User Registered Succesfully");
    	   }catch(Exception e) {
    		   return ResponseEntity.status(HttpStatus.CONFLICT).body("Data may be already exists");
    	   }
       }
       @GetMapping("/searchusers")
       public ResponseEntity<Object> searchUser(@RequestParam ("term")String user_name){
    	    try {
    	    	  
    	    	  return ResponseEntity.ok(userservice.usersearch(user_name));
    	    }catch(NoSuchElementException e) {
    	    	 return ResponseEntity.notFound().build();
    	    }
    	   
       }
       @GetMapping("/user/{user_id}")
       public ResponseEntity<Object> searchByUserId(@PathVariable int user_id){
    	   try {
    		   System.out.println("Entered the user search by Id");
    		   return ResponseEntity.ok(userservice.userSearchId(user_id));
    	   }catch(NoSuchElementException e) {
    		   return ResponseEntity.notFound().build();
    	   }
       }
       @PatchMapping("/{userId}")
       public ResponseEntity<Object> updateUser(@PathVariable int userId, @RequestBody User updatedUserData) {
           try {
        	   System.out.println(updatedUserData);
              
               return ResponseEntity.ok(userservice.updateUser(userId, updatedUserData));
           } catch (NoSuchElementException e) {
               return ResponseEntity.notFound().build();
           }
       }
    
}
