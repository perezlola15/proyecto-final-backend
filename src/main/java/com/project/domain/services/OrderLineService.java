package com.project.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.repositories.OrderLineRepository;
import com.project.persistence.entities.OrderLine;

@Service
public class OrderLineService {

	@Autowired
	private OrderLineRepository orderLineRepository;

	public List<OrderLine> findAll() {
		return orderLineRepository.findAll();
	}

	public Optional<OrderLine> findById(int orderId) {
		return orderLineRepository.findById(orderId);
	}

	public OrderLine save(OrderLine orderLine) {
		return orderLineRepository.save(orderLine);
	}

	public boolean delete(int orderId) {
		return findById(orderId).map(orderLine -> {
			orderLineRepository.deleteById(orderId);
			return true;
		}).orElse(false);
	}
}
