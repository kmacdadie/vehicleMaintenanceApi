package com.promineotech.vehicleMaintenanceApi.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ShopTask {
	
	private Long id;
	private List<String> shopTask = new ArrayList<String>();
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	public List<String> getShopTask() {
		return shopTask;
	}

	public void setShopTask(List<String> shopTask) {
		this.shopTask = shopTask;
	}

}
