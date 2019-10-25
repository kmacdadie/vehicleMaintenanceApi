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

import com.promineotech.vehicleMaintenanceApi.entity.Schedule;
import com.promineotech.vehicleMaintenanceApi.service.ScheduleService;
import com.promineotech.vehicleMaintenanceApi.util.ScheduleStatus;

@RestController
@RequestMapping("customers/{id}/schedules")
public class ScheduleController {
	
	@Autowired
	private ScheduleService service;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createSchedule(@RequestBody Set<Long> vehicleIds,@PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.createNewSchedule(vehicleIds, id), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{scheduleId}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateSchedule(@PathVariable Long scheduleId, @RequestBody Schedule schedule) {
		try {
			if(schedule.getStatus().equals(ScheduleStatus.CANCELED)) {
				return new ResponseEntity<Object>(service.cancelSchedule(scheduleId), HttpStatus.OK);
			} else if (schedule.getStatus().equals(ScheduleStatus.COMPLETE)) {
				return new ResponseEntity<Object>(service.completeSchedule(scheduleId), HttpStatus.OK);
			}
			return new ResponseEntity<Object>("Invalid update request.",HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> cancelSchedule(@PathVariable Long id) {
		try {
			service.cancelSchedule(id);	
			return new ResponseEntity<Object>("Successufly deleted schedule Id Number: " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}		
	}
	
}
