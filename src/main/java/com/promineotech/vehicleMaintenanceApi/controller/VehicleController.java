package com.promineotech.vehicleMaintenanceApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.vehicleMaintenanceApi.entity.Vehicle;
import com.promineotech.vehicleMaintenanceApi.service.VehicleService;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
	
	@Autowired
	VehicleService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getVehicle(@PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.getVehicleById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> addVehicle(@RequestBody Vehicle vehicle) {
		return new ResponseEntity<Object>(service.addVehicle(vehicle), HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getVehicles() {
		return new ResponseEntity<Object>(service.getVehicles(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/vehicles/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
		try {
			return new ResponseEntity<Object>(service.updateVehicle(id, vehicle), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteVehicle(@PathVariable Long id) {
		try {
			service.deleteVehicle(id);
			return new ResponseEntity<Object>("Successufly deleted vehicle ID Number: " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to delete vehicle.", HttpStatus.NOT_FOUND);
		}
	}

}
