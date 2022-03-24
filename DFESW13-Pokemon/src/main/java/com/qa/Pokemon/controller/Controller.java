package com.qa.Pokemon.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Pokemon>> getAll(){
		List<Pokemon> result = services.getAll();
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getId/{id}")
	public ResponseEntity<Pokemon> getById(@PathVariable("id") long id) {
		Pokemon result = services.getById(id);
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateById(@PathVariable("id") long id, @RequestBody Pokemon mon) {
		services.updateById(id, mon);
		String response = mon.getName() + " has been updated!";
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") long id) {
		services.deleteById(id);
		String response = "ID: " + id + " removed from database";
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<String> deleteAll() {
		services.deleteAll();
		String response = "All database entries have been deleted!";
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getType/{type}")
	public ResponseEntity<List<Pokemon>> getByType(@PathVariable("type") String type) {
		List<Pokemon> result = services.getByType(type);
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getName/{name}")
	public ResponseEntity<Pokemon> getByName(@PathVariable("name") String name) {
		Pokemon result = services.getByName(name);
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/bstDesc")
	public ResponseEntity<List<Pokemon>> sortByBstDesc() {
		List<Pokemon> result = services.sortByBstDesc();
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
		}
	
	@GetMapping("/bstAsc")
	public ResponseEntity<List<Pokemon>> sortByBstAsc() {
		List<Pokemon> result = services.sortByBstAsc();
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/checkType/{name}")
	public ResponseEntity<String> checkTypeEffectiveness(@PathVariable("name") String name) {
		String result = services.checkTypeEffectiveness(name);
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);	
	}
}
