package org.simplilearn.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.simplilearn.entities.Role;
import org.simplilearn.entities.Users;
import org.simplilearn.repositories.RoleRepository;
import org.simplilearn.repositories.UserRepository;
import org.simplilearn.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
public class UserController {
	
	
	private UserRepository userRepo;
	private RoleRepository roleRepo;
	//private UserService userService;
	
	@Autowired
	public UserController(UserRepository userRepo, RoleRepository roleRepo,UserService userService) {
		this.userRepo=userRepo;
		this.roleRepo=roleRepo;
		//this.userService=userService;
	}
	
	
	/*@PostConstruct
	public void initRoles() {
		userService.initRoles();
	}*/
	  
     
	  @PostMapping("/register")
	  public String Signup(@RequestBody Users user) {
		  
	/*	  boolean admin=false;
		  for (Role role:user.getRole()) {
			  
		         if (role.getRoleName().equals("Admin")){
		            
		        	 admin=true;
		        	 
		         }
		  } */
	      
		  if(user.getUsername().equals("")) {
			  return "Enter a valid Username";
		  }
		  
		  //String uname=userRepo.findUserName(user.getUsername());
		 if(userRepo.findAllUsernm().contains(user.getUsername())) {
			  
			  
			  return "The username is already in use. Try another username";

		  } 
		  
		  else if( user.getRole().getRoleName().equals("Admin") && /*roleRepo.findAllRoles().contains("Admin")*/ roleRepo.findAllRoles().contains("Admin")){
	
			  return "There  can only be 1 Admin. No other Admin is allowed";
		         }
			
		 
		  
		  else {
			/*  //List<Role> role1=new ArrayList<Role>(user.getRole());
			 // Role role=roleRepo.findById(role1.get(0).getRoleName()).get();
			  //System.out.println("Role:" + role1.get(0).getRoleName());
			  
			  //user.setRole(new Role("Admin"));
			  //Set<Role> roles=new HashSet<>();
			  //roles.add(role);
			 // user.role.addRole(role);*/
			  Role role=roleRepo.findByName(user.getRole().getRoleName());
			  user.setRole(role);
			userRepo.save(user);  
		  }
		  return "Hi " + user.getUsername() + " you are registered successfully";
		 // return user;
		  }
	  
	  
	  
	  
	  @GetMapping("/login/{usernm}/{pwd}")
	  public Users login(@PathVariable("usernm") String usernm , @PathVariable("pwd") String pwd,HttpSession session) {
		
		       Users user=userRepo.findByUsernmAndPwd(usernm,pwd);
		//       session.setAttribute("cuser", user);	
		      // System.out.println("User :"+ user.getUsername());
		       //http.sessionManagement().sessionFixation().none();
            		//if(user==null) {
            			//System.out.println("User :"+ user);
            			return user;
            		//}
                 
            	/*	else {
            			
            			//session.setAttribute("cuser", user);
            			return user;
            		}*/
            		
           /* 	if (user.getRole().getRoleName().equals("Customer")){
            		
                        			
            			return "Customer";
            			
            			
            		}
		  
            		else if (user.getRole().getRoleName().equals("Engineer")) {
            		//	session.setAttribute("cuser",user);
            			return "Engineer";
            		}
            		
            		else if (user.getRole().getRoleName().equals("Manager"))
            		{
            		//    session.setAttribute("cuser",user);	
            			return "Manager";
            		}
            		
            		else if (user.getRole().getRoleName().equals("Admin")) {
            		//     session.setAttribute()	
            			return "Admin";
            			
            		}
            		else return "1";
*/            		
	  }
	  

	  
	  
	       @GetMapping("/logout")
	       public String logout() {
            
            return "logout";	    	   
	    	   
	       }
	       
	       
	       //Delete User
	       @DeleteMapping("/delete/{usernm}")
	       public String deleteUser(@PathVariable("usernm") String usernm) {
	    	   
	    	   Users user=userRepo.findByUsernm(usernm);
	    	   if (user==null) {
	    		return "No user with username "+ usernm + "exists";   
	    	   }
	    	   else {
	    		   
	    		   userRepo.deleteById(user.getUserid());
	    		   return "User Deleted successfully";
	    	   }
	    	   
	       }
	       
	       
	       //Update User
	       
	       @PutMapping("/updateUser/{userid}")
	       public Users UpdateUser(@PathVariable("userid") int userid ,@RequestBody Users user) {
                
	    	   Users user1=userRepo.findById(userid).get();
	    	   //Users user1=userRepo.findByUsernm(user.getUsername());
	    	   
	    	 //  if (user1!=null){
	    		 
	    		   if(user.getUsername()!=null) {
	    			   user1.setUsername(user.getUsername());
	    		   }
	    		   if(user.getPassword()!=null) {
	    			   user1.setPassword(user.getPassword());
	    		   }
	    		   if(user.getRole().getRoleName()!=null) {
	    			   
	    			   Role role=roleRepo.findByName(user.getRole().getRoleName());
	    		        user1.setRole(role);
	    		   }
	    		userRepo.save(user1);
	    		//return "User updated successfully";
	    		return user1;
	//    	   }
	    	   
	    	   /*else {
	    		
	    		   return "User with id" + user.getUserid()+ "does not exist in database";
	    		   
	    	   }*/
	    	   
	       }
	       
             
	       @GetMapping("/searchUser/{username}")
	       public Users searchUser(@PathVariable String username) {
            
	    	   
	    	   Users user=userRepo.findByUsernm(username);
	    	
	    	   if(user!=null) {
	    		   return user;
	    	   }
	    	   else {
	    		   
	    		return null;   
	    	   }
	       }
	       
}
