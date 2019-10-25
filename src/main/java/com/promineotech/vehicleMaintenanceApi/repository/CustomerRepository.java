package com.promineotech.vehicleMaintenanceApi.repository;

import org.springframework.data.repository.CrudRepository;
import com.promineotech.vehicleMaintenanceApi.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
