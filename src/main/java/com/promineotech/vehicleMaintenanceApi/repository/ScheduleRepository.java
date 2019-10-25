package com.promineotech.vehicleMaintenanceApi.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.vehicleMaintenanceApi.entity.Schedule;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
	
	Iterable<Schedule> findSchedulesByCustomerId(Long customerId);

}
