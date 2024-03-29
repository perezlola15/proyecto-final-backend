package com.project.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.repositories.OrderDataRepository;
import com.project.persistence.entities.OrderData;

@Service
public class OrderDataService {

	@Autowired
	private OrderDataRepository orderDataRepository;

	public List<OrderData> findAll() {
		return orderDataRepository.findAll();
	}

	public Optional<OrderData> findById(int orderId) {
		return orderDataRepository.findById(orderId);
	}

	public OrderData save(OrderData orderData) {
		return orderDataRepository.save(orderData);
	}

	public boolean delete(int orderId) {
		return findById(orderId).map(orderData -> {
			orderDataRepository.deleteById(orderId);
			return true;
		}).orElse(false);
	}
}
