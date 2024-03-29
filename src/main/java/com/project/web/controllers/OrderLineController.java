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

import com.project.domain.services.OrderLineService;
import com.project.persistence.entities.OrderLine;

@RestController
@RequestMapping("/ordersLine")
public class OrderLineController {

	@Autowired
	private OrderLineService orderLineService;

	@GetMapping
	public ResponseEntity<List<OrderLine>> findAll() {
		return new ResponseEntity<List<OrderLine>>(orderLineService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{orderLineId}")
	public ResponseEntity<OrderLine> getById(@PathVariable("orderLineId") int orderLineId) {
		return orderLineService.findById(orderLineId).map(orderLine -> new ResponseEntity<>(orderLine, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public ResponseEntity<OrderLine> save(@RequestBody OrderLine orderLine) {
		return new ResponseEntity<OrderLine>(orderLineService.save(orderLine), HttpStatus.CREATED);
	}

	@PutMapping("/{orderLineId}")
	public ResponseEntity<OrderLine> update(@PathVariable("orderLineId") int orderLineId, @RequestBody OrderLine orderLine) {
		if (orderLineId != orderLine.getOrderLineId()) {
		return orderLineService.findById(orderLineId)
				.map(orderLineDB -> new ResponseEntity<>(orderLineService.save(orderLine), HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{orderLineId}")
	public ResponseEntity<OrderLine> delete(@PathVariable("orderLineId") int orderLineId) {
		if (orderLineService.delete(orderLineId)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
