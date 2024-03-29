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

import com.project.domain.services.CategoryDishService;
import com.project.persistence.entities.CategoryDish;

@RestController
@RequestMapping("/categories")
public class CategoryDishController {

	@Autowired
	private CategoryDishService categoryDishService;

	@GetMapping
	public ResponseEntity<List<CategoryDish>> findAll() {
		return new ResponseEntity<List<CategoryDish>>(categoryDishService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDish> getById(@PathVariable("categoryId") int categoryId) {
		return categoryDishService.findById(categoryId).map(categoryDish -> new ResponseEntity<>(categoryDish, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public ResponseEntity<CategoryDish> save(@RequestBody CategoryDish categoryDish) {
		return new ResponseEntity<CategoryDish>(categoryDishService.save(categoryDish), HttpStatus.CREATED);
	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDish> update(@PathVariable("categoryId") int categoryId, @RequestBody CategoryDish categoryDish) {
		if (categoryId != categoryDish.getCategoryId()) {
		return categoryDishService.findById(categoryId)
				.map(categoryDB -> new ResponseEntity<>(categoryDishService.save(categoryDish), HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<CategoryDish> delete(@PathVariable("categoryId") int categoryId) {
		if (categoryDishService.delete(categoryId)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
