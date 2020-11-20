package com.adora.progettoFinale.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Profilo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	
	@OneToMany(mappedBy = "profile")
	private List<Utente> users;

	public Profilo() {
			users = new ArrayList<Utente>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Profilo(Integer id, String name) {
		
		users = new ArrayList<Utente>();
		this.id = id;
		this.name = name;
	}

}
