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

	// =======================================================================================================
	// GROUND TYPE TESTING
	
	@Test
	void checkTypeEffectivenessGroundTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ground", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Grass and Ice type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);	
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);	
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
	}
	
	@Test
	void checkTypeEffectivenessGroundRockTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ground Rock", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Rock Ground", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water (4x), Grass (4x), Ice, Fighting, Ground and Steel type moves!";
		String resultTwo = "testMonTwo is weak to: Water (4x), Grass (4x), Ice, Fighting, Ground and Steel type moves!";
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
	void checkTypeEffectivenessGroundBugTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ground Bug", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Bug Ground", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Water, Ice and Flying type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Water, Ice and Flying type moves!";
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
	void checkTypeEffectivenessGroundGhostTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ground Ghost", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Ghost Ground", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Grass, Ice, Ghost and Dark type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Grass, Ice, Ghost and Dark type moves!";
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
	void checkTypeEffectivenessGroundSteelTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ground Steel", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Steel Ground", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Water, Fighting and Ground type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Water, Fighting and Ground type moves!";
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
	void checkTypeEffectivenessGroundFireTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ground Fire", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fire Ground", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water (4x) and Ground type moves!";
		String resultTwo = "testMonTwo is weak to: Water (4x) and Ground type moves!";
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
	void checkTypeEffectivenessGroundWaterTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ground Water", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Water Ground", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Grass (4x) type moves!";
		String resultTwo = "testMonTwo is weak to: Grass (4x) type moves!";
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
	void checkTypeEffectivenessGroundGrassTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ground Grass", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Grass Ground", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Ice (4x), Flying and Bug type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Ice (4x), Flying and Bug type moves!";
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
	void checkTypeEffectivenessGroundElectricTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ground Electric", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Electric Ground", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Grass, Ice and Ground type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Grass, Ice and Ground type moves!";
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
	void checkTypeEffectivenessGroundPsychicTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ground Psychic", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Psychic Ground", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Grass, Ice, Bug, Ghost and Dark type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Grass, Ice, Bug, Ghost and Dark type moves!";
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
	void checkTypeEffectivenessGroundIceTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ground Ice", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Ice Ground", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Water, Grass and Fighting type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Water, Grass and Fighting type moves!";
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
	void checkTypeEffectivenessGroundDragonTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ground Dragon", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dragon Ground", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ice (4x), Dragon and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Ice (4x), Dragon and Fairy type moves!";
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
	void checkTypeEffectivenessGroundDarkTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ground Dark", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dark Ground", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Grass, Ice, Fighting, Bug and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Grass, Ice, Fighting, Bug and Fairy type moves!";
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
	void checkTypeEffectivenessGroundFairyTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ground Fairy", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fairy Ground", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Grass, Ice and Steel type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Grass, Ice and Steel type moves!";
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
	// ROCK TYPE TESTING
	
	@Test
	void checkTypeEffectivenessRockTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Rock", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Grass, Fighting, Ground and Steel type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);	
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);	
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
	}
	
	@Test
	void checkTypeEffectivenessRockBugTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Rock Bug", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Bug Rock", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Rock and Steel type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Rock and Steel type moves!";
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
	void checkTypeEffectivenessRockGhostTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Rock Ghost", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Ghost Rock", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Grass, Ground, Ghost, Dark and Steel type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Grass, Ground, Ghost, Dark and Steel type moves!";
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
	void checkTypeEffectivenessRockSteelTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Rock Steel", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Steel Rock", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Fighting (4x) and Ground (4x) type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Fighting (4x) and Ground (4x) type moves!";
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
	void checkTypeEffectivenessRockFireTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Rock Fire", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fire Rock", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water (4x), Fighting and Ground (4x) type moves!";
		String resultTwo = "testMonTwo is weak to: Water (4x), Fighting and Ground (4x) type moves!";
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
	void checkTypeEffectivenessRockWaterTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Rock Water", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Water Rock", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Electric, Grass (4x), Fighting and Ground type moves!";
		String resultTwo = "testMonTwo is weak to: Electric, Grass (4x), Fighting and Ground type moves!";
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
	void checkTypeEffectivenessRockGrassTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Rock Grass", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Grass Rock", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ice, Fighting, Bug and Steel type moves!";
		String resultTwo = "testMonTwo is weak to: Ice, Fighting, Bug and Steel type moves!";
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
	void checkTypeEffectivenessRockElectricTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Rock Electric", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Electric Rock", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Grass, Fighting and Ground (4x) type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Grass, Fighting and Ground (4x) type moves!";
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
	void checkTypeEffectivenessRockPsychicTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Rock Psychic", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Psychic Rock", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Grass, Ground, Bug, Ghost, Dark and Steel type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Grass, Ground, Bug, Ghost, Dark and Steel type moves!";
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
	void checkTypeEffectivenessRockIceTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Rock Ice", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Ice Rock", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Grass, Fighting (4x), Ground, Rock and Steel (4x) type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Grass, Fighting (4x), Ground, Rock and Steel (4x) type moves!";
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
	void checkTypeEffectivenessRockDragonTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Rock Dragon", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dragon Rock", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ice, Fighting, Ground, Dragon, Steel and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Ice, Fighting, Ground, Dragon, Steel and Fairy type moves!";
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
	void checkTypeEffectivenessRockDarkTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Rock Dark", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dark Rock", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Grass, Fighting (4x), Ground, Bug, Steel and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Grass, Fighting (4x), Ground, Bug, Steel and Fairy type moves!";
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
	void checkTypeEffectivenessRockFairyTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Rock Fairy", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fairy Rock", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Grass, Ground and Steel (4x) type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Grass, Ground and Steel (4x) type moves!";
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
	// BUG TYPE TESTING
	
	@Test
	void checkTypeEffectivenessBugTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Bug", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Flying and Rock type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);	
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);	
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
	}
	
	@Test
	void checkTypeEffectivenessBugGhostTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Bug Ghost", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Ghost Bug", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Flying, Rock, Ghost and Dark type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Flying, Rock, Ghost and Dark type moves!";
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
	void checkTypeEffectivenessBugSteelTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Bug Steel", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Steel Bug", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire (4x) type moves!";
		String resultTwo = "testMonTwo is weak to: Fire (4x) type moves!";
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
	void checkTypeEffectivenessBugFireTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Bug Fire", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fire Bug", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Flying and Rock (4x) type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Flying and Rock (4x) type moves!";
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
	void checkTypeEffectivenessBugWaterTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Bug Water", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Water Bug", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Electric, Flying and Rock type moves!";
		String resultTwo = "testMonTwo is weak to: Electric, Flying and Rock type moves!";
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
	void checkTypeEffectivenessBugGrassTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Bug Grass", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Grass Bug", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire (4x), Ice, Poison, Flying (4x) Bug and Rock type moves!";
		String resultTwo = "testMonTwo is weak to: Fire (4x), Ice, Poison, Flying (4x) Bug and Rock type moves!";
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
	void checkTypeEffectivenessBugElectricTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Bug Electric", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Electric Bug", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire and Rock type moves!";
		String resultTwo = "testMonTwo is weak to: Fire and Rock type moves!";
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
	void checkTypeEffectivenessBugPsychicTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Bug Psychic", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Psychic Bug", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Flying, Bug, Rock, Ghost and Dark type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Flying, Bug, Rock, Ghost and Dark type moves!";
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
	void checkTypeEffectivenessBugIceTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Bug Ice", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Ice Bug", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire (4x), Flying, Rock (4x) and Steel type moves!";
		String resultTwo = "testMonTwo is weak to: Fire (4x), Flying, Rock (4x) and Steel type moves!";
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
	void checkTypeEffectivenessBugDragonTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Bug Dragon", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dragon Bug", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ice, Flying, Rock, Dragon and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Ice, Flying, Rock, Dragon and Fairy type moves!";
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
	void checkTypeEffectivenessBugDarkTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Bug Dark", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dark Bug", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Flying, Bug, Rock and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Flying, Bug, Rock and Fairy type moves!";
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
	void checkTypeEffectivenessBugFairyTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Bug Fairy", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fairy Bug", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Poison, Flying, Rock and Steel type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Poison, Flying, Rock and Steel type moves!";
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
	// GHOST TYPE TESTING
	
	@Test
	void checkTypeEffectivenessGhostTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ghost", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ghost and Dark type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);	
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);	
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
	}
	
	@Test
	void checkTypeEffectivenessGhostSteelTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ghost Steel", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Steel Ghost", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Ground, Ghost and Dark type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Ground, Ghost and Dark type moves!";
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
	void checkTypeEffectivenessGhostFireTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ghost Fire", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fire Ghost", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Ground, Rock, Ghost and Dark type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Ground, Rock, Ghost and Dark type moves!";
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
	void checkTypeEffectivenessGhostWaterTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ghost Water", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Water Ghost", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Electric, Grass, Ghost and Dark type moves!";
		String resultTwo = "testMonTwo is weak to: Electric, Grass, Ghost and Dark type moves!";
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
	void checkTypeEffectivenessGhostGrassTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ghost Grass", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Grass Ghost", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Ice, Flying, Ghost and Dark type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Ice, Flying, Ghost and Dark type moves!";
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
	void checkTypeEffectivenessGhostElectricTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ghost Electric", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Electric Ghost", "Male", 9.9f, 9.9f, 999);
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
	void checkTypeEffectivenessGhostPsychicTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ghost Psychic", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Psychic Ghost", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ghost (4x) and Dark (4x) type moves!";
		String resultTwo = "testMonTwo is weak to: Ghost (4x) and Dark (4x) type moves!";
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
	void checkTypeEffectivenessGhostIceTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ghost Ice", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Ice Ghost", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Rock, Ghost, Dark and Steel type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Rock, Ghost, Dark and Steel type moves!";
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
	void checkTypeEffectivenessGhostDragonTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ghost Dragon", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dragon Ghost", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ice, Ghost, Dragon, Dark and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Ice, Ghost, Dragon, Dark and Fairy type moves!";
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
	void checkTypeEffectivenessGhostDarkTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ghost Dark", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dark Ghost", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Fairy type moves!";
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
	void checkTypeEffectivenessGhostFairyTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ghost Fairy", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fairy Ghost", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ghost and Steel type moves!";
		String resultTwo = "testMonTwo is weak to: Ghost and Steel type moves!";
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
	// STEEL TYPE TESTING
	
	@Test
	void checkTypeEffectivenessSteelTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Steel", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Fighting and Ground type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);	
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);	
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
	}
	
	@Test
	void checkTypeEffectivenessSteelFireTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Steel Fire", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fire Steel", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Fighting and Ground (4x) type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Fighting and Ground (4x) type moves!";
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
	void checkTypeEffectivenessSteelWaterTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Steel Water", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Water Steel", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Electric, Fighting and Ground type moves!";
		String resultTwo = "testMonTwo is weak to: Electric, Fighting and Ground type moves!";
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
	void checkTypeEffectivenessSteelGrassTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Steel Grass", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Grass Steel", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire (4x) and Fighting type moves!";
		String resultTwo = "testMonTwo is weak to: Fire (4x) and Fighting type moves!";
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
	void checkTypeEffectivenessSteelElectricTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Steel Electric", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Electric Steel", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Fighting and Ground (4x) type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Fighting and Ground (4x) type moves!";
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
	void checkTypeEffectivenessSteelPsychicTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Steel Psychic", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Psychic Steel", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Ground, Ghost and Dark type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Ground, Ghost and Dark type moves!";
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
	void checkTypeEffectivenessSteelIceTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Steel Ice", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Ice Steel", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire (4x), Fighting (4x) and Ground type moves!";
		String resultTwo = "testMonTwo is weak to: Fire (4x), Fighting (4x) and Ground type moves!";
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
	void checkTypeEffectivenessSteelDragonTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Steel Dragon", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dragon Steel", "Male", 9.9f, 9.9f, 999);
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
	void checkTypeEffectivenessSteelDarkTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Steel Dark", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dark Steel", "Male", 9.9f, 9.9f, 999);
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
	void checkTypeEffectivenessSteelFairyTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Steel Fairy", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fairy Steel", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire and Ground type moves!";
		String resultTwo = "testMonTwo is weak to: Fire and Ground type moves!";
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
	// FIRE TYPE TESTING
	
	@Test
	void checkTypeEffectivenessFireTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fire", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Ground and Rock type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);	
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);	
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
	}
	
	@Test
	void checkTypeEffectivenessFireWaterTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fire Water", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Water Fire", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Electric, Ground and Rock type moves!";
		String resultTwo = "testMonTwo is weak to: Electric, Ground and Rock type moves!";
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
	void checkTypeEffectivenessFireGrassTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fire Grass", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Grass Fire", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Poison, Flying and Rock type moves!";
		String resultTwo = "testMonTwo is weak to: Poison, Flying and Rock type moves!";
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
	void checkTypeEffectivenessFireElectricTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fire Electric", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Electric Fire", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Ground (4x) and Rock type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Ground (4x) and Rock type moves!";
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
	void checkTypeEffectivenessFirePsychicTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fire Psychic", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Psychic Fire", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Ground, Rock, Ghost and Dark type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Ground, Rock, Ghost and Dark type moves!";
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
	void checkTypeEffectivenessFireIceTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fire Ice", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Ice Fire", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Fighting, Ground and Rock (4x) type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Fighting, Ground and Rock (4x) type moves!";
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
	void checkTypeEffectivenessFireDragonTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fire Dragon", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dragon Fire", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ground, Rock and Dragon type moves!";
		String resultTwo = "testMonTwo is weak to: Ground, Rock and Dragon type moves!";
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
	void checkTypeEffectivenessFireDarkTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fire Dark", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dark Fire", "Male", 9.9f, 9.9f, 999);
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
	void checkTypeEffectivenessFireFairyTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fire Fairy", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fairy Fire", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Water, Poison, Ground and Rock type moves!";
		String resultTwo = "testMonTwo is weak to: Water, Poison, Ground and Rock type moves!";
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
	// WATER TYPE TESTING
	
	@Test
	void checkTypeEffectivenessWaterTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Water", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Electric and Grass type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);	
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);	
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
	}
	
	@Test
	void checkTypeEffectivenessWaterGrassTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Water Grass", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Grass Water", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Poison, Flying and Bug type moves!";
		String resultTwo = "testMonTwo is weak to: Poison, Flying and Bug type moves!";
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
	void checkTypeEffectivenessWaterElectricTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Water Electric", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Electric Water", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Grass and Ground type moves!";
		String resultTwo = "testMonTwo is weak to: Grass and Ground type moves!";
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
	void checkTypeEffectivenessWaterPsychicTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Water Psychic", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Psychic Water", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Electric, Grass, Bug, Ghost and Dark type moves!";
		String resultTwo = "testMonTwo is weak to: Electric, Grass, Bug, Ghost and Dark type moves!";
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
	void checkTypeEffectivenessWaterIceTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Water Ice", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Ice Water", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Electric, Grass, Fighting and Rock type moves!";
		String resultTwo = "testMonTwo is weak to: Electric, Grass, Fighting and Rock type moves!";
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
	void checkTypeEffectivenessWaterDragonTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Water Dragon", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dragon Water", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Dragon and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Dragon and Fairy type moves!";
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
	void checkTypeEffectivenessWaterDarkTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Water Dark", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dark Water", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Electric, Grass, Fighting, Bug and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Electric, Grass, Fighting, Bug and Fairy type moves!";
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
	void checkTypeEffectivenessWaterFairyTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Water Fairy", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fairy Water", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Electric, Grass and Poison type moves!";
		String resultTwo = "testMonTwo is weak to: Electric, Grass and Poison type moves!";
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
	// GRASS TYPE TESTING
	
	@Test
	void checkTypeEffectivenessGrassTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Grass", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Ice, Poison, Flying and Bug type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);	
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);	
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
	}
	
	@Test
	void checkTypeEffectivenessGrassElectricTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Grass Electric", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Electric Grass", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Ice, Poison and Bug type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Ice, Poison and Bug type moves!";
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
	void checkTypeEffectivenessGrassPsychicTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Grass Psychic", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Psychic Grass", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Ice, Poison, Flying, Bug (4x), Ghost and Dark type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Ice, Poison, Flying, Bug (4x), Ghost and Dark type moves!";
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
	void checkTypeEffectivenessGrassIceTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Grass Ice", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Ice Grass", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire (4x), Fighting, Poison, Flying, Bug, Rock and steel type moves!";
		String resultTwo = "testMonTwo is weak to: Fire (4x), Fighting, Poison, Flying, Bug, Rock and steel type moves!";
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
	void checkTypeEffectivenessGrassDragonTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Grass Dragon", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dragon Grass", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ice (4x), Poison, Flying, Bug, Dragon and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Ice (4x), Poison, Flying, Bug, Dragon and Fairy type moves!";
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
	void checkTypeEffectivenessGrassDarkTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Grass Dark", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dark Grass", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Ice, Fighting, Poison, Flying, Bug (4x) and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Ice, Fighting, Poison, Flying, Bug (4x) and Fairy type moves!";
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
	void checkTypeEffectivenessGrassFairyTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Grass Fairy", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fairy Grass", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Ice, Poison (4x), Flying and Steel type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Ice, Poison (4x), Flying and Steel type moves!";
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
	// ELECTRIC TYPE TESTING
	
	@Test
	void checkTypeEffectivenessElectricTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Electric", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ground type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);	
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);	
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
	}
	
	@Test
	void checkTypeEffectivenessElectricPsychicTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Electric Psychic", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Psychic Electric", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ground, Bug, Ghost and Dark type moves!";
		String resultTwo = "testMonTwo is weak to: Ground, Bug, Ghost and Dark type moves!";
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
	void checkTypeEffectivenessElectricIceTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Electric Ice", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Ice Electric", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Fighting, Ground and Rock type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Fighting, Ground and Rock type moves!";
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
	void checkTypeEffectivenessElectricDragonTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Electric Dragon", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dragon Electric", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ice, Ground, Dragon and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Ice, Ground, Dragon and Fairy type moves!";
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
	void checkTypeEffectivenessElectricDarkTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Electric Dark", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dark Electric", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fighting, Ground, Bug and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Fighting, Ground, Bug and Fairy type moves!";
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
	void checkTypeEffectivenessElectricFairyTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Electric Fairy", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fairy Electric", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Poison and Ground type moves!";
		String resultTwo = "testMonTwo is weak to: Poison and Ground type moves!";
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
	// PSYCHIC TYPE TESTING
	
	@Test
	void checkTypeEffectivenessPsychicTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Psychic", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Bug, Ghost and Dark type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);	
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);	
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
	}
	
	@Test
	void checkTypeEffectivenessPsychicIceTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Psychic Ice", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Ice Psychic", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Bug, Rock, Ghost, Dark and Steel type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Bug, Rock, Ghost, Dark and Steel type moves!";
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
	void checkTypeEffectivenessPsychicDragonTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Psychic Dragon", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dragon Psychic", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ice, Bug, Ghost, Dragon, Dark and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Ice, Bug, Ghost, Dragon, Dark and Fairy type moves!";
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
	void checkTypeEffectivenessPsychicDarkTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Psychic Dark", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dark Psychic", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Bug (4x) and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Bug (4x) and Fairy type moves!";
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
	void checkTypeEffectivenessPsychicFairyTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Psychic Fairy", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fairy Psychic", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Poison, Ghost and Steel type moves!";
		String resultTwo = "testMonTwo is weak to: Poison, Ghost and Steel type moves!";
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
	// PSYCHIC TYPE TESTING
	
	@Test
	void checkTypeEffectivenessIceTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ice", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Fighting, Rock and Steel type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);	
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);	
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
	}
	
	@Test
	void checkTypeEffectivenessIceDragonTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ice Dragon", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dragon Ice", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fighting, Rock, Dragon, Steel and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Fighting, Rock, Dragon, Steel and Fairy type moves!";
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
	void checkTypeEffectivenessIceDarkTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ice Dark", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dark Ice", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Fighting (4x), Bug, Rock, Steel and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Fighting (4x), Bug, Rock, Steel and Fairy type moves!";
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
	void checkTypeEffectivenessIceFairyTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Ice Fairy", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fairy Ice", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fire, Poison, Rock and Steel (4x) type moves!";
		String resultTwo = "testMonTwo is weak to: Fire, Poison, Rock and Steel (4x) type moves!";
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
	// DRAGON TYPE TESTING
	
	@Test
	void checkTypeEffectivenessDragonTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Dragon", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ice, Dragon and Fairy type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);	
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);	
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
	}
	
	@Test
	void checkTypeEffectivenessDragonDarkTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Dragon Dark", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Dark Dragon", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ice, Fighting, Bug, Dragon and Fairy (4x) type moves!";
		String resultTwo = "testMonTwo is weak to: Ice, Fighting, Bug, Dragon and Fairy (4x) type moves!";
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
	void checkTypeEffectivenessDragonFairyTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Dragon Fairy", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fairy Dragon", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Ice, Poison, Steel and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Ice, Poison, Steel and Fairy type moves!";
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
	// DARK TYPE TESTING
	
	@Test
	void checkTypeEffectivenessDarkTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Dark", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Fighting, Bug and Fairy type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);	
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);	
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
	}
	
	@Test
	void checkTypeEffectivenessDarkFairyTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Dark Fairy", "Male", 9.9f, 9.9f, 999);
		Pokemon monTwo = new Pokemon(999L, "999", "testMonTwo", "Fairy Dark", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Poison, Steel and Fairy type moves!";
		String resultTwo = "testMonTwo is weak to: Poison, Steel and Fairy type moves!";
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
	// FAIRY TYPE TESTING
	
	@Test
	void checkTypeEffectivenessFairyTest() {
		//GIVEN
		Pokemon monOne = new Pokemon(999L, "999", "testMonOne", "Fairy", "Male", 9.9f, 9.9f, 999);
		String resultOne = "testMonOne is weak to: Poison and Steel type moves!";
		// WHEN
		Mockito.when(this.repo.findByName("testMonOne")).thenReturn(monOne);	
		// THEN
		assertThat(this.services.checkTypeEffectiveness("testMonOne")).isEqualTo(resultOne);	
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findByName("testMonOne");
	}
	
}
