package com.adora.progettoFinale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adora.progettoFinale.model.Profilo;
import com.adora.progettoFinale.model.Utente;
import com.adora.progettoFinale.repository.ProfiloCrudRepository;
import com.adora.progettoFinale.repository.UtenteCrudRepository;
import com.adora.progettoFinale.utils.ResponseLogin;

@Service
public class UtenteService {

	@Autowired
	private UtenteCrudRepository u;
	
	@Autowired
	private ProfiloCrudRepository p;

	public Utente registra(Utente u0) {
		if (!userAlreadyRegistered(u0.getUserName())) {
			Profilo profiloUtente = p.findByName("user");
			u0.setProfile(profiloUtente);
			u.save(u0);
			return u0;
		}

		else {
			return null;
		}
	}

	public ResponseLogin login(String username, String password) {

		List<Utente> u0 = u.findByUserName(username);
		ResponseLogin response;

		if (u0 != null) {

			if (u0.size() == 0) {
				response = new ResponseLogin(null, -1);
				return response;
			}

			else if (u0.size() > 1) {
				response = new ResponseLogin(null, -2);
				return response;
			}

			else if (u0.size() == 1) {

				if (u0.get(0).getPassword().equals(password)) {
					u.updateLogin(true, u0.get(0).getId());
					response = new ResponseLogin(u0.get(0), 0);
					return response;

				}

				else {
					response = new ResponseLogin(null, -3);
					return response;
				}
			}
			return null;

		} else {

			return null;
		}
	}

	private boolean userAlreadyRegistered(String username) {

		List<Utente> utenti = u.findByUserName(username);

		if (utenti.size() == 1 || utenti.size() > 1) {
			return true;
		}

		else {
			return false;
		}
	}

	
	public void logout(Integer id) {
		if(id != null) {
			u.updateLogin(false, id);
		}
		
	}
}
