package org.simplilearn.repositories;

import java.util.List;
import java.util.Map;

import org.simplilearn.entities.ManagerPinCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerPinCodeRepository extends JpaRepository<ManagerPinCode,Integer> {
	
	@Query(value="select * from manager_pin_code mp where mp.manager_id=?1",nativeQuery=true)
	public ManagerPinCode findByManagerId(int managerId);

	
	@Query(value=" select username,pin_code from users u inner join role r on u.role_id=r.role_id and r.role_name='Manager' left join manager_pin_code mp on mp.manager_id=u.userid",nativeQuery=true)
	public List<Map<String,String>> findPinCodes();
	
	
	
}
