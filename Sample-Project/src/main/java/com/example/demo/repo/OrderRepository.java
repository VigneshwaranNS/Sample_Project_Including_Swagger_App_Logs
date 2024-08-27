package com.example.demo.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Order;

public interface OrderRepository extends MongoRepository<Order, String> {

	List<Order> findByUserId(String userId);

}
