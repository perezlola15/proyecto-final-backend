package com.project.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.repositories.CategoryDishRepository;
import com.project.persistence.entities.CategoryDish;

@Service
public class CategoryDishService {

	@Autowired
	private CategoryDishRepository categoryDishRepository;

	public List<CategoryDish> findAll() {
		return categoryDishRepository.findAll();
	}

	public Optional<CategoryDish> findById(int categoryId) {
		return categoryDishRepository.findById(categoryId);
	}

	public CategoryDish save(CategoryDish categoryDish) {
		return categoryDishRepository.save(categoryDish);
	}

	public boolean delete(int categoryId) {
		return findById(categoryId).map(categoryDish -> {
			categoryDishRepository.deleteById(categoryId);
			return true;
		}).orElse(false);
	}
}
