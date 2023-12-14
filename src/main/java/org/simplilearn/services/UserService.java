package org.simplilearn.services;

import org.simplilearn.entities.Role;
import org.simplilearn.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	   
	private RoleRepository roleRepo;
	
	@Autowired
	public UserService(RoleRepository roleRepo) {
		this.roleRepo=roleRepo;
	}
	
	
	public  void initRoles() {
		
		Role Admin= new Role();
		Admin.setRoleName("Admin");
		//Admin.setRoleDescription("Admin Role");
		roleRepo.save(Admin);
		
		
		Role customer=new Role();
		customer.setRoleName("Customer");
		//customer.setRoleDescription("Customer Role");
		roleRepo.save(customer);
		
		
		Role engineer=new Role();
		engineer.setRoleName("Engineer");
		//engineer.setRoleDescription("Engineer Role");
		roleRepo.save(engineer);
		
		
		Role Manager=new Role();
		Manager.setRoleName("Manager");
		//Manager.setRoleDescription("Manager Role");
		roleRepo.save(Manager);
		
	}
		
	}
	
	
	
	


