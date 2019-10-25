package com.promineotech.vehicleMaintenanceApi.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.promineotech.vehicleMaintenanceApi.util.ScheduleStatus;

@Entity
public class Schedule {
	
	private Long id;
	private String oneMonth;
	private String threeMonth;
	private String sixMonth;
	private double invoiceAmmount;
	private ScheduleStatus status;
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
	
	public String getOneMonth() {
		return oneMonth;
	}
	
	public void setOneMonth(String oneMonth) {
		this.oneMonth = oneMonth;
	}
	
	public String getThreeMonth() {
		return threeMonth;
	}
	
	public void setThreeMonth(String threeMonth) {
		this.threeMonth = threeMonth;
	}
	
	public String getSixMonth() {
		return sixMonth;
	}
	
	public void setSixMonth(String sixMonth) {
		this.sixMonth = sixMonth;
	}
	
	public double getInvoiceAmmount() {
		return invoiceAmmount;
	}

	public void setInvoiceAmmount(double invoiceAmmount) {
		this.invoiceAmmount = invoiceAmmount;
	}
	
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

	public ScheduleStatus getStatus() {
		return status;
	}

	public void setStatus(ScheduleStatus status) {
		this.status = status;
	}
	
}
