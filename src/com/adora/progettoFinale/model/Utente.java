package com.adora.progettoFinale.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	private String userName;
	private String password;
	
	private boolean isLoggedIn;
	
	@ManyToOne
	private Profilo profile;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public Profilo getProfile() {
		return profile;
	}

	public void setProfile(Profilo profile) {
		this.profile = profile;
	}

	public Utente(Integer id, String username, String password) {
		
		this.id = id;
		this.userName = username;
		this.password = password;
		this.isLoggedIn = false;
	}

	public Utente() {
	
	}
	
	
}
