package com.adora.progettoFinale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.adora.progettoFinale.model.Utente;
import com.adora.progettoFinale.service.UtenteService;
import com.adora.progettoFinale.utils.ResponseLogin;

@Controller
@SessionAttributes("loginSessionAttribute")
public class LoginController {

	@Autowired
	private UtenteService userService;

	@ModelAttribute("loginSessionAttribute")
	public Utente loginAttribute() {
		return new Utente(null, "", "");
	}

	@ModelAttribute("newUserAttribute")
	public Utente newUserAttribute() {
		return new Utente(null, "", "");
	}

	@GetMapping("/register")
	public String registerGET(Model model, @ModelAttribute("newUserAttribute") Utente newUser,
			@ModelAttribute("loginSessionAttribute") Utente userLoginSession) {
		// TODO eventuali attributi per la pagina
		if (userLoginSession.isLoggedIn()) {
			return "homeInterna";
		} else {
			model.addAttribute("newUserForm", newUser);
			return "paginaRegistrazione";
		}

	}

	@PostMapping("/register")
	public String registerPOST(Model model, @ModelAttribute("newUserForm") Utente newUser,
			@ModelAttribute("loginSessionAttribute") Utente userLoginSession) {
		if (userLoginSession.isLoggedIn()) {
			return "homeInterna";
		} else {

			if (newUser.getPassword() != null && newUser.getUserName() != null && !newUser.getUserName().equals("")
					&& !newUser.getPassword().equals("")) {

				if (userService.registra(newUser) != null) {
					model.addAttribute("newUserForm", newUserAttribute());
					model.addAttribute("registerSuccess", true);
					model.addAttribute("message", "registrazione completata");
					return "paginaRegistrazione";

				} else {
					model.addAttribute("newUserForm", newUser);
					model.addAttribute("error", true);
					model.addAttribute("message", "utente già esistente");
					return "paginaRegistrazione";
				}

			} else {
				model.addAttribute("newUserForm", newUser);
				model.addAttribute("error", true);
				model.addAttribute("message", "dati errati o mancanti, ricontrollare");
				return "paginaRegistrazione";
			}

		}

	}

	@GetMapping("/login")
	public String loginGET(Model model, @ModelAttribute("loginSessionAttribute") Utente userLogin) {

		if (userLogin.isLoggedIn()) {
			return "homeInterna";
		}

		model.addAttribute("userLoginForm", userLogin);
		return "paginaLogin";
	}

	@PostMapping("/login")
	public String loginPOST(Model model, @ModelAttribute("loginSessionAttribute") Utente userLogin,
			@ModelAttribute("userLoginForm") Utente userLoginForm) {

		if (userLogin.isLoggedIn()) {
			return "redirect:/homeInterna";
		}

		if (userLoginForm.getPassword() != null && userLoginForm.getUserName() != null
				&& !userLoginForm.getPassword().equals("") && !userLoginForm.getPassword().equals("")) {

			ResponseLogin response = userService.login(userLoginForm.getUserName(), userLoginForm.getPassword());
			if (response != null && response.getUser() != null) {

				response.getUser().setPassword("");
				model.addAttribute("loginSessionAttribute", response.getUser());

				return "homeInterna";
			} else if (response != null) {

				switch (response.getErrorCode()) {
				case -1:
					model.addAttribute("error_login", true);
					model.addAttribute("messaggio_login", "Username sbagliata");
					break;
				case -2:
					model.addAttribute("error_login", true);
					model.addAttribute("messaggio_login", "Username duplicata");
					break;
				case -3:
					model.addAttribute("error_login", true);
					model.addAttribute("messaggio_login", "Password sbagliata");
					break;
				case 1:
					model.addAttribute("error_login", true);
					model.addAttribute("messaggio_login", "Password errata");
					break;
				}

				return "paginaLogin";
			} else {

				return "paginaLogin";

			}

		} else {
			model.addAttribute("error_login", true);
			model.addAttribute("messaggio_login", "Inserire credenziali");
			return "paginaLogin";
		}
	}

	@GetMapping("/logout")
	public String logout(Model model, @ModelAttribute("loginSessionAttribute") Utente logoutUser) {
		if (logoutUser != null && logoutUser.isLoggedIn()) {
			userService.logout(logoutUser.getId());
			logoutUser.setId(null);
			logoutUser.setLoggedIn(false);
			logoutUser.setUserName("");
			logoutUser.setPassword("");
			logoutUser.setProfile(null);
			model.addAttribute("loginSessionAttribute", logoutUser);
			model.addAttribute("warning", true);
			model.addAttribute("message", "Sei uscito dal tuo account");
			return "index";

		} else {
			model.addAttribute("userLoginForm", logoutUser);
			return "paginaLogin";
		}
	}

}
