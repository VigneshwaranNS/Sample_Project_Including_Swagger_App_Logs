package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.demo.log.LoggerClass;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.repo.OrderRepository;
import com.example.demo.repo.UserRepository;
import com.google.gson.Gson;

@Service
public class UserService extends LoggerClass {

	@Autowired
	private UserRepository user_repo;

	@Autowired
	private OrderRepository order_repo;

	Logger logg = LoggerFactory.getLogger(LoggerClass.class);

	@Autowired
	public UserService(Gson gson) {
		this.gson = gson;
	}

	@Autowired
	private MongoTemplate mongoTemplate;

	private Gson gson;

	// Save & get all User's
	public List<User> getUserDetailsServie(User user) {

		logg.info("Ation - getUserDetailsServie , input - " + gson.toJson(null));

		List<User> user_result = new ArrayList<User>();

		User save_response = user_repo.save(user);

		if (!save_response.getUser_name().isEmpty()) {

			user_result = user_repo.findAll();

		}
		
		logg.info("Ation - getUserDetailsServie , response - " + gson.toJson(user_result));

		List<User> userobj = getUserByUser("VigneshNEW");

		logg.info("Ation - Before User Name , response - " + gson.toJson(userobj));

		User userNameObj = getUserById("66c86d34c9e49a4d75107295");

		logg.info("Ation - Before One Id , response - " + gson.toJson(userNameObj));

		return user_result;
	}

	// Find User by User Name
	public List<User> getUserByUser(String user_name) {

		Query query = new Query(Criteria.where("user_name").is(user_name));

		return mongoTemplate.find(query, User.class);

	}

	// get User By Id
	public User getUserById(String user_id) {

		Query query = new Query(Criteria.where("_id").is(user_id));

		return mongoTemplate.findOne(query, User.class);
	}

	// get User & Order Details Combined
	public Map<String, Object> getUserOrderDetails(String user_id) {

		Map<String, Object> response = new HashMap();

		if (user_repo.existsById(user_id)
				&& order_repo.findByUserId(user_id)!=null
		) {

			Optional<User> user_result = user_repo.findById(user_id);

			List<User> user_res = user_result.stream().filter(a -> a.getUser_id() != null).collect(Collectors.toList());

			List<Order> order_result = order_repo.findByUserId(user_id);

			response.put("user_details", user_res);
			response.put("order_details", order_result);
		}

		return response;

	}

	// pagination
	public List<User> getAllUserPagination(int start, int size) {

		log.info("Action - getAllUserPagination , Input - " + start + " , " + size);

		Pageable page_able = PageRequest.of(start, size);

		Page<User> page_response = user_repo.pagination_user(page_able);

		List<User> response = page_response.getContent();

		log.info("Action - getAllUserPagination , Result - " + gson.toJson(response));

		return response;

	}

	
	// Search
	public List<User> getUserbySearch(String search) {

		log.info("Action - getUserbySearch , Input - search " + search);

		List<User> user_result = user_repo.getUserSearchDetails(search);

		List<User> ress = user_result.stream().filter(a -> a.getUser_name().equals(search))
				.collect(Collectors.toList());

		log.info("Action - getUserbySearch , response " + gson.toJson(ress));

		return user_result;
	}


	// Paging & Search
	// find Username - vignesh
	// Paging 10 record in each page & skip previous records & Sort By UserName ASC
	
	public List<User> getSpecificUserWithPagination(int start,int size,String search){
		
		log.info("Action -  getSpecificUserWithPagination , input search "+gson.toJson(search));
		
		Pageable page = PageRequest.of(start, size,Sort.by(Sort.Order.asc("userName")));
				
		Page page_data=  user_repo.getUserSpecificName(search,page);
		
		List<User> search_user= page_data.getContent();
		
		log.info("Action -  getSpecificUserWithPagination , result "+gson.toJson(search_user));
		
		return search_user;
	}

}
