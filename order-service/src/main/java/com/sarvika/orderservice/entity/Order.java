package com.sarvika.orderservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders") // This is the table name in your database
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // Unique identifier for the order

	@Column(name = "user_id", nullable = false)
	private Long userId; // ID of the user who placed the order

	@Column(name = "product_id", nullable = false)
	private String productId; // ID of the product being ordered

	@Column(nullable = false)
	private int quantity; // Quantity of the product ordered

	@Column(name = "order_date", nullable = false)
	private LocalDateTime orderDate; // Date and time when the order was placed

	@Column(nullable = false)
	private String status; // Order status (e.g., PENDING, COMPLETED, CANCELLED)

	// Constructors
	public Order() {
	}

	public Order(Long userId, String productId, int quantity, LocalDateTime orderDate, String status) {
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
		this.orderDate = orderDate;
		this.status = status;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
