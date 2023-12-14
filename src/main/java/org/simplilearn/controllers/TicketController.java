package org.simplilearn.controllers;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.simplilearn.entities.ManagerPinCode;
import org.simplilearn.entities.Ticket;
import org.simplilearn.entities.Users;
import org.simplilearn.repositories.ManagerPinCodeRepository;
import org.simplilearn.repositories.TicketRepository;
import org.simplilearn.repositories.UserRepository;
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
public class TicketController {
	
	          
	         private TicketRepository ticketRepo;
	         private ManagerPinCodeRepository managerPinCodeRepo;
	         private UserRepository userRepo;
	         
	         @Autowired
	         public TicketController(TicketRepository ticketRepo, ManagerPinCodeRepository managerPinCodeRepo,UserRepository userRepo) {
	        	 
	        	 this.ticketRepo=ticketRepo;
	        	 this.managerPinCodeRepo=managerPinCodeRepo;
	        
	             this.userRepo=userRepo;
	         }
	         
	         //Creating ticket
	         @PostMapping("/createTicket/{userid}")
	         public String createTicket(@RequestBody Ticket ticket,@PathVariable("userid") int userid ,HttpSession session) {
	        	 
	              System.out.println("Inside create Ticket");     
	            // Users custUser=(Users)session.getAttribute("cuser");
	              //System.out.println("Name:"+ custUser.getUsername());
	              
	              if(ticket.getFullname()==null) {
	            	  return "Please enter Fullname";
	              }
	              else if(ticket.getAddress()==null) {
	            	  
	            	  return "Please enter Address";
	            	  
	              }
	              else if(ticket.getPincode()==null) {
	            	  
	            	  return "Please enter pincode";
	         }
	         else if  	(  ticket.getPhoneNo()==null) {
	        	 
	        	     return "Please enter Phone No.";
	        	     
	         }
	         else if (ticket.getTypeOfProblem()==null) {
	                 return "Please enter Type of Problem";
	         
	         }
	         else {
	              
	              Users custUser=userRepo.findById(userid).get();
	        	 ticket.setCustomer(custUser);
	        	 ticket.setStatus("RAISED");
	        	 ticketRepo.save(ticket);
	        	 
	        	 return "Your ticket is generated successfully. Your ticket no is "+ticket.getTicket_id();
	         }
	         }
	          
	         //Viewing tickets by customer
	         @GetMapping("/viewTicket/{username}")
	         public List<Ticket> viewTickets(@PathVariable("username") String username){
	        	 
	             //  Users custUser=(Users)session.getAttribute("cuser");
	               
	        	 Users custUser=userRepo.findByUsernm(username);
	        	 
	        	 if(custUser.getRole().getRoleName().equals("Admin")) {
	        		 return ticketRepo.findAll();
	        	 }
	        	 else {
	               return ticketRepo.findTicketsBycustomer(custUser.getUserid());
	        	 }
	               
	         }
	         
	         
	         @PostMapping("/submitfeedback/{ticket_id}/{feedback}")
	          public Ticket submitFeedback(@PathVariable("ticket_id") int ticketId , @PathVariable("feedback") String feedback) {

	        	Ticket ticket=ticketRepo.findById(ticketId).get();
	        	
	        	ticket.setFeedback(feedback);
	        	
	        	ticketRepo.save(ticket);
	        	
	        	//return "Feedback submitted successfully";
	        	 return ticket;
	         }	 
	         
	         @GetMapping("/searchTicket/{ticket_id}")
	         public Ticket searchTicket(@PathVariable("ticket_id") int ticket_id) {
	        	
	        	 //Ticket ticket=new Ticket();
	        	 
	        	 Ticket ticket= ticketRepo.findByTicketId(ticket_id);
	        	 //}
	        	 //catch(Exception e) {
	        	 if(ticket!=null) {return ticket;}
	        	 else {return null;}
	        	// }
	        	 //return ticket;
	         }
	         
	         @PutMapping("/updateTicket/{ticket_id}")
	         public Ticket updTicket(@PathVariable("ticket_id") int ticket_id, @RequestBody Ticket ticket) {
	        	 
	        	 Ticket ticket1=ticketRepo.findById(ticket_id).get();
	        	 
	        	 if(ticket.getFullname()!=null) { ticket1.setFullname(ticket.getFullname());}
	        	 
	        	 if(ticket.getAddress()!=null) {ticket1.setAddress(ticket.getAddress());}
	        	 
	        	 if(ticket.getPincode()!=null) {ticket1.setPincode(ticket.getPincode());}
	        	 
	        	 if(ticket.getTypeOfProblem()!=null) {ticket1.setTypeOfProblem(ticket.getTypeOfProblem());}
	        	 
	        	 if(ticket.getDetails()!=null) {ticket1.setDetails(ticket.getDetails());}
	        	 
	        	 if(ticket.getPhoneNo()!=null) {ticket1.setPhoneNo(ticket.getPhoneNo());}
	        	 
	        	 
	        	 ticketRepo.save(ticket1);
	        	 
	        	 return ticket1;
	        
	        
	        
	        }
	        	 
	        
	         
	         
	         @DeleteMapping("/deleteTicket/{ticket_id}")
	         public List<Ticket> delTicket(@PathVariable("ticket_id") int ticket_id) {
	        	 
	        	  ticketRepo.deleteById(ticket_id);
	        	  return ticketRepo.findAll();
	        	 
	         }
	         
	         
	         @GetMapping("/viewTicketsByManager")
	         public List<Ticket> viewforManager(){
                        
	        	 return ticketRepo.findAll();
	        	 
	         }
	         
	         
	         @GetMapping("/CheckassigntoEngineer/{ticket_id}/{username}")
	         public List<Users> assignTicket(@PathVariable("ticket_id") int ticketId,@PathVariable("username") String username ) {
	        	 
                   // Users manager=(Users)session.getAttribute("cuser");
	        	 
	        	 Users  manager=userRepo.findByUsernm(username);
	        	 
	        	 //If the user is Admin then return all the engrs
                 if(manager.getRole().getRoleName().equals("Admin")) {
                	 return userRepo.findEngineer();
                 }
                    ManagerPinCode mpincode=managerPinCodeRepo.findByManagerId(manager.getUserid());
                    String managerPincode=mpincode.getPinCode();
                    
                    Ticket ticket=ticketRepo.findById(ticketId).get();
                     
                    System.out.println("Ticket Pincode:"+ ticket.getPincode());
                    System.out.println("Manager Pin Code:"+ managerPincode);
                    
                    if(ticket.getPincode().equals(managerPincode)) {
                            System.out.println("Inside if of managerPincode equal to ticket pincode");    	
                    /*	Random rand=new Random();
                    	User engineer=userRepo.findEngineer().get(rand.nextInt(userRepo.findEngineer().size()));
                    	ticket.setEngineer(engineer);
                    */
                        //session.setAttribute("ticket_Id",ticketId);
                    	return userRepo.findEngineer();
                    
                    }
                    else 
                     System.out.println("inside else of managerPincode equal to ticket pincode");
                    
                    	return null; 
	        	 
	         }
	         
               
                
	         @PostMapping("/assignTicketToEngineer/{engnrid}/{ticket_id}")
	         public Users assignToEngineer(@PathVariable("engnrid") int engnrId ,@PathVariable("ticket_id") int ticketId) {
	        	 
	        	 
	                        Ticket ticket=ticketRepo.findById(ticketId).get();
	                        Users engineer=userRepo.findById(engnrId).get();
	                        ticket.setEngineer(engineer);
	                        ticketRepo.save(ticket);
	                        return engineer;
	        	 
	        	 
	        	 
	         }
	         
	         
	         
	         @GetMapping("/viewTicketsByEngineer/{engr_id}")
	         public List<Ticket> viewTicketsEngnr(@PathVariable("engr_id") int engr_id){
	        	 
	             	 //Users engnr=(Users)session.getAttribute("cuser");
	        	 Users user=userRepo.findById(engr_id).get();
	        	 if(user.getRole().getRoleName().equals("Admin")) {
	        		 
	        		 return ticketRepo.findAll();
	        	 }
	        	 else {
	        	// Users engr=userRepo.findByUse;
	        	 return ticketRepo.findByEnginr(engr_id);
	        	 
	        	 } 
	         }
	         
               
	         
	         @PostMapping("/assignStatus/{ticket_id}/{status}")
	         public Ticket assignStatus(@PathVariable("ticket_id") int ticketId, @PathVariable("status") String status) {
	        	 
	        	Ticket ticket=ticketRepo.findById(ticketId).get(); 
	        	ticket.setStatus(status);
	        	ticketRepo.save(ticket);
	        	//return "Status assigned as "+ status;
	        	return ticket;
	         }
	         
	         
	         //Set Remark by Engineer
	         @PostMapping("/setRemark/{ticket_id}/{remark}")
	         public Ticket setRemark(@PathVariable("ticket_id") int ticketId, @PathVariable("remark") String remark) {
	        	 
	        	Ticket ticket=ticketRepo.findById(ticketId).get();
	        	ticket.setRemark(remark);
	        	ticket.setStatus("ESCALATED");
	             ticketRepo.save(ticket);	
	        	 
	             return ticket;	 
	        	 
	        	 
	         }
            
	         
	         //View complaints based on individual customer
	         @GetMapping("/viewComplaintsBasedonCustomer/{cust_id}")
	         public List<Ticket> viewTicketsBasedonCust(@PathVariable("cust_id") int cust_id){

	                  // Users cust=userRepo.findById(cust_id).get();;
	                   return ticketRepo.findTicketsBycustomer(cust_id);
	         
	         
	         }
	         
	         
	         //Register Complaint if any if status is RESOLVED
	         @PostMapping("/registerComplaint/{ticketId}/{problem}/{details}")
	         public Ticket registerComplaint(@PathVariable("ticketId") int ticket_id, @PathVariable("problem") String problem ,@PathVariable("details") String details) {
	        	 
                            Ticket ticket=ticketRepo.findById(ticket_id).get();
                            
                            Ticket ticket1=new Ticket();
                            
                            ticket1.setTypeOfProblem(problem);
                            ticket1.setDetails(details);
                            ticket1.setStatus("RAISED");
                            ticket1.setCustomer(ticket.getCustomer());
                            ticket1.setFullname(ticket.getFullname());
                            ticket1.setAddress(ticket.getAddress());
                            ticket1.setPincode(ticket.getPincode());
                            ticket1.setPhoneNo(ticket.getPhoneNo());
                            
                            ticketRepo.save(ticket1);
                          //  return "Your complaint is registered successfully.The ticket id is "+ ticket1.getTicket_id();
	        	            return ticket1;
	         }


                      
	         @GetMapping("/getActiveTickets")
	         public List<Ticket> getactiveTickets(){
	        	 
	        	 return ticketRepo.findTicketByStatus("RAISED");
	         }
	         
	         
	         @GetMapping("/getPinCodes")
	         public List <Map<String,String>> getPinCodes() {
	        	 
	        	 return managerPinCodeRepo.findPinCodes();
	         }

             
	         @PostMapping("/setPincode/{username}/{pincode}")
	         public ManagerPinCode setPinCode(@PathVariable("username") String managername,@PathVariable("pincode") String pincode) {
	        	 
	        	 Users user=userRepo.findByUsernm(managername);
	        	 ManagerPinCode mpincode=managerPinCodeRepo.findByManagerId(user.getUserid());
	        	 
	        	 if(mpincode==null) {
	        		 
	        		 ManagerPinCode mp=new ManagerPinCode();
	        		 mp.setPinCode(pincode);
	        		 mp.setManager(user);
	        		 managerPinCodeRepo.save(mp);
	        		 return mp;
	        	 }
	        	 else {
	        		 if(pincode!=null) {
	        		     
	        			 mpincode.setPinCode(pincode);
	        			 managerPinCodeRepo.save(mpincode);
	        			 //return mpincode;
	        		 }
	        		 return mpincode;
	        	 }
	        	 
	         }


	         
	         
	         


}