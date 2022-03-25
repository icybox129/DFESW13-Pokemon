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

	public boolean deleteAll() {
		repo.deleteAll();
		return true;
	}

	public List<Pokemon> getByType(String type) {
		return repo.findByType(type);
	}

	public Pokemon getByName(String name) {
		return repo.findByName(name);
	}
	
	public List<Pokemon> sortByBstDesc() {
		return repo.findByOrderByBstDesc();
	}
	
	public List<Pokemon> sortByBstAsc() {
		return repo.findByOrderByBstAsc();
	}
	
	public List<Pokemon> sortByNdexDesc() {
		return repo.findByOrderByNdexDesc();
	}

	public List<Pokemon> sortByNdexAsc() {
		return repo.findByOrderByNdexAsc();
	}


	public String checkTypeEffectiveness(String name) {
		Pokemon checkMon = getByName(name);
		
		// Normal types
		if (checkMon.getType().equals("Normal")) {
			return checkMon.getName() + " is weak to: Fighting type moves!";
		
		// Used a || operator because "normal fighting" and "fighting normal" are exactly the same
		// technically the type could be entered into the DB either way so this catches both.
		} else if (checkMon.getType().equals("Normal Fighting") || (checkMon.getType().equals("Fighting Normal"))) {
			return checkMon.getName() + " is weak to: Fighting, Flying, Psychic and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Normal Flying") || (checkMon.getType().equals("Flying Normal"))) {
			return checkMon.getName() + " is weak to: Electric, Ice and Rock type moves!";
		
		} else if (checkMon.getType().equals("Normal Poison") || (checkMon.getType().equals("Poison Normal"))) {
			return checkMon.getName() + " is weak to: Ground and Psychic type moves!";
		
		} else if (checkMon.getType().equals("Normal Ground") || (checkMon.getType().equals("Ground Normal"))) {
			return checkMon.getName() + " is weak to: Water, Grass, Ice and Fighting type moves!";
		
		} else if (checkMon.getType().equals("Normal Rock") || (checkMon.getType().equals("Rock Normal"))) {
			return checkMon.getName() + " is weak to: Water, Grass, Fighting (4x), Ground and Steel type moves!";
		
		} else if (checkMon.getType().equals("Normal Bug") || (checkMon.getType().equals("Bug Normal"))) {
			return checkMon.getName() + " is weak to: Fire, Flying and Rock type moves!";
		
		} else if (checkMon.getType().equals("Normal Ghost") || (checkMon.getType().equals("Ghost Normal"))) {
			return checkMon.getName() + " is weak to: Dark type moves!";
		
		} else if (checkMon.getType().equals("Normal Steel") || (checkMon.getType().equals("Steel Normal"))) {
			return checkMon.getName() + " is weak to: Fire, Fighting (4x) and Ground type moves!";
		
		} else if (checkMon.getType().equals("Normal Fire") || (checkMon.getType().equals("Fire Normal"))) {
			return checkMon.getName() + " is weak to: Water, Fighting, Ground and Rock type moves!";
		
		} else if (checkMon.getType().equals("Normal Water") || (checkMon.getType().equals("Water Normal"))) {
			return checkMon.getName() + " is weak to: Electric, Grass and Fighting type moves!";
		
		} else if (checkMon.getType().equals("Normal Grass") || (checkMon.getType().equals("Grass Normal"))) {
			return checkMon.getName() + " is weak to: Fire, Ice, Fighting, Poison, Flying and Bug type moves!";
		
		} else if (checkMon.getType().equals("Normal Electric") || (checkMon.getType().equals("Electric Normal"))) {
			return checkMon.getName() + " is weak to: Fighting and Ground type moves!";
		
		} else if (checkMon.getType().equals("Normal Psychic") || (checkMon.getType().equals("Psychic Normal"))) {
			return checkMon.getName() + " is weak to: Bug and Steel type moves!";
		
		} else if (checkMon.getType().equals("Normal Ice") || (checkMon.getType().equals("Ice Normal"))) {
			return checkMon.getName() + " is weak to: Fire, Fighting (4x), Rock and Steel type moves!";
		
		} else if (checkMon.getType().equals("Normal Dragon") || (checkMon.getType().equals("Dragon Normal"))) {
			return checkMon.getName() + " is weak to: Ice, Fighting, Dragon and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Normal Dark") || (checkMon.getType().equals("Dark Normal"))) {
			return checkMon.getName() + " is weak to: Fighting (4x), Bug and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Normal Fairy") || (checkMon.getType().equals("Fairy Normal"))) {
			return checkMon.getName() + " is weak to: Poison and Steel type moves!";

			
		// Fighting types
		} else if (checkMon.getType().equals("Fighting")) {
			return checkMon.getName() + " is weak to: Flying, Psychic and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Fighting Flying") || (checkMon.getType().equals("Flying Fighting"))) {
			return checkMon.getName() + " is weak to: Electric, Ice, Flying Psychic and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Fighting Poison") || (checkMon.getType().equals("Poison Fighting"))) {
			return checkMon.getName() + " is weak to: Ground, Flying and Psychic type moves!";
		
		} else if (checkMon.getType().equals("Fighting Ground") || (checkMon.getType().equals("Ground Fighting"))) {
			return checkMon.getName() + " is weak to: Water, Grass, Ice, Flying, Psychic and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Fighting Rock") || (checkMon.getType().equals("Rock Fighting"))) {
			return checkMon.getName() + " is weak to: Water, Grass, Fighting, Ground, Psychic, Steel and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Fighting Bug") || (checkMon.getType().equals("Bug Fighting"))) {
			return checkMon.getName() + " is weak to: Fire, Flying (4x), Psychic and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Fighting Ghost") || (checkMon.getType().equals("Ghost Fighting"))) {
			return checkMon.getName() + " is weak to: Flying, Psychic, Ghost and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Fighting Steel") || (checkMon.getType().equals("Steel Fighting"))) {
			return checkMon.getName() + " is weak to: Fire, Fighting and Ground type moves!";
		
		} else if (checkMon.getType().equals("Fighting Fire") || (checkMon.getType().equals("Fire Fighting"))) {
			return checkMon.getName() + " is weak to: Water, Ground, Flying and Psychic type moves!";
		
		} else if (checkMon.getType().equals("Fighting Water") || (checkMon.getType().equals("Water Fighting"))) {
			return checkMon.getName() + " is weak to: Electric, Grass, Flying, Psychic and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Fighting Grass") || (checkMon.getType().equals("Grass Fighting"))) {
			return checkMon.getName() + " is weak to: Fire, Ice, Poison, Flying (4x), Psychic and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Fighting Electric") || (checkMon.getType().equals("Electric Fighting"))) {
			return checkMon.getName() + " is weak to: Ground, Psychic and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Fighting Psychic") || (checkMon.getType().equals("Psychic Fighting"))) {
			return checkMon.getName() + " is weak to: Flying, Ghost and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Fighting Ice") || (checkMon.getType().equals("Ice Fighting"))) {
			return checkMon.getName() + " is weak to: Fire, Fighting, Flying, Psychic, Steel and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Fighting Dragon") || (checkMon.getType().equals("Dragon Fighting"))) {
			return checkMon.getName() + " is weak to: Ice, Flying, Psychic, Dragon and Fairy (4x) type moves!";
		
		} else if (checkMon.getType().equals("Fighting Dark") || (checkMon.getType().equals("Dark Fighting"))) {
			return checkMon.getName() + " is weak to: Fighting, Flying and Fairy (4x) type moves!";
		
		} else if (checkMon.getType().equals("Fighting Fairy") || (checkMon.getType().equals("Fairy Fighting"))) {
			return checkMon.getName() + " is weak to: Poison, Flying, Psychic, Steel and Fairy type moves!";

			
		// Flying types
		} else if (checkMon.getType().equals("Flying")) {
			return checkMon.getName() + " is weak to: Electric, Ice and Rock type moves!";
		
		} else if (checkMon.getType().equals("Flying Poison") || (checkMon.getType().equals("Poison Flying"))) {
			return checkMon.getName() + " is weak to: Electric, Ice, Psychic and Rock type moves!";
		
		} else if (checkMon.getType().equals("Flying Ground") || (checkMon.getType().equals("Ground Flying"))) {
			return checkMon.getName() + " is weak to: Water and Ice (4x) type moves!";
		
		} else if (checkMon.getType().equals("Flying Rock") || (checkMon.getType().equals("Rock Flying"))) {
			return checkMon.getName() + " is weak to: Water, Electric, Ice, Rock and Steel type moves!";
		
		} else if (checkMon.getType().equals("Flying Bug") || (checkMon.getType().equals("Bug Flying"))) {
			return checkMon.getName() + " is weak to: Fire, Electric, Ice, Flying and Rock (4x) type moves!";
		
		} else if (checkMon.getType().equals("Flying Ghost") || (checkMon.getType().equals("Ghost Flying"))) {
			return checkMon.getName() + " is weak to: Electric, Ice, Rock, Ghost and Dark type moves!";
		
		} else if (checkMon.getType().equals("Flying Steel") || (checkMon.getType().equals("Steel Flying"))) {
			return checkMon.getName() + " is weak to: Fire and Electric type moves!";
		
		} else if (checkMon.getType().equals("Flying Fire") || (checkMon.getType().equals("Fire Flying"))) {
			return checkMon.getName() + " is weak to: Water, Electric and Rock (4x) type moves!";
		
		} else if (checkMon.getType().equals("Flying Water") || (checkMon.getType().equals("Water Flying"))) {
			return checkMon.getName() + " is weak to: Electric (4x) and Rock type moves!";
		
		} else if (checkMon.getType().equals("Flying Grass") || (checkMon.getType().equals("Grass Flying"))) {
			return checkMon.getName() + " is weak to: Fire, Ice (4x), Poison, Flying and Rock type moves!";
		
		} else if (checkMon.getType().equals("Flying Electric") || (checkMon.getType().equals("Electric Flying"))) {
			return checkMon.getName() + " is weak to: Ice and Rock type moves!";
		
		} else if (checkMon.getType().equals("Flying Ice") || (checkMon.getType().equals("Ice Flying"))) {
			return checkMon.getName() + " is weak to: Fire, Electric and Rock (4x) type moves!";
		
		} else if (checkMon.getType().equals("Flying Dragon") || (checkMon.getType().equals("Dragon Flying"))) {
			return checkMon.getName() + " is weak to: Ice (4x), Rock, Dragon and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Flying Dark") || (checkMon.getType().equals("Dark Flying"))) {
			return checkMon.getName() + " is weak to: Electric, Ice, Rock and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Flying Fairy") || (checkMon.getType().equals("Fairy Flying"))) {
			return checkMon.getName() + " is weak to: Electric, Ice, Poison, Rock and Steel type moves!";

			
		// Poison types
		} else if (checkMon.getType().equals("Poison")) {
			return checkMon.getName() + " is weak to: Ground and Psychic type moves!";
		
		} else if (checkMon.getType().equals("Poison Ground") || (checkMon.getType().equals("Ground Poison"))) {
			return checkMon.getName() + " is weak to: Water, Ice, Ground and Psychic type moves!";
		
		} else if (checkMon.getType().equals("Poison Rock") || (checkMon.getType().equals("Rock Poison"))) {
			return checkMon.getName() + " is weak to: Water, Ground (4x), Psychic and Steel type moves!";
		
		} else if (checkMon.getType().equals("Poison Bug") || (checkMon.getType().equals("Bug Poison"))) {
			return checkMon.getName() + " is weak to: Fire, Flying, Psychic and Rock type moves!";
		
		} else if (checkMon.getType().equals("Poison Ghost") || (checkMon.getType().equals("Ghost Poison"))) {
			return checkMon.getName() + " is weak to: Ground, Psychic, Ghost and Dark type moves!";
		
		} else if (checkMon.getType().equals("Poison Steel") || (checkMon.getType().equals("Steel Poison"))) {
			return checkMon.getName() + " is weak to: Fire and Ground (4x) type moves!";
		
		} else if (checkMon.getType().equals("Poison Fire") || (checkMon.getType().equals("Fire Poison"))) {
			return checkMon.getName() + " is weak to: Water, Ground (4x), Psychic and Rock type moves!";
		
		} else if (checkMon.getType().equals("Poison Water") || (checkMon.getType().equals("Water Poison"))) {
			return checkMon.getName() + " is weak to: Electric, Ground and Psychic type moves!";
		
		} else if (checkMon.getType().equals("Poison Grass") || (checkMon.getType().equals("Grass Poison"))) {
			return checkMon.getName() + " is weak to: Fire, Ice, Flying and Psychic type moves!";
		
		} else if (checkMon.getType().equals("Poison Electric") || (checkMon.getType().equals("Electric Poison"))) {
			return checkMon.getName() + " is weak to: Ground (4x) and Psychic type moves!";
		
		} else if (checkMon.getType().equals("Poison Psychic") || (checkMon.getType().equals("Psychic Poison"))) {
			return checkMon.getName() + " is weak to: Ground, Ghost and Dark type moves!";
		
		} else if (checkMon.getType().equals("Poison Ice") || (checkMon.getType().equals("Ice Poison"))) {
			return checkMon.getName() + " is weak to: Fire, Ground, Psychic, Rock and Steel type moves!";
		
		} else if (checkMon.getType().equals("Poison Dragon") || (checkMon.getType().equals("Dragon Poison"))) {
			return checkMon.getName() + " is weak to: Ice, Ground, Psychic and Dragon type moves!";
		
		} else if (checkMon.getType().equals("Poison Dark") || (checkMon.getType().equals("Dark Poison"))) {
			return checkMon.getName() + " is weak to: Ground type moves!";
		
		} else if (checkMon.getType().equals("Poison Fairy") || (checkMon.getType().equals("Fairy Poison"))) {
			return checkMon.getName() + " is weak to: Ground, Psychic and Steel type moves!";

			
		// Ground types
		} else if (checkMon.getType().equals("Ground")) {
			return checkMon.getName() + " is weak to: Water, Grass and Ice type moves!";
		
		} else if (checkMon.getType().equals("Ground Rock") || (checkMon.getType().equals("Rock Ground"))) {
			return checkMon.getName() + " is weak to: Water (4x), Grass (4x), Ice, Fighting, Ground and Steel type moves!";
		
		} else if (checkMon.getType().equals("Ground Bug") || (checkMon.getType().equals("Bug Ground"))) {
			return checkMon.getName() + " is weak to: Fire, Water, Ice and Flying type moves!";
		
		} else if (checkMon.getType().equals("Ground Ghost") || (checkMon.getType().equals("Ghost Ground"))) {
			return checkMon.getName() + " is weak to: Water, Grass, Ice, Ghost and Dark type moves!";
		
		} else if (checkMon.getType().equals("Ground Steel") || (checkMon.getType().equals("Steel Ground"))) {
			return checkMon.getName() + " is weak to: Fire, Water, Fighting and Ground type moves!";
		
		} else if (checkMon.getType().equals("Ground Fire") || (checkMon.getType().equals("Fire Ground"))) {
			return checkMon.getName() + " is weak to: Water (4x) and Ground type moves!";
		
		} else if (checkMon.getType().equals("Ground Water") || (checkMon.getType().equals("Water Ground"))) {
			return checkMon.getName() + " is weak to: Grass (4x) type moves!";
		
		} else if (checkMon.getType().equals("Ground Grass") || (checkMon.getType().equals("Grass Ground"))) {
			return checkMon.getName() + " is weak to: Fire, Ice (4x), Flying and Bug type moves!";
		
		} else if (checkMon.getType().equals("Ground Electric") || (checkMon.getType().equals("Electric Ground"))) {
			return checkMon.getName() + " is weak to: Water, Grass, Ice and Ground type moves!";
		
		} else if (checkMon.getType().equals("Ground Psychic") || (checkMon.getType().equals("Psychic Ground"))) {
			return checkMon.getName() + " is weak to: Water, Grass, Ice, Bug, Ghost and Dark type moves!";
		
		} else if (checkMon.getType().equals("Ground Ice") || (checkMon.getType().equals("Ice Ground"))) {
			return checkMon.getName() + " is weak to: Fire, Water, Grass and Fighting type moves!";
		
		} else if (checkMon.getType().equals("Ground Dragon") || (checkMon.getType().equals("Dragon Ground"))) {
			return checkMon.getName() + " is weak to: Ice (4x), Dragon and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Ground Dark") || (checkMon.getType().equals("Dark Ground"))) {
			return checkMon.getName() + " is weak to: Water, Grass, Ice, Fighting, Bug and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Ground Fairy") || (checkMon.getType().equals("Fairy Ground"))) {
			return checkMon.getName() + " is weak to: Water, Grass, Ice and Steel type moves!";


		// Rock types
		} else if (checkMon.getType().equals("Rock")) {
			return checkMon.getName() + " is weak to: Water, Grass, Fighting, Ground and Steel type moves!";
		
		} else if (checkMon.getType().equals("Rock Bug") || (checkMon.getType().equals("Bug Rock"))) {
			return checkMon.getName() + " is weak to: Water, Rock and Steel type moves!";
		
		} else if (checkMon.getType().equals("Rock Ghost") || (checkMon.getType().equals("Ghost Rock"))) {
			return checkMon.getName() + " is weak to: Water, Grass, Ground, Ghost, Dark and Steel type moves!";
		
		} else if (checkMon.getType().equals("Rock Steel") || (checkMon.getType().equals("Steel Rock"))) {
			return checkMon.getName() + " is weak to: Water, Fighting (4x) and Ground (4x) type moves!";
		
		} else if (checkMon.getType().equals("Rock Fire") || (checkMon.getType().equals("Fire Rock"))) {
			return checkMon.getName() + " is weak to: Water (4x), Fighting and Ground (4x) type moves!";
		
		} else if (checkMon.getType().equals("Rock Water") || (checkMon.getType().equals("Water Rock"))) {
			return checkMon.getName() + " is weak to: Electric, Grass (4x), Fighting and Ground type moves!";
		
		} else if (checkMon.getType().equals("Rock Grass") || (checkMon.getType().equals("Grass Rock"))) {
			return checkMon.getName() + " is weak to: Ice, Fighting, Bug and Steel type moves!";
		
		} else if (checkMon.getType().equals("Rock Electric") || (checkMon.getType().equals("Electric Rock"))) {
			return checkMon.getName() + " is weak to: Water, Grass, Fighting and Ground (4x) type moves!";
		
		} else if (checkMon.getType().equals("Rock Psychic") || (checkMon.getType().equals("Psychic Rock"))) {
			return checkMon.getName() + " is weak to: Water, Grass, Ground, Bug, Ghost, Dark and Steel type moves!";
		
		} else if (checkMon.getType().equals("Rock Ice") || (checkMon.getType().equals("Ice Rock"))) {
			return checkMon.getName() + " is weak to: Water, Grass, Fighting (4x), Ground, Rock and Steel (4x) type moves!";
		
		} else if (checkMon.getType().equals("Rock Dragon") || (checkMon.getType().equals("Dragon Rock"))) {
			return checkMon.getName() + " is weak to: Ice, Fighting, Ground, Dragon, Steel and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Rock Dark") || (checkMon.getType().equals("Dark Rock"))) {
			return checkMon.getName() + " is weak to: Water, Grass, Fighting (4x), Ground, Bug, Steel and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Rock Fairy") || (checkMon.getType().equals("Fairy Rock"))) {
			return checkMon.getName() + " is weak to: Water, Grass, Ground and Steel (4x) type moves!";
		
		
		// Bug types
		} else if (checkMon.getType().equals("Bug")) {
			return checkMon.getName() + " is weak to: Fire, Flying and Rock type moves!";
		
		} else if (checkMon.getType().equals("Bug Ghost") || (checkMon.getType().equals("Ghost Bug"))) {
			return checkMon.getName() + " is weak to: Fire, Flying, Rock, Ghost and Dark type moves!";
		
		} else if (checkMon.getType().equals("Bug Steel") || (checkMon.getType().equals("Steel Bug"))) {
			return checkMon.getName() + " is weak to: Fire (4x) type moves!";
		
		} else if (checkMon.getType().equals("Bug Fire") || (checkMon.getType().equals("Fire Bug"))) {
			return checkMon.getName() + " is weak to: Water, Flying and Rock (4x) type moves!";
		
		} else if (checkMon.getType().equals("Bug Water") || (checkMon.getType().equals("Water Bug"))) {
			return checkMon.getName() + " is weak to: Electric, Flying and Rock type moves!";
		
		} else if (checkMon.getType().equals("Bug Grass") || (checkMon.getType().equals("Grass Bug"))) {
			return checkMon.getName() + " is weak to: Fire (4x), Ice, Poison, Flying (4x) Bug and Rock type moves!";
		
		} else if (checkMon.getType().equals("Bug Electric") || (checkMon.getType().equals("Electric Bug"))) {
			return checkMon.getName() + " is weak to: Fire and Rock type moves!";
		
		} else if (checkMon.getType().equals("Bug Psychic") || (checkMon.getType().equals("Psychic Bug"))) {
			return checkMon.getName() + " is weak to: Fire, Flying, Bug, Rock, Ghost and Dark type moves!";
		
		} else if (checkMon.getType().equals("Bug Ice") || (checkMon.getType().equals("Ice Bug"))) {
			return checkMon.getName() + " is weak to: Fire (4x), Flying, Rock (4x) and Steel type moves!";
		
		} else if (checkMon.getType().equals("Bug Dragon") || (checkMon.getType().equals("Dragon Bug"))) {
			return checkMon.getName() + " is weak to: Ice, Flying, Rock, Dragon and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Bug Dark") || (checkMon.getType().equals("Dark Bug"))) {
			return checkMon.getName() + " is weak to: Fire, Flying, Bug, Rock and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Bug Fairy") || (checkMon.getType().equals("Fairy Bug"))) {
			return checkMon.getName() + " is weak to: Fire, Poison, Flying, Rock and Steel type moves!";
			
		
		// Ghost types
		} else if (checkMon.getType().equals("Ghost")) {
			return checkMon.getName() + " is weak to: Ghost and Dark type moves!";
		
		} else if (checkMon.getType().equals("Ghost Steel") || (checkMon.getType().equals("Steel Ghost"))) {
			return checkMon.getName() + " is weak to: Fire, Ground, Ghost and Dark type moves!";
		
		} else if (checkMon.getType().equals("Ghost Fire") || (checkMon.getType().equals("Fire Ghost"))) {
			return checkMon.getName() + " is weak to: Water, Ground, Rock, Ghost and Dark type moves!";
		
		} else if (checkMon.getType().equals("Ghost Water") || (checkMon.getType().equals("Water Ghost"))) {
			return checkMon.getName() + " is weak to: Electric, Grass, Ghost and Dark type moves!";
		
		} else if (checkMon.getType().equals("Ghost Grass") || (checkMon.getType().equals("Grass Ghost"))) {
			return checkMon.getName() + " is weak to: Fire, Ice, Flying, Ghost and Dark type moves!";
		
		} else if (checkMon.getType().equals("Ghost Electric") || (checkMon.getType().equals("Electric Ghost"))) {
			return checkMon.getName() + " is weak to: Ground, Ghost and Dark type moves!";
		
		} else if (checkMon.getType().equals("Ghost Psychic") || (checkMon.getType().equals("Psychic Ghost"))) {
			return checkMon.getName() + " is weak to: Ghost (4x) and Dark (4x) type moves!";
		
		} else if (checkMon.getType().equals("Ghost Ice") || (checkMon.getType().equals("Ice Ghost"))) {
			return checkMon.getName() + " is weak to: Fire, Rock, Ghost, Dark and Steel type moves!";
		
		} else if (checkMon.getType().equals("Ghost Dragon") || (checkMon.getType().equals("Dragon Ghost"))) {
			return checkMon.getName() + " is weak to: Ice, Ghost, Dragon, Dark and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Ghost Dark") || (checkMon.getType().equals("Dark Ghost"))) {
			return checkMon.getName() + " is weak to: Fairy type moves!";
		
		} else if (checkMon.getType().equals("Ghost Fairy") || (checkMon.getType().equals("Fairy Ghost"))) {
			return checkMon.getName() + " is weak to: Ghost and Steel type moves!";
			
			
		// Steel types
		} else if (checkMon.getType().equals("Steel")) {
			return checkMon.getName() + " is weak to: Fire, Fighting and Ground type moves!";
		
		} else if (checkMon.getType().equals("Steel Fire") || (checkMon.getType().equals("Fire Steel"))) {
			return checkMon.getName() + " is weak to: Water, Fighting and Ground (4x) type moves!";
		
		} else if (checkMon.getType().equals("Steel Water") || (checkMon.getType().equals("Water Steel"))) {
			return checkMon.getName() + " is weak to: Electric, Fighting and Ground type moves!";
		
		} else if (checkMon.getType().equals("Steel Grass") || (checkMon.getType().equals("Grass Steel"))) {
			return checkMon.getName() + " is weak to: Fire (4x) and Fighting type moves!";
		
		} else if (checkMon.getType().equals("Steel Electric") || (checkMon.getType().equals("Electric Steel"))) {
			return checkMon.getName() + " is weak to: Fire, Fighting and Ground (4x) type moves!";
		
		} else if (checkMon.getType().equals("Steel Psychic") || (checkMon.getType().equals("Psychic Steel"))) {
			return checkMon.getName() + " is weak to: Fire, Ground, Ghost and Dark type moves!";
		
		} else if (checkMon.getType().equals("Steel Ice") || (checkMon.getType().equals("Ice Steel"))) {
			return checkMon.getName() + " is weak to: Fire (4x), Fighting (4x) and Ground type moves!";
		
		} else if (checkMon.getType().equals("Steel Dragon") || (checkMon.getType().equals("Dragon Steel"))) {
			return checkMon.getName() + " is weak to: Fighting and Ground type moves!";
		
		} else if (checkMon.getType().equals("Steel Dark") || (checkMon.getType().equals("Dark Steel"))) {
			return checkMon.getName() + " is weak to: Fire, Fighting (4x) and Ground type moves!";
		
		} else if (checkMon.getType().equals("Steel Fairy") || (checkMon.getType().equals("Fairy Steel"))) {
			return checkMon.getName() + " is weak to: Fire and Ground type moves!";

			
		// Fire types
		} else if (checkMon.getType().equals("Fire")) {
			return checkMon.getName() + " is weak to: Water, Ground and Rock type moves!";
		
		} else if (checkMon.getType().equals("Fire Water") || (checkMon.getType().equals("Water Fire"))) {
			return checkMon.getName() + " is weak to: Electric, Ground and Rock type moves!";
		
		} else if (checkMon.getType().equals("Fire Grass") || (checkMon.getType().equals("Grass Fire"))) {
			return checkMon.getName() + " is weak to: Poison, Flying and Rock type moves!";
		
		} else if (checkMon.getType().equals("Fire Electric") || (checkMon.getType().equals("Electric Fire"))) {
			return checkMon.getName() + " is weak to: Water, Ground (4x) and Rock type moves!";
		
		} else if (checkMon.getType().equals("Fire Psychic") || (checkMon.getType().equals("Psychic Fire"))) {
			return checkMon.getName() + " is weak to: Water, Ground, Rock, Ghost and Dark type moves!";
		
		} else if (checkMon.getType().equals("Fire Ice") || (checkMon.getType().equals("Ice Fire"))) {
			return checkMon.getName() + " is weak to: Water, Fighting, Ground and Rock (4x) type moves!";
		
		} else if (checkMon.getType().equals("Fire Dragon") || (checkMon.getType().equals("Dragon Fire"))) {
			return checkMon.getName() + " is weak to: Ground, Rock and Dragon type moves!";
		
		} else if (checkMon.getType().equals("Fire Dark") || (checkMon.getType().equals("Dark Fire"))) {
			return checkMon.getName() + " is weak to: Water, Fighting, Ground and Rock type moves!";
		
		} else if (checkMon.getType().equals("Fire Fairy") || (checkMon.getType().equals("Fairy Fire"))) {
			return checkMon.getName() + " is weak to: Water, Poison, Ground and Rock type moves!";
			
			
		// Water types
		} else if (checkMon.getType().equals("Water")) {
			return checkMon.getName() + " is weak to: Electric and Grass type moves!";
		
		} else if (checkMon.getType().equals("Water Grass") || (checkMon.getType().equals("Grass Water"))) {
			return checkMon.getName() + " is weak to: Poison, Flying and Bug type moves!";
		
		} else if (checkMon.getType().equals("Water Electric") || (checkMon.getType().equals("Electric Water"))) {
			return checkMon.getName() + " is weak to: Grass and Ground type moves!";
		
		} else if (checkMon.getType().equals("Water Psychic") || (checkMon.getType().equals("Psychic Water"))) {
			return checkMon.getName() + " is weak to: Electric, Grass, Bug, Ghost and Dark type moves!";
		
		} else if (checkMon.getType().equals("Water Ice") || (checkMon.getType().equals("Ice Water"))) {
			return checkMon.getName() + " is weak to: Electric, Grass, Fighting and Rock type moves!";
		
		} else if (checkMon.getType().equals("Water Dragon") || (checkMon.getType().equals("Dragon Water"))) {
			return checkMon.getName() + " is weak to: Dragon and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Water Dark") || (checkMon.getType().equals("Dark Water"))) {
			return checkMon.getName() + " is weak to: Electric, Grass, Fighting, Bug and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Water Fairy") || (checkMon.getType().equals("Fairy Water"))) {
			return checkMon.getName() + " is weak to: Electric, Grass and Poison type moves!";
			
			
		// Grass types
		} else if (checkMon.getType().equals("Grass")) {
			return checkMon.getName() + " is weak to: Fire, Ice, Poison, Flying and Bug type moves!";
		
		} else if (checkMon.getType().equals("Grass Electric") || (checkMon.getType().equals("Electric Grass"))) {
			return checkMon.getName() + " is weak to: Fire, Ice, Poison and Bug type moves!";	
		
		} else if (checkMon.getType().equals("Grass Psychic") || (checkMon.getType().equals("Psychic Grass"))) {
			return checkMon.getName() + " is weak to: Fire, Ice, Poison, Flying, Bug (4x), Ghost and Dark type moves!";
		
		} else if (checkMon.getType().equals("Grass Ice") || (checkMon.getType().equals("Ice Grass"))) {
			return checkMon.getName() + " is weak to: Fire (4x), Fighting, Poison, Flying, Bug, Rock and steel type moves!";	
		
		} else if (checkMon.getType().equals("Grass Dragon") || (checkMon.getType().equals("Dragon Grass"))) {
			return checkMon.getName() + " is weak to: Ice (4x), Poison, Flying, Bug, Dragon and Fairy type moves!";	
		
		} else if (checkMon.getType().equals("Grass Dark") || (checkMon.getType().equals("Dark Grass"))) {
			return checkMon.getName() + " is weak to: Fire, Ice, Fighting, Poison, Flying, Bug (4x) and Fairy type moves!";	
		
		} else if (checkMon.getType().equals("Grass Fairy") || (checkMon.getType().equals("Fairy Grass"))) {
			return checkMon.getName() + " is weak to: Fire, Ice, Poison (4x), Flying and Steel type moves!";	
			

		// Electric types
		} else if (checkMon.getType().equals("Electric")) {
			return checkMon.getName() + " is weak to: Ground type moves!";
		
		} else if (checkMon.getType().equals("Electric Psychic") || (checkMon.getType().equals("Psychic Electric"))) {
			return checkMon.getName() + " is weak to: Ground, Bug, Ghost and Dark type moves!";	
		
		} else if (checkMon.getType().equals("Electric Ice") || (checkMon.getType().equals("Ice Electric"))) {
			return checkMon.getName() + " is weak to: Fire, Fighting, Ground and Rock type moves!";	
		
		} else if (checkMon.getType().equals("Electric Dragon") || (checkMon.getType().equals("Dragon Electric"))) {
			return checkMon.getName() + " is weak to: Ice, Ground, Dragon and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Electric Dark") || (checkMon.getType().equals("Dark Electric"))) {
			return checkMon.getName() + " is weak to: Fighting, Ground, Bug and Fairy type moves!";	
		
		} else if (checkMon.getType().equals("Electric Fairy") || (checkMon.getType().equals("Fairy Electric"))) {
			return checkMon.getName() + " is weak to: Poison and Ground type moves!";	
			
			
		// Psychic types
		} else if (checkMon.getType().equals("Psychic")) {
			return checkMon.getName() + " is weak to: Bug, Ghost and Dark type moves!";
		
		} else if (checkMon.getType().equals("Psychic Ice") || (checkMon.getType().equals("Ice Psychic"))) {
			return checkMon.getName() + " is weak to: Fire, Bug, Rock, Ghost, Dark and Steel type moves!";	
		
		} else if (checkMon.getType().equals("Psychic Dragon") || (checkMon.getType().equals("Dragon Psychic"))) {
			return checkMon.getName() + " is weak to: Ice, Bug, Ghost, Dragon, Dark and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Psychic Dark") || (checkMon.getType().equals("Dark Psychic"))) {
			return checkMon.getName() + " is weak to: Bug (4x) and Fairy type moves!";	
		
		} else if (checkMon.getType().equals("Psychic Fairy") || (checkMon.getType().equals("Fairy Psychic"))) {
			return checkMon.getName() + " is weak to: Poison, Ghost and Steel type moves!";

				
		// Ice types
		} else if (checkMon.getType().equals("Ice")) {
			return checkMon.getName() + " is weak to: Fire, Fighting, Rock and Steel type moves!";
		
		} else if (checkMon.getType().equals("Ice Dragon") || (checkMon.getType().equals("Dragon Ice"))) {
			return checkMon.getName() + " is weak to: Fighting, Rock, Dragon, Steel and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Ice Dark") || (checkMon.getType().equals("Dark Ice"))) {
			return checkMon.getName() + " is weak to: Fire, Fighting (4x), Bug, Rock, Steel and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Ice Fairy") || (checkMon.getType().equals("Fairy Ice"))) {
			return checkMon.getName() + " is weak to: Fire, Poison, Rock and Steel (4x) type moves!";	
			
			
		// Dragon types	
		} else if (checkMon.getType().equals("Dragon")) {
			return checkMon.getName() + " is weak to: Ice, Dragon and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Dragon Dark") || (checkMon.getType().equals("Dark Dragon"))) {
			return checkMon.getName() + " is weak to: Ice, Fighting, Bug, Dragon and Fairy (4x) type moves!";
		
		} else if (checkMon.getType().equals("Dragon Fairy") || (checkMon.getType().equals("Fairy Dragon"))) {
			return checkMon.getName() + " is weak to: Ice, Poison, Steel and Fairy type moves!";
			
			
		// Dark types	
		} else if (checkMon.getType().equals("Dark")) {
			return checkMon.getName() + " is weak to: Fighting, Bug and Fairy type moves!";
		
		} else if (checkMon.getType().equals("Dark Fairy") || (checkMon.getType().equals("Fairy Dark"))) {
			return checkMon.getName() + " is weak to: Poison, Steel and Fairy type moves!";	

			
    	// Fairy types	
		} else if (checkMon.getType().equals("Fairy")) {
			return checkMon.getName() + " is weak to: Poison and Steel type moves!";
		} else {
			return "A Pokemon of this type doesn't exist yet.";
		}

	}

}
