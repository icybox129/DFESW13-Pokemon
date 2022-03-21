package com.qa.Pokemon.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.Pokemon.model.Pokemon;

public interface Repo extends JpaRepository<Pokemon, Long> {


}
