package com.project.persistence.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "staff_role")
public class StaffRole {
	@Id
	@Column(name ="role_id")
	private int roleId;
	
	@Id
	private String role;
	
	@Column(name ="granted_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime grantedDate;
	
	@ManyToOne
	@JoinColumn(name="role_id", referencedColumnName = "staff_id", insertable = false, updatable = false)
	private Staff staff;

	
	// Getters y setters
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LocalDateTime getGrantedDate() {
		return grantedDate;
	}

	public void setGrantedDate(LocalDateTime grantedDate) {
		this.grantedDate = grantedDate;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
}
