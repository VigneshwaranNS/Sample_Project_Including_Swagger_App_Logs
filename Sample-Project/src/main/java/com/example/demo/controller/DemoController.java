package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.log.LoggerClass;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import com.google.gson.Gson;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class DemoController extends LoggerClass {

	@Autowired
	UserService user_service;

	@Autowired
	OrderService order_service;

	Logger logger = LoggerFactory.getLogger(LoggerClass.class);

	@Autowired
	private final Gson gson;

	@Autowired
	public DemoController(Gson gsonObj) {
		this.gson = gsonObj;
	}

	@GetMapping("getAllUsers")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(type = "string")) }),
			@ApiResponse(responseCode = "200", description = "User not found", content = @Content) })

	public ResponseEntity<String> getUserDetails(
			@Parameter(description = "User Json Data", required = true) @RequestBody User user) {

		logger.warn("action - getUserDetails, input - ");

		String response = null;

		try {

			List<User> result = user_service.getUserDetailsServie(user);

			logger.info("action - getUserDetails, response - " + gson.toJson(result));

			response = String.format("{\"status\": 200, \"data\": %s}", gson.toJson(result));

			return new ResponseEntity<String>(response, HttpStatus.OK);

		} catch (RuntimeException e) {

			logger.error("ation - getUserDetails, error - " + e.toString());

			e.printStackTrace();

			response = String.format("{\"status\": 200, \"data\": %s}", null);

			return new ResponseEntity<String>(response, HttpStatus.OK);
		}
	}

	@GetMapping("getAllOrders")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(type = "string")) }),
			@ApiResponse(responseCode = "200", description = "Order not found", content = @Content) })
	public ResponseEntity<String> getAllOrderDetails(
			@Parameter(description = "Order Json Data", required = true) @RequestBody Order order) {

		Order response = new Order();

		String responseObj = null;

		try {

			response = order_service.saveOrder(order);

		} catch (Exception e) {

			logger.error("ation - getAllOrderDetails, error - " + e.toString());

			e.printStackTrace();

			responseObj = String.format("{\"status\": 200, \"data\": %s}", gson.toJson(e.getMessage()));

			return new ResponseEntity<String>(responseObj, HttpStatus.OK);
		}

		responseObj = String.format("{\"status\": 200, \"data\": %s}", gson.toJson(response));

		return new ResponseEntity<String>(responseObj, HttpStatus.OK);
	}

	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(type = "string")) }),
			@ApiResponse(responseCode = "200", description = "User not found", content = @Content) })
	@GetMapping("userPagination")
	public ResponseEntity<String> getUserPagination(
			@Parameter(description = "Page Start Of the Index", required = true) @RequestParam int start_page,
			@Parameter(description = "Page Size Length of Page Getting Loads The Data", required = true) @RequestParam int page_size) {

		log.info("Action - getUserPagination , Input start_page - " + start_page + " , page_size - " + page_size);

		List<User> user_list = new ArrayList<User>();

		String response = null;
		try {

			user_list = user_service.getAllUserPagination(start_page, page_size);

			log.info("Action - getUserPagination , response - " + gson.toJson(user_list));

			response = String.format("{\"status\": 200, \"data\": %s}", gson.toJson(user_list));

		} catch (Exception e) {

			logger.error("ation - getUserPagination, error - " + e.toString());

			e.printStackTrace();

			response = String.format("{\"status\": 200, \"data\": %s}", gson.toJson(e.getMessage()));

			return new ResponseEntity<String>(response, HttpStatus.OK);

		}

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@GetMapping("getUserOrderDetails/{user_id}")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(type = "string")) }),
			@ApiResponse(responseCode = "200", description = "Order Not found", content = @Content) })
	public ResponseEntity<String> getUserOrderDetails(
			@Parameter(description = "User Id for Particular User", required = true) @PathVariable String user_id) {

		log.info("Action - getUserOrderDetails , Input user_id - " + user_id);

		String response = null;

		try {

			Map<String, Object> result = user_service.getUserOrderDetails(user_id);

			response = String.format("{\"status\": 200, \"data\": %s}", gson.toJson(result));

			return new ResponseEntity<String>(response, HttpStatus.OK);

		} catch (Exception e) {

			logger.error("ation - getUserOrderDetails, error - " + e.toString());

			e.printStackTrace();

			response = String.format("{\"status\": 200, \"data\": %s}", gson.toJson(e.getMessage()));

			return new ResponseEntity<String>(response, HttpStatus.OK);

		}
	}

	@GetMapping("Search/{search}")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(type = "string")) }),
			@ApiResponse(responseCode = "200", description = "User not found", content = @Content) })
	public ResponseEntity<String> getUserSearchDetails(
			@Parameter(description = "User Getting Find Using Search", required = true) @PathVariable String search) {

		log.info("Action - getUserSearchDetails , Input search - " + search);

		String response = null;

		try {

			List<User> result = user_service.getUserbySearch(search);

			response = String.format("{\"status\": 200, \"data\": %s}", gson.toJson(result));

			return new ResponseEntity<String>(response, HttpStatus.OK);

		} catch (Exception e) {

			logger.error("ation - getUserSearchDetails, error - " + e.toString());

			e.printStackTrace();

			response = String.format("{\"status\": 200, \"data\": %s}", gson.toJson(e.getMessage()));

			return new ResponseEntity<String>(response, HttpStatus.OK);

		}

	}

	@GetMapping("UserPaging/{start}/{page}")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(type = "string")) }),
			@ApiResponse(responseCode = "200", description = "User not found", content = @Content) })
	public ResponseEntity<String> getSpecificUserwithPaging(
			@Parameter(description = "Pagination Page Details", required = true) @PathVariable int start,
			@Parameter(description = "Pagination Page Size Getting Displayed Based On ", required = true) @PathVariable int page,
			@Parameter(description = "User Search Data ", required = true) @RequestParam String search) {

		log.info("Action - getSpecificUserwithPaging , Input " + gson.toJson(search));

		String response = null;

		try {

			List<User> result = user_service.getSpecificUserWithPagination(start, page, search);

			response = String.format("{\"status\": 200, \"data\": %s}", gson.toJson(result));

			return new ResponseEntity<String>(response, HttpStatus.OK);

		} catch (Exception e) {

			logger.error("ation - getUserSearchDetails, error - " + e.toString());

			e.printStackTrace();

			response = String.format("{\"status\": 200, \"data\": %s}", gson.toJson(e.getMessage()));

			return new ResponseEntity<String>(response, HttpStatus.OK);

		}
	}

	@GetMapping("find")
	public ResponseEntity<String> getMethodName() {

		String response = null;

		try {

			List<User> user_res = user_service.findFieldSpecific();

			User findOneUser = user_service.findOne();

//			response = String.format("{\"status\": 200, \"data\": %s}", gson.toJson(findOneUser));

			response = String.format("{\"status\": 200, \"data\": %s}", gson.toJson(user_res));

		} catch (Exception e) {
			// TODO: handle exception
		}

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@GetMapping("update/{userName}/{email}")
	public ResponseEntity<String> updateUser(@PathVariable String userName,@PathVariable String email) {
		String response = null;

		try {

			long updated_count =user_service.updateUserByName(userName, email);
			

			long many_updated_count =user_service.updateManyUserbyEmail(userName, email);
			
			response = String.format("{\"status\": 200, \"data\": %s}", gson.toJson(many_updated_count));

//			response = String.format("{\"status\": 200, \"data\": %s}", gson.toJson(updated_count));

		} catch (Exception e) {
			
			e.printStackTrace();
			// TODO: handle exception
		}

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@GetMapping("delete/{user_id}")
	public ResponseEntity<String> deleteUser(@PathVariable String user_id) {
		String response = null;

		try {

			long delete_count =user_service.deleteUserByUserId(user_id);

			response = String.format("{\"status\": 200, \"data\": %s}", gson.toJson(delete_count));

		} catch (Exception e) {
			
			e.printStackTrace();
			// TODO: handle exception
		}

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@PostMapping("saveUser")
	public ResponseEntity<String> postMethodName(@RequestBody User entity) {
		
		String response = null;

		try {

			User user_response =user_service.SaveUser(entity);

			response = String.format("{\"status\": 200, \"data\": %s}", gson.toJson(user_response));

		} catch (Exception e) {
			
			e.printStackTrace();
			// TODO: handle exception
		}

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	
	@PostMapping("comparison")
	public ResponseEntity<String> ComparisonOpertaor(@RequestBody User entity) {
	
		String response = null;

		try {

			Map<String, List<User>> user_response =user_service.filterOutUserComparison(entity);

			response = String.format("{\"status\": 200, \"data\": %s}", gson.toJson(user_response));

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	
	@PostMapping("Logical")
	public ResponseEntity<String> logicalOperation(@RequestBody User entity) {
		
		String response = null;

		try {

			Map<String, List<User>> user_response =user_service.filterOutLogicalOperation(entity);

			response = String.format("{\"status\": 200, \"data\": %s}", gson.toJson(user_response));

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@PostMapping("FieldUpdate")
	public ResponseEntity<String> FieldUpdate(@RequestBody User entity) {
		String response = null;

		try {

			user_service.fieldsUpdate(entity);

			response = String.format("{\"status\": 200, \"data\": %s}", gson.toJson(null));

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	

}
