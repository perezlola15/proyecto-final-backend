package com.project.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.persistence.crud.DishCrudRepository;
import com.project.persistence.entities.Dish;

@Repository
public class DishRepository {

	@Autowired
	private DishCrudRepository dishCrudRepository;
	
	public List<Dish> findAll() {
        return (List<Dish>) dishCrudRepository.findAll();
    }
    
    public Optional<Dish> findById(int dishId) {
        return dishCrudRepository.findById(dishId);
    }

    public Dish save (Dish dish) {
        return dishCrudRepository.save(dish);
    }
    
    public void deleteById(int dishId) {
        dishCrudRepository.deleteById(dishId);
    }
}
