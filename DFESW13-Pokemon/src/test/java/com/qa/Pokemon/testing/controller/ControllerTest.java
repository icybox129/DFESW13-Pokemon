package com.qa.Pokemon.testing.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.Pokemon.model.Pokemon;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:pokemon-schema.sql", "classpath:data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("dev")
public class ControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired ObjectMapper map;
	
	@Test
	void createMonTest() throws Exception {
		
		Pokemon create = new Pokemon("135", "Jolteon", "Electric", "Male", 0.8f, 24.5f, 525);
		String createJSON = this.map.writeValueAsString(create);
		
		RequestBuilder mockReq = post("/createMon").contentType(MediaType.APPLICATION_JSON).content(createJSON);
		
//		Pokemon saved = new Pokemon(5L, "133", "Eevee", "Normal", "Male", 0.3f, 6.5f, 325);
//		
//		String savedJSON = this.map.writeValueAsString(saved);
		
		ResultMatcher matchStatus = status().isCreated();
		ResultMatcher matchBody = content().string("Jolteon added!");
		
		this.mvc.perform(mockReq).andExpect(matchStatus).andExpect(matchBody);
	}
	
	@Test
	void getAllTest() throws Exception {
		
		Pokemon a = new Pokemon(1L, "001", "Bulbasaur", "Grass Poison", "Male", 0.7f, 6.9f, 318);
		Pokemon b = new Pokemon(2L, "004", "Charmander", "Fire", "Male", 0.6f, 8.5f, 309);
		Pokemon c = new Pokemon(3L, "007", "Squirtle", "Water", "Male", 0.5f, 9.0f, 314);
		Pokemon d = new Pokemon(4L, "025", "Pikachu", "Electric", "Female", 0.4f, 6.0f, 320);
		Pokemon e = new Pokemon(5L, "133", "Eevee", "Normal", "Male", 0.3f, 6.5f, 325);
		
		List<Pokemon> db = List.of(a, b, c, d, e);
		
		String allPokeJSON = this.map.writeValueAsString(db);
		
		RequestBuilder mockReq = get("/getAll");
		ResultMatcher matchStatus = status().isAccepted();
		ResultMatcher matchBody = content().json(allPokeJSON);
		
		this.mvc.perform(mockReq).andExpect(matchStatus).andExpect(matchBody);
	}
	
	@Test
	void getIdTest() throws Exception {
		
		Pokemon a = new Pokemon(5L, "133", "Eevee", "Normal", "Male", 0.3f, 6.5f, 325);
		
		String testId = this.map.writeValueAsString(a);
		
		RequestBuilder mockReq = get("/getId/5");
		ResultMatcher status = status().isAccepted();
		ResultMatcher body = content().json(testId);
		
		this.mvc.perform(mockReq).andExpect(status).andExpect(body);
	}
	
	@Test
	void updateByIdTest() throws Exception {
		
		Pokemon updatedMon = new Pokemon("025", "Pikachu", "Electric", "Male", 0.4f, 6.0f, 320);
		String updatedMonJSON = this.map.writeValueAsString(updatedMon);
		
		RequestBuilder mockReq = put("/update/4").contentType(MediaType.APPLICATION_JSON).content(updatedMonJSON);
		ResultMatcher status = status().isAccepted();
		ResultMatcher body = content().string("Pikachu has been updated!");
		
		this.mvc.perform(mockReq).andExpect(status).andExpect(body);
	}
	
	@Test
	void deleteTest() throws Exception {
		
		RequestBuilder mockReq = delete("/delete/2");
		ResultMatcher status = status().isAccepted();
		ResultMatcher body = content().string("ID: 2 removed from the database");
	
		this.mvc.perform(mockReq).andExpect(status).andExpect(body);	
	}
	
	@Test
	void deleteAllTest() throws Exception {
		
		RequestBuilder mockReq = delete("/deleteAll");
		ResultMatcher status = status().isAccepted();
		ResultMatcher body = content().string("All database entries have been deleted!");
		
		this.mvc.perform(mockReq).andExpect(status).andExpect(body);
	}
	
	@Test
	void getTypeTest() throws Exception {
		
		Pokemon a = new Pokemon(1L, "001", "Bulbasaur", "Grass Poison", "Male", 0.7f, 6.9f, 318);
		Pokemon b = new Pokemon(2L, "004", "Charmander", "Fire", "Male", 0.6f, 8.5f, 309);
		Pokemon c = new Pokemon(3L, "007", "Squirtle", "Water", "Male", 0.5f, 9.0f, 314);
		Pokemon d = new Pokemon(4L, "025", "Pikachu", "Electric", "Female", 0.4f, 6.0f, 320);
		Pokemon e = new Pokemon(5L, "133", "Eevee", "Normal", "Male", 0.3f, 6.5f, 325);
		
		List<Pokemon> db = List.of(b);
		
		String allPokeJSON = this.map.writeValueAsString(db);
		
		RequestBuilder mockReq = get("/getType/Fire");
		ResultMatcher status = status().isAccepted();
		ResultMatcher body = content().json(allPokeJSON);
		
		this.mvc.perform(mockReq).andExpect(status).andExpect(body);
	}
	
	@Test
	void getNameTest() throws Exception {
		

		Pokemon d = new Pokemon(4L, "025", "Pikachu", "Electric", "Female", 0.4f, 6.0f, 320);

		String namePokeJSON = this.map.writeValueAsString(d);
		
		RequestBuilder mockReq = get("/getName/Pikachu");
		ResultMatcher status = status().isAccepted();
		ResultMatcher body = content().json(namePokeJSON);
		
		this.mvc.perform(mockReq).andExpect(status).andExpect(body);
	}
	
	@Test
	void sortByBstDescTest() throws Exception {
		
		Pokemon a = new Pokemon(1L, "001", "Bulbasaur", "Grass Poison", "Male", 0.7f, 6.9f, 318);
		Pokemon b = new Pokemon(2L, "004", "Charmander", "Fire", "Male", 0.6f, 8.5f, 309);
		Pokemon c = new Pokemon(3L, "007", "Squirtle", "Water", "Male", 0.5f, 9.0f, 314);
		Pokemon d = new Pokemon(4L, "025", "Pikachu", "Electric", "Female", 0.4f, 6.0f, 320);
		Pokemon e = new Pokemon(5L, "133", "Eevee", "Normal", "Male", 0.3f, 6.5f, 325);
		
		List<Pokemon> db = List.of(a, b, c, d, e);
		
		String allPokeJSON = this.map.writeValueAsString(db);
		
		RequestBuilder mockReq = get("/bstDesc");
		ResultMatcher status = status().isAccepted();
		ResultMatcher body = content().json(allPokeJSON);
		
		this.mvc.perform(mockReq).andExpect(status).andExpect(body);
	}
	
	@Test
	void sortByBstAscTest() throws Exception {
		
		Pokemon a = new Pokemon(1L, "001", "Bulbasaur", "Grass Poison", "Male", 0.7f, 6.9f, 318);
		Pokemon b = new Pokemon(2L, "004", "Charmander", "Fire", "Male", 0.6f, 8.5f, 309);
		Pokemon c = new Pokemon(3L, "007", "Squirtle", "Water", "Male", 0.5f, 9.0f, 314);
		Pokemon d = new Pokemon(4L, "025", "Pikachu", "Electric", "Female", 0.4f, 6.0f, 320);
		Pokemon e = new Pokemon(5L, "133", "Eevee", "Normal", "Male", 0.3f, 6.5f, 325);
		
		List<Pokemon> db = List.of(a, b, c, d, e);
		
		String allPokeJSON = this.map.writeValueAsString(db);
		
		RequestBuilder mockReq = get("/bstAsc");
		ResultMatcher status = status().isAccepted();
		ResultMatcher body = content().json(allPokeJSON);
		
		this.mvc.perform(mockReq).andExpect(status).andExpect(body);
	}
	
	@Test
	void sortByNdexDescTest() throws Exception {
		
		Pokemon a = new Pokemon(1L, "001", "Bulbasaur", "Grass Poison", "Male", 0.7f, 6.9f, 318);
		Pokemon b = new Pokemon(2L, "004", "Charmander", "Fire", "Male", 0.6f, 8.5f, 309);
		Pokemon c = new Pokemon(3L, "007", "Squirtle", "Water", "Male", 0.5f, 9.0f, 314);
		Pokemon d = new Pokemon(4L, "025", "Pikachu", "Electric", "Female", 0.4f, 6.0f, 320);
		Pokemon e = new Pokemon(5L, "133", "Eevee", "Normal", "Male", 0.3f, 6.5f, 325);
		
		List<Pokemon> db = List.of(a, b, c, d, e);
		
		String allPokeJSON = this.map.writeValueAsString(db);
		
		RequestBuilder mockReq = get("/ndexDesc");
		ResultMatcher status = status().isAccepted();
		ResultMatcher body = content().json(allPokeJSON);
		
		this.mvc.perform(mockReq).andExpect(status).andExpect(body);
	}
	
	@Test
	void sortByNdexAscTest() throws Exception {
		
		Pokemon a = new Pokemon(1L, "001", "Bulbasaur", "Grass Poison", "Male", 0.7f, 6.9f, 318);
		Pokemon b = new Pokemon(2L, "004", "Charmander", "Fire", "Male", 0.6f, 8.5f, 309);
		Pokemon c = new Pokemon(3L, "007", "Squirtle", "Water", "Male", 0.5f, 9.0f, 314);
		Pokemon d = new Pokemon(4L, "025", "Pikachu", "Electric", "Female", 0.4f, 6.0f, 320);
		Pokemon e = new Pokemon(5L, "133", "Eevee", "Normal", "Male", 0.3f, 6.5f, 325);
		
		List<Pokemon> db = List.of(a, b, c, d, e);
		
		String allPokeJSON = this.map.writeValueAsString(db);
		
		RequestBuilder mockReq = get("/ndexAsc");
		ResultMatcher status = status().isAccepted();
		ResultMatcher body = content().json(allPokeJSON);
		
		this.mvc.perform(mockReq).andExpect(status).andExpect(body);
	}
	
	@Test
	void checkTypeEffectivenessTest () throws Exception {
	
		RequestBuilder mockReq = get("/checkType/Charmander");
		ResultMatcher status = status().isAccepted();
		ResultMatcher body = content().string("Charmander is weak to: Water, Ground and Rock type moves!");
		
		this.mvc.perform(mockReq).andExpect(status).andExpect(body);
	}
}
