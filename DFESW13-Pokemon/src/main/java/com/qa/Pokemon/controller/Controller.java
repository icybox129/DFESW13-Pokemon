package com.qa.Pokemon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.Pokemon.model.Pokemon;
import com.qa.Pokemon.services.Services;

@RestController
public class Controller {
	
	private Services services;
	
	public Controller(Services services) {
		super();
		this.services = services;
	}

	@PostMapping("/createMon")
	public ResponseEntity<String> createMon(@RequestBody Pokemon mon){
		
		services.createMon(mon);
		String response = mon.getName() + " added!";
		return new ResponseEntity<String>(response, HttpStatus.CREATED);
	}
}
