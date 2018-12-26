package com.assignment.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.model.User;
import com.assignment.service.UserService;

@RestController
public class UserController {

	@Autowired
	   private UserService userService;

	   /*---Add new User---*/
	   @PostMapping("/user")
	   public ResponseEntity<?> save(@RequestBody User user) throws SQLIntegrityConstraintViolationException {
		   if(user.getStatus().equalsIgnoreCase(User.ACTIVATED) || user.getStatus().equalsIgnoreCase(User.DEACTIVATED)){
			   userService.save(user);
			   return ResponseEntity.ok().body("New User has been saved with ID:" + user.getId());
		   }else{
			   return ResponseEntity.badRequest().body("New User Save Error : Status - Activated or Deactivated");
		   }
		  
	   }

	   /*---Get a User by id---*/
	   @GetMapping("/user/{id}")
	   public ResponseEntity<?> get(@PathVariable("id") Integer id) {
		   User user = userService.get(id);
		   if(null != user){
			   return ResponseEntity.ok().body(user);
		   }else{
			   return ResponseEntity.ok().body("No User Found : Id - " +id);
		   }
	      
	   }

	   /*---get all users---*/
	   @GetMapping("/user")
	   public ResponseEntity<List<User>> list() {
	      List<User> users = userService.list();
	      return ResponseEntity.ok().body(users);
	   }

	   /*---Update a user by id---*/
	   @PutMapping("/user/{id}")
	   public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody User user) {
		   if(user.getStatus().equalsIgnoreCase(User.ACTIVATED) || user.getStatus().equalsIgnoreCase(User.DEACTIVATED)){
			   userService.update(id, user);
			   return ResponseEntity.ok().body("User has been updated successfully.");
		   }else{
			   return ResponseEntity.badRequest().body("User Update Error : Status - Activated or Deactivated");
		   }
	     
	   }

	   /*---Delete a User by id---*/
	   @DeleteMapping("/user/{id}")
	   public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
	      userService.delete(id);
	      return ResponseEntity.ok().body("User has been deleted successfully.");
	   }
	   
	   @ExceptionHandler({Exception.class})
	    public ResponseEntity<?> handleException(){
		   return ResponseEntity.badRequest().body("Something went wrong, Please Check input.");
	   }
}
