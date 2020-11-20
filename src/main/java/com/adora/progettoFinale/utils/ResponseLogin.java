package com.adora.progettoFinale.utils;

import com.adora.progettoFinale.model.Utente;

public class ResponseLogin {
	
	private Utente user;
	private Integer errorCode;
	public ResponseLogin(Utente user, Integer errorCode) {
		
		this.user = user;
		this.errorCode = errorCode;
	}
	public Utente getUser() {
		return user;
	}
	public void setUser(Utente user) {
		this.user = user;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	
	
	

}
