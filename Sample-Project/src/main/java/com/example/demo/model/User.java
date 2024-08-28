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
import io.swagger.v3.oas.annotations.media.Schema;

@Document(collection = "Users")
public class User {
	
	@Id
	@Schema(description = "Unique identifier of the user", example = "1")
	public String user_id;
	
	public String user_name;

	public String email;
	
	public int user_age;
	
	@CreatedBy
	public String created_user_id;
	
	@CreatedDate
	public Date created_user_date;
	
	@LastModifiedBy
	public String updated_user_id;
	
	@LastModifiedDate
	public Date updated_user_date;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUser_age() {
		return user_age;
	}

	public void setUser_age(int user_age) {
		this.user_age = user_age;
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
		return Objects.hash(created_user_date, created_user_id, email, updated_user_date, updated_user_id, user_age,
				user_id, user_name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(created_user_date, other.created_user_date)
				&& Objects.equals(created_user_id, other.created_user_id) && Objects.equals(email, other.email)
				&& Objects.equals(updated_user_date, other.updated_user_date)
				&& Objects.equals(updated_user_id, other.updated_user_id) && user_age == other.user_age
				&& Objects.equals(user_id, other.user_id) && Objects.equals(user_name, other.user_name);
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name + ", email=" + email + ", user_age=" + user_age
				+ ", created_user_id=" + created_user_id + ", created_user_date=" + created_user_date
				+ ", updated_user_id=" + updated_user_id + ", updated_user_date=" + updated_user_date + "]";
	}
	
	
	

}
