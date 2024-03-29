package com.project.persistence.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_data")
public class OrderData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int orderId;

	@Column(name = "order_table")
	private int orderTable;

	@ManyToOne
	@JoinColumn(name = "staff_id", insertable = false, updatable = false)
	private Staff staff;

	/*
	 * public enum OrderStatus { EN_PROCESO, PREPARADO }
	 * 
	 * @Enumerated(EnumType.STRING)
	 * 
	 * @Column(name = "order_status") private OrderStatus orderStatus;
	 */

	public enum OrderStatus {
		EN_PROCESO(1), PREPARADO(2);

		private final int value;

		OrderStatus(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	@Column(name = "order_status")
	private int orderStatus;

	@Column(name = "order_date")
	private LocalDateTime orderDate;
	
	@PrePersist
    protected void onCreate() {
		// Establece la fecha y hora actual cuando se crea el pedido
        orderDate = LocalDateTime.now(); 
    }

	@OneToMany(mappedBy = "orderData")
	private List<OrderLine> orderLines;

	// Getters y setters
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderTable() {
		return orderTable;
	}

	public void setOrderTable(int orderTable) {
		this.orderTable = orderTable;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}	

}
