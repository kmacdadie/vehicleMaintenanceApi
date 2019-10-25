package com.promineotech.vehicleMaintenanceApi.repository;

import org.springframework.data.repository.CrudRepository;
import com.promineotech.vehicleMaintenanceApi.entity.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

}
