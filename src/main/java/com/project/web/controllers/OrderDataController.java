package com.project.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.services.OrderDataService;
import com.project.persistence.entities.OrderData;

@RestController
@RequestMapping("/orders")
public class OrderDataController {

	@Autowired
	private OrderDataService orderDataService;

	@GetMapping
	public ResponseEntity<List<OrderData>> findAll() {
		return new ResponseEntity<List<OrderData>>(orderDataService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<OrderData> getById(@PathVariable("orderId") int orderId) {
		return orderDataService.findById(orderId).map(orderData -> new ResponseEntity<>(orderData, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public ResponseEntity<OrderData> save(@RequestBody OrderData orderData) {
		return new ResponseEntity<OrderData>(orderDataService.save(orderData), HttpStatus.CREATED);
	}

	@PutMapping("/{orderId}")
	public ResponseEntity<OrderData> update(@PathVariable("orderId") int orderId, @RequestBody OrderData updatedOrder) {
	    return orderDataService.findById(orderId).map(orderDB -> {
	        orderDB.setOrderTable(updatedOrder.getOrderTable());
	        orderDB.setStaff(updatedOrder.getStaff());
	        orderDB.setOrderStatus(updatedOrder.getOrderStatus());
	        orderDB.setOrderDate(updatedOrder.getOrderDate());

	        OrderData savedOrder = orderDataService.save(orderDB);
	        return new ResponseEntity<>(savedOrder, HttpStatus.OK); 
	    }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); 
	}

	@DeleteMapping("/{orderId}")
	public ResponseEntity<OrderData> delete(@PathVariable("orderId") int orderId) {
		if (orderDataService.delete(orderId)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
