package org.sid.entities;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document

@NoArgsConstructor
@AllArgsConstructor @ToString
public class Product {

	@Id
	private String id;
	private String name;
	private double price;
	
	public Category category;
	public Product(String id, String name, double price, Category category) {
		
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
	}
	

}
