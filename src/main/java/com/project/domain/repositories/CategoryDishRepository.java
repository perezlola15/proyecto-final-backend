package com.project.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.persistence.crud.CategoryDishCrudRepository;
import com.project.persistence.entities.CategoryDish;

@Repository
public class CategoryDishRepository {

	@Autowired
	private CategoryDishCrudRepository categoryDishCrudRepository;
	
	public List<CategoryDish> findAll() {
		return (List<CategoryDish>) categoryDishCrudRepository.findAll();
	}
	
	public Optional<CategoryDish> findById(int category_id) {
		return categoryDishCrudRepository.findById(category_id);
	}
	
	public CategoryDish save (CategoryDish categoryDish) {
		return categoryDishCrudRepository.save(categoryDish);
	}
	
	public void deleteById(int categoryId) {
		categoryDishCrudRepository.deleteById(categoryId);
	}

}
