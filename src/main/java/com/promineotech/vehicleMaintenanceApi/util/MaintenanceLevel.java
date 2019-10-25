package com.promineotech.vehicleMaintenanceApi.util;

public enum MaintenanceLevel {
	
	Bronze(.30),
	Silver(.40),
	Gold(.50);
	
	private Double coverage;
	
	MaintenanceLevel(Double coverage) {
		this.coverage = coverage;
	}
	
	public double getCoverage() {
		return coverage;
	}
	
}
