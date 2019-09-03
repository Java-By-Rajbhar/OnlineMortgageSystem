package com.mortgage.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mortgage.api.entity.Login;
/**
 * 
 * @author Sushil
 *
 */
@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {

	Login findByUserIdAndPassword(String userId, String decodedPassword);
	
	Login findByUserId(String userId);

}
