package com.promineotech.vehicleMaintenanceApi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.vehicleMaintenanceApi.entity.Vehicle;
import com.promineotech.vehicleMaintenanceApi.repository.VehicleRepository;

@Service
public class VehicleService {
	
	private static final Logger logger = LogManager.getLogger(CustomerService.class);
	
	@Autowired
	VehicleRepository repo;
	
	public Vehicle getVehicleById(Long id) throws Exception {
		try {
			return repo.findOne(id);
		} catch (Exception e) {
			logger.error("Exception occured while trying to retrieve vehicle: " + id, e);
			throw e;
		}
	}

	public Iterable<Vehicle> getVehicles() {
		return repo.findAll();
	}
	
	public Vehicle addVehicle(Vehicle vehicle) {
		return repo.save(vehicle);
	}
	
	public Vehicle updateVehicle(Long id, Vehicle vehicle)  throws Exception {
		try {
		Vehicle foundVehicle = repo.findOne(id);
			foundVehicle.setMake(vehicle.getMake());
			foundVehicle.setModel(vehicle.getMake());
			foundVehicle.setYear(vehicle.getYear());
			foundVehicle.setMileage(vehicle.getMileage());
			return repo.save(foundVehicle);
		} catch (Exception e) {
			logger.error("Exception occured while trying to update vehicle: " + id, e);
			throw new Exception("Unable to update vehicle.");
		}
	}
	
	public void deleteVehicle(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch(Exception e) {
			logger.error("Exception occured while trying to delete customer: " + id, e);
			throw new Exception("Unable to delete customer.");
		}
	}
	
}
