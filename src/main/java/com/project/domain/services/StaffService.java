package com.project.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.repositories.StaffRepository;
import com.project.persistence.entities.Staff;

@Service
public class StaffService {

	@Autowired
	private StaffRepository staffRepository;

	public List<Staff> findAll() {
		return staffRepository.findAll();
	}

	public Optional<Staff> findById(int staffId) {
		return staffRepository.findById(staffId);
	}

	public Staff save(Staff staff) {
		return staffRepository.save(staff);
	}

	public boolean delete(int staffId) {
		return findById(staffId).map(staff -> {
			staffRepository.deleteById(staffId);
			return true;
		}).orElse(false);
	}
	
	public Optional<Staff> getByUsername(String username) {
		return staffRepository.getByUsername(username);
	}
	
}
