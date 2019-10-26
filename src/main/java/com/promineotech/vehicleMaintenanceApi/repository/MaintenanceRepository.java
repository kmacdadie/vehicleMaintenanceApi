package com.promineotech.vehicleMaintenanceApi.repository;

import org.springframework.data.repository.CrudRepository;
import com.promineotech.vehicleMaintenanceApi.entity.Maintenance;

public interface MaintenanceRepository extends CrudRepository<Maintenance, Long> {
	
	Iterable<Maintenance> findMaintenanceByCustomerId(Long customerId);

}
