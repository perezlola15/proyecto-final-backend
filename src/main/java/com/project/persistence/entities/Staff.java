package com.project.persistence.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "staff")
public class Staff {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="staff_id")
	private int staffId;
	private String username;
	private String password;
	private boolean locked;
	private boolean disabled;
	
	@OneToMany(mappedBy = "staff")
	private List<OrderData> orders;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "staff")
	@JsonIgnore
	private List<StaffRole> roles;

	
	// Getters y setters
	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public List<StaffRole> getRoles() {
		return roles;
	}

	public void setRoles(List<StaffRole> roles) {
		this.roles = roles;
	} 
	
	
}
