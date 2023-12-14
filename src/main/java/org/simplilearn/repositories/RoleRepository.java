package org.simplilearn.repositories;

import java.util.List;
import java.util.Set;

import org.simplilearn.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer>{

	
	@Query(value="select r.roleName from org.simplilearn.entities.Role r")
	public List<String>findRoleName();
	
	
	
	@Query(value="select * from role r where r.role_name=?1",nativeQuery=true)
	public Role findByName(String roleName);
	
	
	//@Query(value="select r.Role_id from org.simplilearn.entities User_Role r")
    //public Set<String> findAllRoles(); 
	
	@Query(value="select r.roleName from org.simplilearn.entities.Role r where r.roleId in (select u.role.roleId from org.simplilearn.entities.Users u) ")
	public List<String> findAllRoles();

}
