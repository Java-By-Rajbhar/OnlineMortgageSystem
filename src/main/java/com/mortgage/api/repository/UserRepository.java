package com.mortgage.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mortgage.api.entity.User;
/**
 * 
 * @author Sushil
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	public User findByUserId(String userId);

	@Query("SELECT DISTINCT userId FROM User")
	public List<String> getDistinctUserId();
}
