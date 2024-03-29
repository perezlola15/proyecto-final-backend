package com.project.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.persistence.crud.OrderLineCrudRepository;
import com.project.persistence.entities.OrderLine;

@Repository
public class OrderLineRepository {

	@Autowired
	private OrderLineCrudRepository orderLineCrudRepository;
	
	public List<OrderLine> findAll() {
        return (List<OrderLine>) orderLineCrudRepository.findAll();
    }
    
    public Optional<OrderLine> findById(int orderLineId) {
        return orderLineCrudRepository.findById(orderLineId);
    }

    public OrderLine save(OrderLine orderLine) {
        return orderLineCrudRepository.save(orderLine);
    }

    public void deleteById(int orderLineId) {
        orderLineCrudRepository.deleteById(orderLineId);
    }
}
