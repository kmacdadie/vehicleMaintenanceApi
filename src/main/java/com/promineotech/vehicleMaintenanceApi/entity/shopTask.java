package com.promineotech.vehicleMaintenanceApi.entity;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ShopTask {
	
	private Long id;
	private String discription;
	private double price;
	private LocalDate date = LocalDate.now();
	
	@JsonIgnore
	private Maintenance maintenance;
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getdiscription() {
		return discription;
	}

	public void setdiscription(String discription) {
		this.discription = discription;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	//@OneToOne(mappedBy = "shopTask")
	public Maintenance getMaintenance() {
		return maintenance;
	}

	public void setMaintenance(Maintenance maintenance) {
		this.maintenance = maintenance;
	}	

}
