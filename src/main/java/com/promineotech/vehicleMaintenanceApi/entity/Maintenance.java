package com.promineotech.vehicleMaintenanceApi.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.promineotech.vehicleMaintenanceApi.util.MaintenanceStatus;

@Entity
public class Maintenance {
	
	private Long id;
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
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	public List<ShopTask> getShopTasks() {
		return shopTasks;
	}

	public void setShopTasks(List<ShopTask> shopTasks) {
		this.shopTasks = shopTasks;
	}
	
	@ManyToMany(mappedBy = "maintenances")
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