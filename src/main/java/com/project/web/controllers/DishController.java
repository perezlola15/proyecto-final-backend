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

import com.project.domain.services.DishService;
import com.project.persistence.entities.Dish;


@RestController
@RequestMapping("/dishes")
public class DishController {

	@Autowired
	private DishService dishService;
	
	@GetMapping
	public ResponseEntity<List<Dish>> findAll() {
		return new ResponseEntity<List<Dish>>(dishService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{dishId}")
	public ResponseEntity<Dish> getById(@PathVariable("dishId") int dishId) {
		return dishService.findById(dishId).map(dish -> new ResponseEntity<>(dish, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public ResponseEntity<Dish> save(@RequestBody Dish dish) {
		return new ResponseEntity<Dish>(dishService.save(dish), HttpStatus.CREATED);
	}

	@PutMapping("/{dishId}")
	public ResponseEntity<Dish> update(@PathVariable("dishId") int dishId, @RequestBody Dish updatedDish) {
	    return dishService.findById(dishId).map(dishDB -> {
	    	dishDB.setDishName(updatedDish.getDishName());
	    	dishDB.setPrice(updatedDish.getPrice());
	    	dishDB.setVat(updatedDish.getVat());
	    	dishDB.setDishDescription(updatedDish.getDishDescription());
	    	dishDB.setCategoryDish(updatedDish.getCategoryDish());
	    	
	    	Dish savedDish = dishService.save(dishDB);
	        return new ResponseEntity<>(savedDish, HttpStatus.OK); 
	    }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); 
	}

	@DeleteMapping("/{dishId}")
	public ResponseEntity<Dish> delete(@PathVariable("dishId") int dishId) {
		if (dishService.delete(dishId)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
