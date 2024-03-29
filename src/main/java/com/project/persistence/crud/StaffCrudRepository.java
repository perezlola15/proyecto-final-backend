package com.project.persistence.crud;

import org.springframework.data.repository.CrudRepository;

import com.project.persistence.entities.Staff;


public interface StaffCrudRepository extends CrudRepository<Staff, Integer>{

}
