package com.project.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.persistence.crud.OrderDataCrudRepository;
import com.project.persistence.entities.OrderData;

@Repository
public class OrderDataRepository {

	@Autowired
	private OrderDataCrudRepository orderDataCrudRepository;
	
	public List<OrderData> findAll() {
        return (List<OrderData>) orderDataCrudRepository.findAll();
    }
    
    public Optional<OrderData> findById(int orderId) {
        return orderDataCrudRepository.findById(orderId);
    }

    public OrderData save (OrderData orderData) {
        return orderDataCrudRepository.save(orderData);
    }
    
    public void deleteById(int orderId) {
        orderDataCrudRepository.deleteById(orderId);
    }
}
