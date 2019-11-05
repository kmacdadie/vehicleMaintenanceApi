package com.promineotech.vehicleMaintenanceApi.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.promineotech.vehicleMaintenanceApi.util.MaintenanceLevel;

@Entity
public class Customer {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private MaintenanceLevel level;
	private Set<Maintenance> maintenances;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public MaintenanceLevel getLevel() {
		return level;
	}
	
	public void setLevel(MaintenanceLevel level) {
		this.level = level;
	}
	
	@OneToMany(mappedBy = "customer")
	public Set<Maintenance> getMaintenances() {
		return maintenances;
	}
	
	public void setMaintenances(Set<Maintenance> maintenances) {
		this.maintenances = maintenances;
	}

}
