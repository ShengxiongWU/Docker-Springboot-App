package main.database;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class order_info {
	@Id
	@GeneratedValue
	private Integer orderId;
	private Integer buyerId;
	private Integer sellerId;
	private Integer productId;

	private LocalDateTime orderTime;
	
	
	public order_info() {
		
	}



	public order_info(Integer orderId, Integer buyerId, Integer sellerId, Integer productId, LocalDateTime orderTime) {
		super();
		this.orderId = orderId;
		this.buyerId = buyerId;
		this.sellerId = sellerId;
		this.productId = productId;
		this.orderTime = orderTime;
	}



	public Integer getOrderId() {
		return orderId;
	}



	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}



	public Integer getBuyerId() {
		return buyerId;
	}



	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}



	public Integer getSellerId() {
		return sellerId;
	}



	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}



	public Integer getProductId() {
		return productId;
	}



	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public LocalDateTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}
}
