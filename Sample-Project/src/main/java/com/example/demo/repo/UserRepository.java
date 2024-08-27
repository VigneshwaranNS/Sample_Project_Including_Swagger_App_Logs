package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>,PagingAndSortingRepository<User, String>{
	
	public List<User> findAll();
	
	public User save(User user);
	
	@Query("{}")  //optional
	public Page<User> pagination_user(Pageable page_able);

	public Optional<User> findById(String user_id);

	@Query("{ 'user_name': { $regex: ?0 } }") 
	List<User> getUserSearchDetails(String search);

	@Query("{ 'user_name' : {$regex: ?0 } }")
	public Page<User> getUserSpecificName(String search,Pageable page);

//	@Query("{ 'user_name': { $regex: '\\b' + ?0 + '\\b' } }") 
//	List<User> getUserSearchDetails(String search);

}
