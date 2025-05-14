package com.example.greetingapp;

import jakarta.persistence.*;

@Entity
public class Greeting {
	
	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
    private String message;
	
	public Greeting() {
    }
	
	public Greeting(String message) {
        this.message = message;
    }
	
	public long getId() {
        return id;
    }
	
	public String getMessage() {
        return message;
    }
	
	public void setMessage(String message) {
	        this.message = message;
	}
 

}
