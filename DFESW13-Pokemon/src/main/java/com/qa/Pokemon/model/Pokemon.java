package com.qa.Pokemon.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pokemon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private int ndex;
	
	@Column(nullable = false, length = 100)
	private String name;
	
	@Column(nullable = false, length = 100)
	private String type;
	
	@Column(nullable = false, length = 100)
	private String gender;
	
	@Column(nullable = false)
	private float height;
	
	@Column(nullable = false)
	private float weight;
	
	@Column(nullable = false)
	private int bst;

	public Pokemon() {
		super();
		
	}

	// With ID - for retrieving data from DB
	public Pokemon(long id, int ndex, String name, String type, String gender, float height, float weight, int bst) {
		super();
		this.id = id;
		this.ndex = ndex;
		this.name = name;
		this.type = type;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.bst = bst;
	}

	// Without ID - for posting data into DB
	public Pokemon(int ndex, String name, String type, String gender, float height, float weight, int bst) {
		super();
		this.ndex = ndex;
		this.name = name;
		this.type = type;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.bst = bst;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNdex() {
		return ndex;
	}

	public void setNdex(int ndex) {
		this.ndex = ndex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public int getBst() {
		return bst;
	}

	public void setBst(int bst) {
		this.bst = bst;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bst, gender, height, id, name, ndex, type, weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pokemon other = (Pokemon) obj;
		return bst == other.bst && Objects.equals(gender, other.gender)
				&& Float.floatToIntBits(height) == Float.floatToIntBits(other.height) && id == other.id
				&& Objects.equals(name, other.name) && ndex == other.ndex && Objects.equals(type, other.type)
				&& Float.floatToIntBits(weight) == Float.floatToIntBits(other.weight);
	}

	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", ndex=" + ndex + ", name=" + name + ", type=" + type + ", gender=" + gender
				+ ", height=" + height + ", weight=" + weight + ", bst=" + bst + "]";
	}
	
	
}
