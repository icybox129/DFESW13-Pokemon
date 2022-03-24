package com.qa.Pokemon.testing.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.Pokemon.model.Pokemon;
import com.qa.Pokemon.repo.Repo;
import com.qa.Pokemon.services.Services;

@SpringBootTest
@ActiveProfiles("dev")
public class ServicesTest {
	
	private Pokemon input;
	private Pokemon returned;
	
	@Autowired
	private Services services;
	
	@MockBean
	private Repo repo;
	
	@BeforeEach
	void setup() {
		
		input = new Pokemon("001", "Bulbasaur", "Grass Poison", "Male", 0.7f, 6.9f, 318);
		returned = new Pokemon(1L, "001", "Bulbasaur", "Grass Poison", "Male", 0.7f, 6.9f, 318);
	}
	
	@Test
	void createMonTest() {
		//GIVEN
		
		// WHEN
		Mockito.when(this.repo.save(input)).thenReturn(returned);
		
		// THEN
		assertThat(this.services.createMon(input)).isEqualTo(returned);
		
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).save(input);
	}
	
	@Test
	void getAllTest() {
		// GIVEN
		List<Pokemon> getAllList = new ArrayList<>();
		getAllList.add(input);
		// WHEN
		Mockito.when(this.repo.findAll()).thenReturn(getAllList);
		// THEN
		assertThat(this.services.getAll()).isEqualTo(getAllList);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	void getByIdTest() {
		// GIVEN
		Long id = 1L;
		// WHEN
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(returned));
		// THEN
		assertThat(this.services.getById(id)).isEqualTo(returned);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}
	
	@Test
	void updateByIdTest() {
		// GIVEN
		Long id = 1L;
		Pokemon toUpdate = new Pokemon("001", "Bulbasaur", "Grass Poison", "Male", 0.7f, 6.9f, 318);
		Pokemon updatedMon = new Pokemon("001", "Bulbasaur", "Grass Poison", "Female", 0.7f, 6.9f, 318);
		// WHEN
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(updatedMon));
		Mockito.when(this.repo.save(updatedMon)).thenReturn(updatedMon);
		// THEN
		assertThat(this.services.updateById(id, toUpdate)).isEqualTo(updatedMon);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(updatedMon);
	}
	
	@Test
	void deleteByIdTest() {
		// GIVEN
		Long id = 1L;
		// WHEN

		// THEN
		assertThat(this.services.deleteById(id)).isTrue();
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
	}
	
	@Test
	void deleteAllTest() {
		// GIVEN
		
		// WHEN
		
		// THEN
		assertThat(this.services.deleteAll()).isEqualTo(true);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).deleteAll();
	}
	
	@Test
	void getByTestTest() {
		// GIVEN
		Pokemon a = new Pokemon(1L, "001", "Bulbasaur", "Grass Poison", "Male", 0.7f, 6.9f, 318);
		Pokemon b = new Pokemon(2L, "004", "Charmander", "Fire", "Male", 0.6f, 8.5f, 309);
		Pokemon c = new Pokemon(3L, "007", "Squirtle", "Water", "Male", 0.5f, 9.0f, 314);
		Pokemon d = new Pokemon(4L, "025", "Pikachu", "Electric", "Female", 0.4f, 6.0f, 320);
		Pokemon e = new Pokemon(5L, "133", "Eevee", "Normal", "Male", 0.3f, 6.5f, 325);
		
		List<Pokemon> db = List.of(b);
		// WHEN
		Mockito.when(this.repo.findByType("Fire")).thenReturn(db);
		// THEN
		assertThat(this.services.getByType("Fire")).isEqualTo(db);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByType("Fire");
	}
	
	@Test
	void getByName() {
		// GIVEN
		Pokemon d = new Pokemon(4L, "025", "Pikachu", "Electric", "Female", 0.4f, 6.0f, 320);
		// WHEN
		Mockito.when(this.repo.findByName("Pikachu")).thenReturn(d);
		// THEN
		assertThat(this.services.getByName("Pikachu")).isEqualTo(d);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("Pikachu");
	}
	
	@Test
	void sortByBstDescTest() {
		// GIVEN
		Pokemon a = new Pokemon(1L, "001", "Bulbasaur", "Grass Poison", "Male", 0.7f, 6.9f, 318);
		Pokemon b = new Pokemon(2L, "004", "Charmander", "Fire", "Male", 0.6f, 8.5f, 309);
		Pokemon c = new Pokemon(3L, "007", "Squirtle", "Water", "Male", 0.5f, 9.0f, 314);
		Pokemon d = new Pokemon(4L, "025", "Pikachu", "Electric", "Female", 0.4f, 6.0f, 320);
		Pokemon e = new Pokemon(5L, "133", "Eevee", "Normal", "Male", 0.3f, 6.5f, 325);
		
		List<Pokemon> db = List.of(a, b, c, d, e);
		// WHEN
		Mockito.when(this.repo.findByOrderByBstDesc()).thenReturn(db);
		// THEN
		assertThat(this.services.sortByBstDesc()).isEqualTo(db);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByOrderByBstDesc();
	}

	@Test
	void sortByBstAscTest() {
		// GIVEN
		Pokemon a = new Pokemon(1L, "001", "Bulbasaur", "Grass Poison", "Male", 0.7f, 6.9f, 318);
		Pokemon b = new Pokemon(2L, "004", "Charmander", "Fire", "Male", 0.6f, 8.5f, 309);
		Pokemon c = new Pokemon(3L, "007", "Squirtle", "Water", "Male", 0.5f, 9.0f, 314);
		Pokemon d = new Pokemon(4L, "025", "Pikachu", "Electric", "Female", 0.4f, 6.0f, 320);
		Pokemon e = new Pokemon(5L, "133", "Eevee", "Normal", "Male", 0.3f, 6.5f, 325);
		
		List<Pokemon> db = List.of(a, b, c, d, e);
		// WHEN
		Mockito.when(this.repo.findByOrderByBstAsc()).thenReturn(db);
		// THEN
		assertThat(this.services.sortByBstAsc()).isEqualTo(db);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByOrderByBstAsc();
	}
	
	@Test
	void sortByNdexDescTest() {
		// GIVEN
		Pokemon a = new Pokemon(1L, "001", "Bulbasaur", "Grass Poison", "Male", 0.7f, 6.9f, 318);
		Pokemon b = new Pokemon(2L, "004", "Charmander", "Fire", "Male", 0.6f, 8.5f, 309);
		Pokemon c = new Pokemon(3L, "007", "Squirtle", "Water", "Male", 0.5f, 9.0f, 314);
		Pokemon d = new Pokemon(4L, "025", "Pikachu", "Electric", "Female", 0.4f, 6.0f, 320);
		Pokemon e = new Pokemon(5L, "133", "Eevee", "Normal", "Male", 0.3f, 6.5f, 325);
		
		List<Pokemon> db = List.of(a, b, c, d, e);
		// WHEN
		Mockito.when(this.repo.findByOrderByNdexDesc()).thenReturn(db);
		// THEN
		assertThat(this.services.sortByNdexDesc()).isEqualTo(db);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByOrderByNdexDesc();
	}
	
	@Test
	void sortByNdexAscTest() {
		// GIVEN
		Pokemon a = new Pokemon(1L, "001", "Bulbasaur", "Grass Poison", "Male", 0.7f, 6.9f, 318);
		Pokemon b = new Pokemon(2L, "004", "Charmander", "Fire", "Male", 0.6f, 8.5f, 309);
		Pokemon c = new Pokemon(3L, "007", "Squirtle", "Water", "Male", 0.5f, 9.0f, 314);
		Pokemon d = new Pokemon(4L, "025", "Pikachu", "Electric", "Female", 0.4f, 6.0f, 320);
		Pokemon e = new Pokemon(5L, "133", "Eevee", "Normal", "Male", 0.3f, 6.5f, 325);
		
		List<Pokemon> db = List.of(a, b, c, d, e);
		// WHEN
		Mockito.when(this.repo.findByOrderByNdexAsc()).thenReturn(db);
		// THEN
		assertThat(this.services.sortByNdexAsc()).isEqualTo(db);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByOrderByNdexAsc();
	}
	
	@Test
	void checkTypeEffectivenessEndTest() {
		//GIVEN
		Pokemon test = new Pokemon(999L, "999", "Random", "RandomType", "Male", 9.9f, 9.9f, 999);
		String result = "A Pokemon of this type doesn't exist yet.";
		// WHEN
		Mockito.when(this.repo.findByName("Random")).thenReturn(test);
		// THEN
		assertThat(this.services.checkTypeEffectiveness("Random")).isEqualTo(result);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("Random");
	}
	
	@Test
	void checkTypeEffectivenessNormalTest() {
		//GIVEN
		Pokemon test = new Pokemon(999L, "999", "NormalMon", "Normal", "Male", 9.9f, 9.9f, 999);
		String result = "NormalMon is weak to: Fighting type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("NormalMon")).thenReturn(test);
		// THEN
		assertThat(this.services.checkTypeEffectiveness("NormalMon")).isEqualTo(result);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("NormalMon");
	}
	
	@Test
	void checkTypeEffectivenessNormalFightingTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Normal Fighting", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fighting Normal", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fighting, Flying, Psychic and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Fighting, Flying, Psychic and Fairy type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);
		Mockito.when(this.repo.findByName("testMonTwo")).thenReturn(monTwo);
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);
		assertThat(this.services.checkTypeEffectiveness("testMonTwo")).isEqualTo(resultTwo);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonTwo");
	}
	
	@Test
	void checkTypeEffectivenessNormalFlyingTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Normal Flying", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Flying Normal", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Electric, Ice and Rock type moves!";
		String resultTwo = "testMonTwo is weak to: Electric, Ice and Rock type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);
		Mockito.when(this.repo.findByName("testMonTwo")).thenReturn(monTwo);
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);
		assertThat(this.services.checkTypeEffectiveness("testMonTwo")).isEqualTo(resultTwo);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonTwo");
	}
	
	@Test
	void checkTypeEffectivenessNormalPoisonTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Normal Poison", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Poison Normal", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ground and Psychic type moves!";
		String resultTwo = "testMonTwo is weak to: Ground and Psychic type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);
		Mockito.when(this.repo.findByName("testMonTwo")).thenReturn(monTwo);
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);
		assertThat(this.services.checkTypeEffectiveness("testMonTwo")).isEqualTo(resultTwo);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonTwo");
	}
	
	@Test
	void checkTypeEffectivenessNormalGroundTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Normal Ground", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Ground Normal", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Grass, Ice and Fighting type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Grass, Ice and Fighting type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);
		Mockito.when(this.repo.findByName("testMonTwo")).thenReturn(monTwo);
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);
		assertThat(this.services.checkTypeEffectiveness("testMonTwo")).isEqualTo(resultTwo);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonTwo");
	}
	
	@Test
	void checkTypeEffectivenessNormalRockTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Normal Rock", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Rock Normal", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Grass, Fighting (4x), Ground and Steel type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Grass, Fighting (4x), Ground and Steel type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);
		Mockito.when(this.repo.findByName("testMonTwo")).thenReturn(monTwo);
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);
		assertThat(this.services.checkTypeEffectiveness("testMonTwo")).isEqualTo(resultTwo);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonTwo");
	}
	
	@Test
	void checkTypeEffectivenessNormalBugTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Normal Bug", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Bug Normal", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Flying and Rock type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Flying and Rock type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);
		Mockito.when(this.repo.findByName("testMonTwo")).thenReturn(monTwo);
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);
		assertThat(this.services.checkTypeEffectiveness("testMonTwo")).isEqualTo(resultTwo);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonTwo");
	}
	
	@Test
	void checkTypeEffectivenessNormalGhostTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Normal Ghost", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Ghost Normal", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Dark type moves!";
		String resultTwo = "testMonTwo is weak to: Dark type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);
		Mockito.when(this.repo.findByName("testMonTwo")).thenReturn(monTwo);
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);
		assertThat(this.services.checkTypeEffectiveness("testMonTwo")).isEqualTo(resultTwo);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonTwo");
	}
	
	@Test
	void checkTypeEffectivenessNormalSteelTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Normal Steel", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Steel Normal", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Fighting (4x) and Ground type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Fighting (4x) and Ground type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);
		Mockito.when(this.repo.findByName("testMonTwo")).thenReturn(monTwo);
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);
		assertThat(this.services.checkTypeEffectiveness("testMonTwo")).isEqualTo(resultTwo);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonTwo");
	}
	
	@Test
	void checkTypeEffectivenessNormalFireTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Normal Fire", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fire Normal", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Fighting, Ground and Rock type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Fighting, Ground and Rock type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);
		Mockito.when(this.repo.findByName("testMonTwo")).thenReturn(monTwo);
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);
		assertThat(this.services.checkTypeEffectiveness("testMonTwo")).isEqualTo(resultTwo);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonTwo");
	}
	
	@Test
	void checkTypeEffectivenessNormalWaterTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Normal Water", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Water Normal", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Electric, Grass and Fighting type moves!";
		String resultTwo = "testMonTwo is weak to: Electric, Grass and Fighting type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);
		Mockito.when(this.repo.findByName("testMonTwo")).thenReturn(monTwo);
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);
		assertThat(this.services.checkTypeEffectiveness("testMonTwo")).isEqualTo(resultTwo);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonTwo");
	}
	
	@Test
	void checkTypeEffectivenessNormalGrassTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Normal Grass", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Grass Normal", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Ice, Fighting, Poison, Flying and Bug type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Ice, Fighting, Poison, Flying and Bug type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);
		Mockito.when(this.repo.findByName("testMonTwo")).thenReturn(monTwo);
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);
		assertThat(this.services.checkTypeEffectiveness("testMonTwo")).isEqualTo(resultTwo);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonTwo");
	}
	
	@Test
	void checkTypeEffectivenessNormalElectricTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Normal Electric", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Electric Normal", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fighting and Ground type moves!";
		String resultTwo = "testMonTwo is weak to: Fighting and Ground type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);
		Mockito.when(this.repo.findByName("testMonTwo")).thenReturn(monTwo);
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);
		assertThat(this.services.checkTypeEffectiveness("testMonTwo")).isEqualTo(resultTwo);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonTwo");
	}
	
	@Test
	void checkTypeEffectivenessNormalPsychicTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Normal Psychic", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Psychic Normal", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Bug and Steel type moves!";
		String resultTwo = "testMonTwo is weak to: Bug and Steel type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);
		Mockito.when(this.repo.findByName("testMonTwo")).thenReturn(monTwo);
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);
		assertThat(this.services.checkTypeEffectiveness("testMonTwo")).isEqualTo(resultTwo);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonTwo");
	}
	
	@Test
	void checkTypeEffectivenessNormalIceTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Normal Ice", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Ice Normal", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Fighting (4x), Rock and Steel type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Fighting (4x), Rock and Steel type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);
		Mockito.when(this.repo.findByName("testMonTwo")).thenReturn(monTwo);
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);
		assertThat(this.services.checkTypeEffectiveness("testMonTwo")).isEqualTo(resultTwo);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonTwo");
	}
	
	@Test
	void checkTypeEffectivenessNormalDragonTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Normal Dragon", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dragon Normal", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ice, Fighting, Dragon and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Ice, Fighting, Dragon and Fairy type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);
		Mockito.when(this.repo.findByName("testMonTwo")).thenReturn(monTwo);
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);
		assertThat(this.services.checkTypeEffectiveness("testMonTwo")).isEqualTo(resultTwo);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonTwo");
	}
	
	@Test
	void checkTypeEffectivenessNormalDarkTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Normal Dark", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dark Normal", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fighting (4x), Bug and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Fighting (4x), Bug and Fairy type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);
		Mockito.when(this.repo.findByName("testMonTwo")).thenReturn(monTwo);
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);
		assertThat(this.services.checkTypeEffectiveness("testMonTwo")).isEqualTo(resultTwo);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonTwo");
	}
	
	@Test
	void checkTypeEffectivenessNormalFairyTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Normal Fairy", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fairy Normal", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Poison and Steel type moves!";
		String resultTwo = "testMonTwo is weak to: Poison and Steel type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);
		Mockito.when(this.repo.findByName("testMonTwo")).thenReturn(monTwo);
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);
		assertThat(this.services.checkTypeEffectiveness("testMonTwo")).isEqualTo(resultTwo);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonTwo");
	}
}
