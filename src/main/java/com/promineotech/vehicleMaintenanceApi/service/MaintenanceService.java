package com.promineotech.vehicleMaintenanceApi.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.vehicleMaintenanceApi.entity.Customer;
import com.promineotech.vehicleMaintenanceApi.entity.Maintenance;
import com.promineotech.vehicleMaintenanceApi.entity.Vehicle;
import com.promineotech.vehicleMaintenanceApi.entity.ShopTask;
import com.promineotech.vehicleMaintenanceApi.repository.CustomerRepository;
import com.promineotech.vehicleMaintenanceApi.repository.MaintenanceRepository;
import com.promineotech.vehicleMaintenanceApi.repository.VehicleRepository;
import com.promineotech.vehicleMaintenanceApi.util.MaintenanceStatus;

@Service
public class MaintenanceService {
	
	private static final Logger logger = LogManager.getLogger(CustomerService.class);
	
	@Autowired
	MaintenanceRepository repo;
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	VehicleRepository vehicleRepo;
	
	public Maintenance generateNewMaintenance(Set<Long> vehicleIds, Long customerId) throws Exception {
		try {
			Customer customer = customerRepo.findOne(customerId);
			Maintenance maintenance = generateNewMaintenance(vehicleIds, customer); 
			return repo.save(maintenance);
		} catch (Exception e) {
			logger.error("Exception occured while trying to create a new service for customer: " + customerId, e);
			throw e;
		}
	}
	
	public Maintenance cancelSchedule(Long maintenanceId) throws Exception {
		try {
			Maintenance maintenance = repo.findOne(maintenanceId);
			maintenance.setStatus(MaintenanceStatus.CANCELED);
			return repo.save(maintenance);
		} catch (Exception e) {
			logger.error("Exception occured while trying to delete maintenance: " + maintenanceId, e);
			throw new Exception("Unable to update schedule.");
		}
	}
	
	public Maintenance completeMaintenance(Long maintenanceId) throws Exception {
		try {
			Maintenance maintenance = repo.findOne(maintenanceId);
			maintenance.setStatus(MaintenanceStatus.COMPLETE);
			return repo.save(maintenance);
		} catch (Exception e) {
			logger.error("Exception occured while trying to complete schedule: " + maintenanceId, e);
			throw new Exception("Unable to update schedule.");
		}
	}
	private Maintenance generateNewMaintenance(Set<Long> vehicleIds, Customer customer) {
		Maintenance maintenance = new Maintenance();
		maintenance.setVehicles(convertToVehicleSet(vehicleRepo.findAll(vehicleIds)));
		maintenance.setCustomer(customer);
		maintenance.setStatus(MaintenanceStatus.SCHEDULED);
		return maintenance;
	}

	private Set<Vehicle> convertToVehicleSet(Iterable<Vehicle> iterable) {
		Set<Vehicle> set = new HashSet<Vehicle>();
		for (Vehicle vehicle : iterable) {
			set.add(vehicle);
		}
		return set;
	}
	
	public Maintenance updateSchedule(Long id, Maintenance maintenance) {
		Maintenance foundMaintenance = repo.findOne(id);
		if (foundMaintenance != null) {
			foundMaintenance.setShopTasks(maintenance.getShopTasks());
			repo.save(foundMaintenance);
		}
		return foundMaintenance;
	}
	
	public Iterable<Maintenance> getMaintenanceByCustomerId(Long id) {
		return repo.findMaintenanceByCustomerId(id);
	}

}
