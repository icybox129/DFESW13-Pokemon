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
	
	// =======================================================================================================
	// NORMAL TYPE TESTING
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
	
	// =======================================================================================================
	// FIGHTING TYPE TESTING
	
	@Test
	void checkTypeEffectivenessFightingTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fighting", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Flying, Psychic and Fairy type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);	
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);	
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
	}
	
	@Test
	void checkTypeEffectivenessFightingFlyingTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fighting Flying", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Flying Fighting", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Electric, Ice, Flying Psychic and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Electric, Ice, Flying Psychic and Fairy type moves!";
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
	void checkTypeEffectivenessFightingPoisonTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fighting Poison", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Poison Fighting", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ground, Flying and Psychic type moves!";
		String resultTwo = "testMonTwo is weak to: Ground, Flying and Psychic type moves!";
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
	void checkTypeEffectivenessFightingGroundTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fighting Ground", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Ground Fighting", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Grass, Ice, Flying, Psychic and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Grass, Ice, Flying, Psychic and Fairy type moves!";
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
	void checkTypeEffectivenessFightingRockTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fighting Rock", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Rock Fighting", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Grass, Fighting, Ground, Psychic, Steel and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Grass, Fighting, Ground, Psychic, Steel and Fairy type moves!";
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
	void checkTypeEffectivenessFightingBugTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fighting Bug", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Bug Fighting", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Flying (4x), Psychic and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Flying (4x), Psychic and Fairy type moves!";
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
	void checkTypeEffectivenessFightingGhostTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fighting Ghost", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Ghost Fighting", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Flying, Psychic, Ghost and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Flying, Psychic, Ghost and Fairy type moves!";
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
	void checkTypeEffectivenessFightingSteelTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fighting Steel", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Steel Fighting", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Fighting and Ground type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Fighting and Ground type moves!";
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
	void checkTypeEffectivenessFightingFireTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fighting Fire", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fire Fighting", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Ground, Flying and Psychic type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Ground, Flying and Psychic type moves!";
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
	void checkTypeEffectivenessFightingWaterTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fighting Water", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Water Fighting", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Electric, Grass, Flying, Psychic and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Electric, Grass, Flying, Psychic and Fairy type moves!";
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
	void checkTypeEffectivenessFightingGrassTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fighting Grass", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Grass Fighting", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Ice, Poison, Flying (4x), Psychic and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Ice, Poison, Flying (4x), Psychic and Fairy type moves!";
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
	void checkTypeEffectivenessFightingElectricTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fighting Electric", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Electric Fighting", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ground, Psychic and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Ground, Psychic and Fairy type moves!";
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
	void checkTypeEffectivenessFightingPsychicTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fighting Psychic", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Psychic Fighting", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Flying, Ghost and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Flying, Ghost and Fairy type moves!";
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
	void checkTypeEffectivenessFightingIceTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fighting Ice", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Ice Fighting", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Fighting, Flying, Psychic, Steel and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Fighting, Flying, Psychic, Steel and Fairy type moves!";
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
	void checkTypeEffectivenessFightingDragonTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fighting Dragon", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dragon Fighting", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ice, Flying, Psychic, Dragon and Fairy (4x) type moves!";
		String resultTwo = "testMonTwo is weak to: Ice, Flying, Psychic, Dragon and Fairy (4x) type moves!";
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
	void checkTypeEffectivenessFightingDarkTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fighting Dark", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dark Fighting", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fighting, Flying and Fairy (4x) type moves!";
		String resultTwo = "testMonTwo is weak to: Fighting, Flying and Fairy (4x) type moves!";
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
	void checkTypeEffectivenessFightingFairyTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fighting Fairy", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fairy Fighting", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Poison, Flying, Psychic, Steel and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Poison, Flying, Psychic, Steel and Fairy type moves!";
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
	
	// =======================================================================================================
	// FLYING TYPE TESTING
	
	@Test
	void checkTypeEffectivenessFlyingTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Flying", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Electric, Ice and Rock type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);	
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);	
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
	}
	
	@Test
	void checkTypeEffectivenessFlyingPoisonTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Flying Poison", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Poison Flying", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Electric, Ice, Psychic and Rock type moves!";
		String resultTwo = "testMonTwo is weak to: Electric, Ice, Psychic and Rock type moves!";
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
	void checkTypeEffectivenessFlyingGroundTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Flying Ground", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Ground Flying", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water and Ice (4x) type moves!";
		String resultTwo = "testMonTwo is weak to: Water and Ice (4x) type moves!";
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
	void checkTypeEffectivenessFlyingRockTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Flying Rock", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Rock Flying", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Electric, Ice, Rock and Steel type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Electric, Ice, Rock and Steel type moves!";
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
	void checkTypeEffectivenessFlyingBugTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Flying Bug", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Bug Flying", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Electric, Ice, Flying and Rock (4x) type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Electric, Ice, Flying and Rock (4x) type moves!";
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
	void checkTypeEffectivenessFlyingGhostTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Flying Ghost", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Ghost Flying", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Electric, Ice, Rock, Ghost and Dark type moves!";
		String resultTwo = "testMonTwo is weak to: Electric, Ice, Rock, Ghost and Dark type moves!";
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
	void checkTypeEffectivenessFlyingSteelTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Flying Steel", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Steel Flying", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire and Electric type moves!";
		String resultTwo = "testMonTwo is weak to: Fire and Electric type moves!";
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
	void checkTypeEffectivenessFlyingFireTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Flying Fire", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fire Flying", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Electric and Rock (4x) type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Electric and Rock (4x) type moves!";
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
	void checkTypeEffectivenessFlyingWaterTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Flying Water", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Water Flying", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Electric (4x) and Rock type moves!";
		String resultTwo = "testMonTwo is weak to: Electric (4x) and Rock type moves!";
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
	void checkTypeEffectivenessFlyingGrassTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Flying Grass", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Grass Flying", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Ice (4x), Poison, Flying and Rock type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Ice (4x), Poison, Flying and Rock type moves!";
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
	void checkTypeEffectivenessFlyingElectricTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Flying Electric", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Electric Flying", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ice and Rock type moves!";
		String resultTwo = "testMonTwo is weak to: Ice and Rock type moves!";
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
	void checkTypeEffectivenessFlyingIceTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Flying Ice", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Ice Flying", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Electric and Rock (4x) type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Electric and Rock (4x) type moves!";
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
	void checkTypeEffectivenessFlyingDragonTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Flying Dragon", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dragon Flying", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ice (4x), Rock, Dragon and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Ice (4x), Rock, Dragon and Fairy type moves!";
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
	void checkTypeEffectivenessFlyingDarkTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Flying Dark", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dark Flying", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Electric, Ice, Rock and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Electric, Ice, Rock and Fairy type moves!";
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
	void checkTypeEffectivenessFlyingFairyTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Flying Fairy", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fairy Flying", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Electric, Ice, Poison, Rock and Steel type moves!";
		String resultTwo = "testMonTwo is weak to: Electric, Ice, Poison, Rock and Steel type moves!";
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
	
	// =======================================================================================================
	// POISON TYPE TESTING
	
	@Test
	void checkTypeEffectivenessPoisonTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Poison", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ground and Psychic type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);	
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);	
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
	}
	
	@Test
	void checkTypeEffectivenessPoisonGroundTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Poison Ground", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Ground Poison", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Ice, Ground and Psychic type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Ice, Ground and Psychic type moves!";
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
	void checkTypeEffectivenessPoisonRockTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Poison Rock", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Rock Poison", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Ground (4x), Psychic and Steel type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Ground (4x), Psychic and Steel type moves!";
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
	void checkTypeEffectivenessPoisonBugTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Poison Bug", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Bug Poison", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Flying, Psychic and Rock type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Flying, Psychic and Rock type moves!";
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
	void checkTypeEffectivenessPoisonGhostTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Poison Ghost", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Ghost Poison", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ground, Psychic, Ghost and Dark type moves!";
		String resultTwo = "testMonTwo is weak to: Ground, Psychic, Ghost and Dark type moves!";
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
	void checkTypeEffectivenessPoisonSteelTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Poison Steel", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Steel Poison", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire and Ground (4x) type moves!";
		String resultTwo = "testMonTwo is weak to: Fire and Ground (4x) type moves!";
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
	void checkTypeEffectivenessPoisonFireTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Poison Fire", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fire Poison", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Ground (4x), Psychic and Rock type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Ground (4x), Psychic and Rock type moves!";
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
	void checkTypeEffectivenessPoisonWaterTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Poison Water", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Water Poison", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Electric, Ground and Psychic type moves!";
		String resultTwo = "testMonTwo is weak to: Electric, Ground and Psychic type moves!";
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
	void checkTypeEffectivenessPoisonGrassTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Poison Grass", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Grass Poison", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Ice, Flying and Psychic type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Ice, Flying and Psychic type moves!";
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
	void checkTypeEffectivenessPoisonElectricTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Poison Electric", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Electric Poison", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ground (4x) and Psychic type moves!";
		String resultTwo = "testMonTwo is weak to: Ground (4x) and Psychic type moves!";
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
	void checkTypeEffectivenessPoisonPsychicTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Poison Psychic", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Psychic Poison", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ground, Ghost and Dark type moves!";
		String resultTwo = "testMonTwo is weak to: Ground, Ghost and Dark type moves!";
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
	void checkTypeEffectivenessPoisonIceTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Poison Ice", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Ice Poison", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Ground, Psychic, Rock and Steel type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Ground, Psychic, Rock and Steel type moves!";
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
	void checkTypeEffectivenessPoisonDragonTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Poison Dragon", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dragon Poison", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ice, Ground, Psychic and Dragon type moves!";
		String resultTwo = "testMonTwo is weak to: Ice, Ground, Psychic and Dragon type moves!";
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
	void checkTypeEffectivenessPoisonDarkTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Poison Dark", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dark Poison", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ground type moves!";
		String resultTwo = "testMonTwo is weak to: Ground type moves!";
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
	void checkTypeEffectivenessPoisonFairyTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Poison Fairy", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fairy Poison", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ground, Psychic and Steel type moves!";
		String resultTwo = "testMonTwo is weak to: Ground, Psychic and Steel type moves!";
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
