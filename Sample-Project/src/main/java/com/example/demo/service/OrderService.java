package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.log.LoggerClass;
import com.example.demo.model.Order;
import com.example.demo.repo.OrderRepository;
import com.example.demo.repo.UserRepository;
import com.google.gson.Gson;

@Service
public class OrderService extends LoggerClass {

	@Autowired
	OrderRepository order_repo;

	@Autowired
	UserRepository user_repo;

	@Autowired
	MongoTemplate mongo_template;

	@Autowired
	OrderService(Gson son) {
		this.gson = son;
	}

	private final Gson gson;

	Logger log = LoggerFactory.getLogger(LoggerClass.class);

	
	// Save Orders
	public Order saveOrder(Order order) throws Exception {
		
		Order order_result = new Order();
		
		log.info("Action - saveOrder , Input - "+gson.toJson(order));

		if (user_repo.existsById(order.getUserId()) && !order_repo.findByUserId(order.getUserId()).isEmpty()) {

			order_result = order_repo.save(order);
		} else {
			throw new Exception("User Id Is Not Exists");
		}

		log.info("Action - saveOrder , Response - "+gson.toJson(order_result));
		
		return order_result;
	}

}
