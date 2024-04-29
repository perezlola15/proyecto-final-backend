package com.project.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.services.StaffService;
import com.project.persistence.entities.Staff;

@RestController
@RequestMapping("/staffs")
public class StaffController {

	@Autowired
	private StaffService staffService;

	@GetMapping
	public ResponseEntity<List<Staff>> findAll() {
		return new ResponseEntity<List<Staff>>(staffService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{staffId}")
	public ResponseEntity<Staff> getById(@PathVariable("staffId") int staffId) {
		return staffService.findById(staffId).map(staff -> new ResponseEntity<>(staff, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	/*@GetMapping("/byname/{staffName}")
	public ResponseEntity<Staff> getByUsername(@PathVariable("staffName") String staffName) {
		return staffService.getByUsername(staffName).map(staff -> new ResponseEntity<>(staff, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	} */

	@PostMapping
	public ResponseEntity<Staff> save(@RequestBody Staff staff) {
		return new ResponseEntity<Staff>(staffService.save(staff), HttpStatus.CREATED);
	}

	@PutMapping("/{staffId}")
	public ResponseEntity<Staff> update(@PathVariable("staffId") int staffId, @RequestBody Staff updatedStaff) {
	    return staffService.findById(staffId).map(staffDB -> {
	        staffDB.setUsername(updatedStaff.getUsername());
	        staffDB.setPassword(updatedStaff.getPassword());

	        Staff savedStaff = staffService.save(staffDB);
	        return new ResponseEntity<>(savedStaff, HttpStatus.OK); 
	    }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); 
	}

	@DeleteMapping("/{staffId}")
	public ResponseEntity<Staff> delete(@PathVariable("staffId") int staffId) {
		if (staffService.delete(staffId)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
