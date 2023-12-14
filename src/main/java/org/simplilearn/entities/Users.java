package org.simplilearn.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;

@Entity
@Table(name="users")
@AllArgsConstructor
public class Users {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userid;
	
	private String username;
	
	private String password;
	
	
	
	//private String fullName;
	
	//private String address;
	
	//private String phoneNo;
	
	
	//private String pincode;
	
	//private String pincodeAssigned;
	
	@ManyToOne //(cascade=CascadeType.ALL)
	@JoinColumn(name="role_id")
	private Role role;
    
/*	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinTable(name="User_Role",
	joinColumns= {
			@JoinColumn(name="User_Id")
	},
	
			inverseJoinColumns= {
					@JoinColumn(name="Role_id")
			})
	private Set<Role> role;
	*/
	
	public Users() {}

    
	//Getters And Setters
	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
	} 


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


/*	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	} */


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


/*	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhoneNo() {
		return phoneNo;
	}


	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	public String getPincode() {
		return pincode;
	}


	public void setPincode(String pincode) {
		this.pincode = pincode;
	}


	public String getPincodeAssigned() {
		return pincodeAssigned;
	}


	public void setPincodeAssigned(String pincodeAssigned) {
		this.pincodeAssigned = pincodeAssigned;
	}

    */
	
	
    
	
        



}
