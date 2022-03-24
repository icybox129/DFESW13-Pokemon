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

	// Create a pokemon
	@PostMapping("/createMon")
	public ResponseEntity<String> createMon(@RequestBody Pokemon mon){
		services.createMon(mon);
		String response = mon.getName() + " added!";
		return new ResponseEntity<String>(response, HttpStatus.CREATED);
	}
	
	// Get all pokemon in the DB
	@GetMapping("/getAll")
	public ResponseEntity<List<Pokemon>> getAll(){
		List<Pokemon> result = services.getAll();
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	// Get a specific pokemon by their ID
	@GetMapping("/getId/{id}")
	public ResponseEntity<Pokemon> getById(@PathVariable("id") long id) {
		Pokemon result = services.getById(id);
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	// Update a pokemon entry by their ID
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateById(@PathVariable("id") long id, @RequestBody Pokemon mon) {
		services.updateById(id, mon);
		String response = mon.getName() + " has been updated!";
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	// Delete a pokemon by their ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") long id) {
		services.deleteById(id);
		String response = "ID: " + id + " removed from the database";
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	// Delete all entries in the DB
	@DeleteMapping("/deleteAll")
	public ResponseEntity<String> deleteAll() {
		services.deleteAll();
		String response = "All database entries have been deleted!";
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	// Get all pokemon of that type
	@GetMapping("/getType/{type}")
	public ResponseEntity<List<Pokemon>> getByType(@PathVariable("type") String type) {
		List<Pokemon> result = services.getByType(type);
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	// Get a pokemon by their name
	@GetMapping("/getName/{name}")
	public ResponseEntity<Pokemon> getByName(@PathVariable("name") String name) {
		Pokemon result = services.getByName(name);
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	// List all pokemon by their base stats (BST) in descending order
	@GetMapping("/bstDesc")
	public ResponseEntity<List<Pokemon>> sortByBstDesc() {
		List<Pokemon> result = services.sortByBstDesc();
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
		}
	
	// List all pokemon by their base stats (BST) in ascending order
	@GetMapping("/bstAsc")
	public ResponseEntity<List<Pokemon>> sortByBstAsc() {
		List<Pokemon> result = services.sortByBstAsc();
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	// List all pokemon by their national dex ID in desceinding order
	@GetMapping("/ndexDesc")
	public ResponseEntity<List<Pokemon>> sortByNdexDesc() {
		List<Pokemon> result = services.sortByNdexDesc();
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	// List all pokemon by their national dex ID in ascending order
	@GetMapping("/ndexAsc")
	public ResponseEntity<List<Pokemon>> sortByNdexAsc() {
		List<Pokemon> result = services.sortByNdexAsc();
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	// Check a pokemon and return what moves are effective against it's type
	@GetMapping("/checkType/{name}")
	public ResponseEntity<String> checkTypeEffectiveness(@PathVariable("name") String name) {
		String result = services.checkTypeEffectiveness(name);
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);	
	}
}
