package com.project.persistence.crud;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.project.persistence.entities.Staff;


public interface StaffCrudRepository extends CrudRepository<Staff, Integer>{
	Optional<Staff> findByUsername(String username);
}
