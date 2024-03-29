package com.project.persistence.crud;

import org.springframework.data.repository.CrudRepository;

import com.project.persistence.entities.CategoryDish;


public interface CategoryDishCrudRepository extends CrudRepository<CategoryDish, Integer> {
   
}
