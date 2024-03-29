package com.project.persistence.crud;

import org.springframework.data.repository.CrudRepository;

import com.project.persistence.entities.OrderLine;

public interface OrderLineCrudRepository extends CrudRepository<OrderLine, Integer>{

}