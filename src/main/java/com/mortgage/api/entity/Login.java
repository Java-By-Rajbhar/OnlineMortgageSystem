package com.mortgage.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Sushil
 *
 */
@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
public class Login {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String userId;
	private String password;
}
