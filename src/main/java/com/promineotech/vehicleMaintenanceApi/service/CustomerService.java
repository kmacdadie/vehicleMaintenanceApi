package com.promineotech.vehicleMaintenanceApi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.vehicleMaintenanceApi.entity.Customer;
import com.promineotech.vehicleMaintenanceApi.repository.CustomerRepository;

@Service
public class CustomerService {
	
	private static final Logger logger = LogManager.getLogger(CustomerService.class);
	
	@Autowired
	CustomerRepository repo;
	
	public Customer getCustomerById(Long id) throws Exception {
		try {
			return repo.findOne(id);
		} catch (Exception e) {
			logger.error("Exception occured while trying to retrieve customer: " + id, e);
			throw e;
		}
	}
	
	public Iterable<Customer> getCustomers() {
		return repo.findAll();
	}
	
	public Customer addCustomer(Customer customer) {
		return repo.save(customer);
	}
	
	public Customer updateCustomer(Long id, Customer customer) throws Exception {
		try {
		Customer foundCustomer = repo.findOne(id);
			foundCustomer.setFirstName(customer.getFirstName());
			foundCustomer.setLastName(customer.getLastName());
			foundCustomer.setPhoneNumber(customer.getPhoneNumber());
			foundCustomer.setEmail(customer.getEmail());
			return repo.save(foundCustomer);
		} catch (Exception e) {
			logger.error("Exception occured while trying to update customer: " + id, e);
			throw new Exception("Unable to update customer.");
		}
	}
	
	public void deleteCustomer(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch(Exception e) {
			logger.error("Exception occured while trying to delete customer: " + id, e);
			throw new Exception("Unable to delete customer.");
		}
	}

}
