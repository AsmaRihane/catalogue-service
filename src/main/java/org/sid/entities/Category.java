package org.sid.entities;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Document
@Data
@NoArgsConstructor @AllArgsConstructor 
public class Category {
	
	@Id
	private String id;
	private String name;
	@DBRef
	private Collection<Product> products= new ArrayList<>();
	public Category(String id, String name, Collection<Product> products) {
		
		this.id = id;
		this.name = name;
		this.products = products;
	}
	@Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
	public Collection<Product> getProducts() {
		return products;
	}
	

}
