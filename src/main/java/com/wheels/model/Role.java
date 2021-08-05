package com.wheels.model;




import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Roles")
public class Role {
	
	private String id;



	public Role() {

	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}

	
}