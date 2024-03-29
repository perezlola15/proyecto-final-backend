package com.project.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.repositories.DishRepository;
import com.project.persistence.entities.Dish;

@Service
public class DishService {
	
	@Autowired
	private DishRepository dishRepository;

	public List<Dish> findAll() {
		return dishRepository.findAll();
	}

	public Optional<Dish> findById(int dishId) {
		return dishRepository.findById(dishId);
	}

	public Dish save(Dish dish) {
		return dishRepository.save(dish);
	}

	public boolean delete(int dishId) {
		return findById(dishId).map(categoryDish -> {
			dishRepository.deleteById(dishId);
			return true;
		}).orElse(false);
	}
}
