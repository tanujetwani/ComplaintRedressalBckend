package org.simplilearn.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class ManagerPinCode {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
		
	private String pinCode;
	
	@OneToOne
	@JoinColumn(name="manager_id")
	private Users Manager;
	
	
	public ManagerPinCode() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Users getManager() {
		return Manager;
	}

	public void setManager(Users manager) {
		Manager = manager;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	
	

}
