package org.simplilearn.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class Ticket {
	
	
	     @Id
	     @GeneratedValue(strategy=GenerationType.IDENTITY)
	     private int ticket_id;
	     
	     private String typeOfProblem;
	     
	     private String details;
	     
	     private String fullname;
	     
	     private String address;
	     
	     private String pincode;
	     
	     private String phoneNo;
         
	     
	     private String status;
	     
	     private String remark;
	     
	     private String feedback;
	
	      @ManyToOne
	      @JoinColumn(name="cust_id")
	      private Users customer;
	      
           @ManyToOne
           @JoinColumn(name="engr_id")
	      private Users engineer;
	      
	      public Ticket() {}

          //Getters and Setters         

		public int getTicket_id() {
			return ticket_id;
		}



		public void setTicket_id(int ticket_id) {
			this.ticket_id = ticket_id;
		}



		public String getTypeOfProblem() {
			return typeOfProblem;
		}



		public void setTypeOfProblem(String typeOfProblem) {
			this.typeOfProblem = typeOfProblem;
		}



		public String getDetails() {
			return details;
		}



		public void setDetails(String details) {
			this.details = details;
		}



		public String getStatus() {
			return status;
		}



		public void setStatus(String status) {
			this.status = status;
		}



		public String getRemark() {
			return remark;
		}



		public void setRemark(String remark) {
			this.remark = remark;
		}



		public Users getCustomer() {
			return customer;
		}



		public void setCustomer(Users customer) {
			this.customer = customer;
		}

		public String getFullname() {
			return fullname;
		}

		public void setFullname(String fullname) {
			this.fullname = fullname;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getPincode() {
			return pincode;
		}

		public void setPincode(String pincode) {
			this.pincode = pincode;
		}

		public String getPhoneNo() {
			return phoneNo;
		}

		public void setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
		}

		public String getFeedback() {
			return feedback;
		}

		public void setFeedback(String feedback) {
			this.feedback = feedback;
		}

		public Users getEngineer() {
			return engineer;
		}

		public void setEngineer(Users engineer) {
			this.engineer = engineer;
		}
	      
	      
         	      

}
