package com.qa.Pokemon.services;

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

}
