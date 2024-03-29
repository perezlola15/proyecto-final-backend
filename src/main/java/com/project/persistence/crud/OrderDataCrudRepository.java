package com.project.persistence.crud;

import org.springframework.data.repository.CrudRepository;

import com.project.persistence.entities.OrderData;

public interface OrderDataCrudRepository extends CrudRepository<OrderData, Integer>{

}

