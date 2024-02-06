package com.MrFix30.Controller;

import java.util.List;
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
       public ResponseEntity<String> postcomplaints(@RequestBody User users){
    	   try {
    	       userservice.userregister(users);
    	      
	          return ResponseEntity.ok("User Registered Succesfully");
    	   }catch(Exception e) {
    		   return ResponseEntity.status(HttpStatus.CONFLICT).body("Data may be already exists");
    	   }
       }
       
       @PatchMapping("/{userId}")
       public ResponseEntity<String> updateUser(@PathVariable int userId, @RequestBody User updatedUserData) {
           try {
               userservice.updateUser(userId, updatedUserData);
               return ResponseEntity.ok("User updated successfully");
           } catch (NoSuchElementException e) {
               return ResponseEntity.notFound().build();
           }
       }
    
}
