package com.example.demo.model;

import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
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

	@CreatedBy
	public String created_user_id;

	@CreatedDate
	public Date created_user_date;

	@LastModifiedBy
	public String updated_user_id;

	@LastModifiedDate
	public Date updated_user_date;

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

	public String getCreated_user_id() {
		return created_user_id;
	}

	public void setCreated_user_id(String created_user_id) {
		this.created_user_id = created_user_id;
	}

	public Date getCreated_user_date() {
		return created_user_date;
	}

	public void setCreated_user_date(Date created_user_date) {
		this.created_user_date = created_user_date;
	}

	public String getUpdated_user_id() {
		return updated_user_id;
	}

	public void setUpdated_user_id(String updated_user_id) {
		this.updated_user_id = updated_user_id;
	}

	public Date getUpdated_user_date() {
		return updated_user_date;
	}

	public void setUpdated_user_date(Date updated_user_date) {
		this.updated_user_date = updated_user_date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(created_user_date, created_user_id, description, order_id, order_name, updated_user_date,
				updated_user_id, userId);
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
		return Objects.equals(created_user_date, other.created_user_date)
				&& Objects.equals(created_user_id, other.created_user_id)
				&& Objects.equals(description, other.description) && Objects.equals(order_id, other.order_id)
				&& Objects.equals(order_name, other.order_name)
				&& Objects.equals(updated_user_date, other.updated_user_date)
				&& Objects.equals(updated_user_id, other.updated_user_id) && Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", order_name=" + order_name + ", description=" + description
				+ ", userId=" + userId + ", created_user_id=" + created_user_id + ", created_user_date="
				+ created_user_date + ", updated_user_id=" + updated_user_id + ", updated_user_date="
				+ updated_user_date + "]";
	}

	
	
	
	
}
