package com.promineotech.vehicleMaintenanceApi.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.promineotech.vehicleMaintenanceApi.util.MaintenanceStatus;

@Entity
public class Maintenance {
	
	private Long id;
	private String discription;
	private double invoiceAmmount;
	private List<ShopTask> shopTasks;
	private MaintenanceStatus status;
	private Set<Vehicle> vehicles;
	
	@JsonIgnore
	private Customer customer;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public List<ShopTask> getShopTasks() {
		return shopTasks;
	}

	public void setShopTasks(List<ShopTask> shopTasks) {
		this.shopTasks = shopTasks;
	}
	
	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}
	
	public double getInvoiceAmmount() {
		return invoiceAmmount;
	}

	public void setInvoiceAmmount(double invoiceAmmount) {
		this.invoiceAmmount = invoiceAmmount;
	}
	
//	this @ManyToMany  throws an error when launching spring-boot for some reason.
	@ManyToMany(mappedBy = "vehicles")
	public Set<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
	@ManyToOne
	@JoinColumn(name = "customerId")
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public MaintenanceStatus getStatus() {
		return status;
	}

	public void setStatus(MaintenanceStatus status) {
		this.status = status;
	}

}
