package org.simplilearn.repositories;

import java.util.List;

import org.simplilearn.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer>{
	
	
	@Query(value="select u.username from org.simplilearn.entities.Users u")
	public List<String> findAllUsernm();
	
	@Query(value="select * from users u where u.username=?1 and u.password=?2", nativeQuery=true)
	public Users findByUsernmAndPwd(String usernm, String pwd);
	
	
	//@Query(value="select r.roleName from role r",nativeQuery=true)
	//public List<String>findRoleName();
	
	@Query(value="select * from users u where u.username=?1",nativeQuery=true)
	public Users findByUsernm(String usernm);
	
	@Query(value="select * from users u where u.role_id in(select r.role_id from role r where r.role_name='Engineer')",nativeQuery=true)
    public List<Users> findEngineer(); 
	
	//@Query(value="select r.roleName from org.simplilearn.entities.Role r where r.roleId in (select u.roleId from org.simplilearn.entities.Users u) ")
	//public List<String> findAllRoles();

}
