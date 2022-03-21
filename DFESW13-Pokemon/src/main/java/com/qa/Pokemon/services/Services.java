package com.qa.Pokemon.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.Pokemon.model.Pokemon;
import com.qa.Pokemon.repo.Repo;

@Service
public class Services {
	
	private Repo repo;

	public Services(Repo repo) {
		super();
		this.repo = repo;
	}

	public Pokemon createMon(Pokemon mon) {
		Pokemon savedObj = repo.save(mon);
		return savedObj;
		
	}

	public List<Pokemon> getAll() {
		return repo.findAll();
	}

	public Pokemon getById(long id) {
		return repo.findById(id).get();
	}

	public Pokemon updateById(long id, Pokemon mon) {
		// Find the object we want to update
		Pokemon oldMon = getById(id);
		
		// Update the properties of this object, passing in new values
		oldMon.setNdex(mon.getNdex());
		oldMon.setName(mon.getName());
		oldMon.setType(mon.getType());
		oldMon.setGender(mon.getGender());
		oldMon.setHeight(mon.getHeight());
		oldMon.setWeight(mon.getWeight());
		oldMon.setBst(mon.getBst());
		
		// Creating a new pokemon
		Pokemon updatedMon = oldMon;
		
		// Saving the new pokemon into DB
		return repo.save(updatedMon);
		
		
	}

	public boolean deleteById(long id) {
		repo.deleteById(id);
		return true;	
	}

}
