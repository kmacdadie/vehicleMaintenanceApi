package com.promineotech.vehicleMaintenanceApi.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.vehicleMaintenanceApi.entity.Customer;
import com.promineotech.vehicleMaintenanceApi.entity.Schedule;
import com.promineotech.vehicleMaintenanceApi.entity.Vehicle;
import com.promineotech.vehicleMaintenanceApi.repository.CustomerRepository;
import com.promineotech.vehicleMaintenanceApi.repository.ScheduleRepository;
import com.promineotech.vehicleMaintenanceApi.repository.VehicleRepository;
import com.promineotech.vehicleMaintenanceApi.util.ScheduleStatus;

@Service
public class ScheduleService {
	
	private static final Logger logger = LogManager.getLogger(CustomerService.class);
	
	@Autowired
	ScheduleRepository repo;
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	VehicleRepository vehicleRepo;
	
	public Schedule createNewSchedule(Set<Long> vehicleIds, Long customerId) throws Exception {
		try {
			Customer customer = customerRepo.findOne(customerId);
			Schedule schedule = initializeNewSchedule(vehicleIds, customer); 
			return repo.save(schedule);
		} catch (Exception e) {
			logger.error("Exception occured while trying to create a new service for customer: " + customerId, e);
			throw e;
		}
	}
	
	public Schedule cancelSchedule(Long scheduleId) throws Exception {
		try {
			Schedule schedule = repo.findOne(scheduleId);
			schedule.setStatus(ScheduleStatus.CANCELED);
			return repo.save(schedule);
		} catch (Exception e) {
			logger.error("Exception occured while trying to delete schedule: " + scheduleId, e);
			throw new Exception("Unable to update schedule.");
		}
	}
	
	public Schedule completeSchedule(Long scheduleId) throws Exception {
		try {
			Schedule schedule = repo.findOne(scheduleId);
			schedule.setStatus(ScheduleStatus.COMPLETE);
			return repo.save(schedule);
		} catch (Exception e) {
			logger.error("Exception occured while trying to complete schedule: " + scheduleId, e);
			throw new Exception("Unable to update schedule.");
		}
	}
	private Schedule initializeNewSchedule(Set<Long> vehicleIds, Customer customer) {
		Schedule schedule = new Schedule();
		schedule.setVehicles(convertToVehicleSet(vehicleRepo.findAll(vehicleIds)));
		schedule.setCustomer(customer);
		//schedule.setInvoiceAmmount(invoiceAmmount);
		schedule.setStatus(ScheduleStatus.SCHEDULED);
		return schedule;
	}

	private Set<Vehicle> convertToVehicleSet(Iterable<Vehicle> iterable) {
		Set<Vehicle> set = new HashSet<Vehicle>();
		for (Vehicle vehicle : iterable) {
			set.add(vehicle);
		}
		return set;
	}
	
	public Schedule updateSchedule(Long id, Schedule schedule) {
		Schedule foundSchedule = repo.findOne(id);
		if (foundSchedule != null) {
			foundSchedule.setOneMonth(schedule.getOneMonth());
			foundSchedule.setThreeMonth(schedule.getThreeMonth());
			foundSchedule.setSixMonth(schedule.getSixMonth());
			repo.save(foundSchedule);
		}
		return foundSchedule;
	}
	
	public Iterable<Schedule> getSchedulesByCustomerId(Long id) {
		return repo.findSchedulesByCustomerId(id);
	}

}
