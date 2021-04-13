package com.rajdeep.rediscache.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable{
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private long salary;
}
