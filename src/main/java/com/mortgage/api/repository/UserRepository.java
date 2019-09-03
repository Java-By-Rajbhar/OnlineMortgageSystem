package com.mortgage.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
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

}
