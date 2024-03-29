package com.project.persistence.crud;

import org.springframework.data.repository.CrudRepository;

import com.project.persistence.entities.Dish;

public interface DishCrudRepository extends CrudRepository<Dish, Integer> {

}
