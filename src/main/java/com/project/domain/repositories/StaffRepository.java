package com.project.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.persistence.crud.StaffCrudRepository;
import com.project.persistence.entities.Staff;

@Repository
public class StaffRepository {

	@Autowired
	private StaffCrudRepository staffCrudRepository;
	
	public List<Staff> findAll() {
        return (List<Staff>) staffCrudRepository.findAll();
    }
    
    public Optional<Staff> findById(int staffId) {
        return staffCrudRepository.findById(staffId);
    }

    public Staff save(Staff staff) {
        return staffCrudRepository.save(staff);
    }

    public void deleteById(int staffId) {
        staffCrudRepository.deleteById(staffId);
    }
    
    public Optional<Staff> getByUsername(String username) {
		return staffCrudRepository.findByUsername(username);
	}
}
