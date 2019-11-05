package com.promineotech.vehicleMaintenanceApi.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Vehicle {
	
	private Long id ;
	private int year;
	private String make;
	private String model;
	private Long mileage;
	
	@JsonIgnore
	private Set<Maintenance> maintenances;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getMake() {
		return make;
	}
	
	public void setMake(String make) {
		this.make = make;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public Long getMileage() {
		return mileage;
	}
	
	public void setMileage(Long mileage) {
		this.mileage = mileage;
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "vehicle_maintenance",
			joinColumns = @JoinColumn(name = "maintenanceId", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "vehicleId", referencedColumnName = "id"))
	public Set<Maintenance> getMaintenances() {
		return maintenances;
	}

	public void setMaintenances(Set<Maintenance> maintenances) {
		this.maintenances = maintenances;
	}
	
}