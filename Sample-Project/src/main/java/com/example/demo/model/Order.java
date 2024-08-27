package com.example.demo.model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Orders")
public class Order {

	@Id
	public String order_id;
	@Indexed
	public String order_name;
	@Indexed
	public String description;
	public String userId;
	
	
	
	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", order_name=" + order_name + ", description=" + description
				+ ", userId=" + userId + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(description, order_id, order_name, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(description, other.description) && Objects.equals(order_id, other.order_id)
				&& Objects.equals(order_name, other.order_name) && Objects.equals(userId, other.userId);
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getOrder_name() {
		return order_name;
	}
	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
}
