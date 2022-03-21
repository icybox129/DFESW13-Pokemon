package com.qa.Pokemon.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.Pokemon.model.Pokemon;

public interface Repo extends JpaRepository<Pokemon, Long> {

	public List<Pokemon> findByType(String type);
	
	public Pokemon findByName(String name);

}
