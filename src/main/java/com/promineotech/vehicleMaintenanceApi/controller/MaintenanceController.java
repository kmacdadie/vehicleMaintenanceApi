package com.promineotech.vehicleMaintenanceApi.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.vehicleMaintenanceApi.entity.Maintenance;
import com.promineotech.vehicleMaintenanceApi.service.MaintenanceService;
import com.promineotech.vehicleMaintenanceApi.util.MaintenanceStatus;

@RestController
@RequestMapping("customers/{id}/maintenances")
public class MaintenanceController {
	
	@Autowired
	private MaintenanceService service;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createMaintenance(@RequestBody Set<Long> vehicleIds,@PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.generateNewMaintenance(vehicleIds, id), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{maintenanceId}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateMaintenance(@PathVariable Long maintenanceId, @RequestBody Maintenance maintenance) {
		try {
			if(maintenance.getStatus().equals(MaintenanceStatus.CANCELED)) {
				return new ResponseEntity<Object>(service.cancelMaintenance(maintenanceId), HttpStatus.OK);
			} else if (maintenance.getStatus().equals(MaintenanceStatus.COMPLETE)) {
				return new ResponseEntity<Object>(service.completeMaintenance(maintenanceId), HttpStatus.OK);
			}
			return new ResponseEntity<Object>("Invalid update request.",HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> cancelMaintenance(@PathVariable Long id) {
		try {
			service.cancelMaintenance(id);	
			return new ResponseEntity<Object>("Successufly deleted maintenance Id Number: " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}		
	}
	
}
