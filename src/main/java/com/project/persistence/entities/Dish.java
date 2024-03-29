package com.project.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "dish")
public class Dish {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="dish_id")
	private int dishId;
	
	@Column(name ="dish_name")
	private String dishName;
	
	private double price;
	private int vat;
	
	@Column(name ="dish_description")
	private String dishDescription;
	
	@ManyToOne
	@JoinColumn(name = "category_id") //, insertable = false, updatable = false
	private CategoryDish categoryDish;

	/*@Column(name = "category_id")
	private int categoryId;*/
	
	
	// Getter y setters
	public int getDishId() {
		return dishId;
	}

	public void setDishId(int dishId) {
		this.dishId = dishId;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getVat() {
		return vat;
	}

	public void setVat(int vat) {
		this.vat = vat;
	}

	public String getDishDescription() {
		return dishDescription;
	}

	public void setDishDescription(String dishDescription) {
		this.dishDescription = dishDescription;
	}

	public CategoryDish getCategoryDish() {
		return categoryDish;
	}

	public void setCategoryDish(CategoryDish categoryDish) {
		this.categoryDish = categoryDish;
	}
		
}
