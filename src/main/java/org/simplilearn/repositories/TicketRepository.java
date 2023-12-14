package org.simplilearn.repositories;

import java.util.List;

import org.simplilearn.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.simplilearn.entities.Users;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer>{
	
	
	@Query(value="select * from Ticket t where t.cust_id=?1",nativeQuery=true)
	public List<Ticket> findTicketsBycustomer(int cust_id);
	
	
	@Query(value="select * from ticket t where t.engr_id=?1",nativeQuery=true)
	public List<Ticket> findByEnginr(int engr_id);
	
	@Query(value="select * from ticket t where t.status=?1",nativeQuery=true)
	public List<Ticket> findTicketByStatus(String status);
	
	@Query(value="select * from ticket t where t.ticket_id=?1",nativeQuery=true)
	public Ticket findByTicketId(int id);
	

}
