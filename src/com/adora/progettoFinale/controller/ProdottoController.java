package com.adora.progettoFinale.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.adora.progettoFinale.model.Prodotto;
import com.adora.progettoFinale.model.Utente;
import com.adora.progettoFinale.service.ProdottoService;
import com.adora.progettoFinale.utils.ProdottoWeb;

@Controller
@RequestMapping("/prodotti")
@SessionAttributes("loginSessionAttribute")
public class ProdottoController {

	@Autowired
	private ProdottoService prodottoService;

	@ModelAttribute("emptyProdottoAttribute")
	public ProdottoWeb emptyProdotto() {
		return new ProdottoWeb("", "");
	}

	@ModelAttribute("loginSessionAttribute")
	public Utente loginAttribute() {
		return new Utente(null, "", "");
	}

	@GetMapping("/ricerca")
	public String searchGET(Model model, @ModelAttribute("loginSessionAttribute") Utente sessionUser,
			@ModelAttribute("emptyProdottoAttribute") ProdottoWeb emptyProduct) {
		if (sessionUser.isLoggedIn()) {
			System.out.println("l'utente e loggato ritorna ricerca prodotti");
			model.addAttribute("searchProdottoForm", emptyProduct);
			return "ricercaProdotti";
		} else {

			model.addAttribute("userLoginForm", sessionUser);
			model.addAttribute("errorOccured", true);
			model.addAttribute("message", "bisogna fare l'accesso per usare la ricerca prodotti");
			return "paginaLogin";
		}

	}

	@PostMapping("/ricerca")
	public String searchPOST(Model model, @ModelAttribute("searchProdottoForm") ProdottoWeb searchProdotto,
			@ModelAttribute("loginSessionAttribute") Utente sessionUser) {

		if (sessionUser.isLoggedIn()) {

			List<Prodotto> searchResult = new ArrayList<Prodotto>();

			if (searchProdotto != null) {
				Float prezzo = -1f;

				if (!searchProdotto.getPrice().equals("")) {

					try {
						String prezzoString = searchProdotto.getPrice().replaceAll(",", ".");
						prezzo = Float.parseFloat(prezzoString);
						if(prezzo <= 0) {
							throw new NumberFormatException();
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();

						model.addAttribute("errorOccured", true);
						model.addAttribute("message", "errore nei dati: prezzo");
						model.addAttribute("searchProdottoForm", searchProdotto);
						return "ricercaProdotti";
					}

				}

				String nome = searchProdotto.getName();

				if (searchProdotto.getName() != null && !searchProdotto.getName().equals("") && prezzo != null
						&& prezzo > 0.0f) {
					// ricerca per nome e per prezzo

					searchResult = prodottoService.cercaPerNomeECosto(nome, prezzo);

				} else if (searchProdotto.getName() != null && !searchProdotto.getName().equals("")) {
					// ricerca per nome
					searchResult = prodottoService.cercaPerNome(nome);
				} else if (prezzo != null && prezzo > 0.0f) {
					// ricerca per prezzo
					searchResult = prodottoService.findByPriceLessThanEqual(prezzo);

				} else {
					// prendi tutta la lista
					searchResult = prodottoService.findAll();
				}

			}
			// TODO aggiungi lista al model
			if (searchResult != null) {
				model.addAttribute("resultList", searchResult);
				return "risultatiRicerca";
			} else {
				model.addAttribute("errorOccured", true);
				model.addAttribute("message", "errore non specificato");
				model.addAttribute("searchProdottoForm", searchProdotto);
				return "ricercaProdotti";
			}

		} else {

			model.addAttribute("userLoginForm", sessionUser);
			model.addAttribute("errorOccured", true);
			model.addAttribute("message", "bisogna fare l'accesso per usare la ricerca prodotti");
			return "paginaLogin";
		}

	}

	@GetMapping("/crea")
	public String creaGET(Model model, @ModelAttribute("loginSessionAttribute") Utente sessionUser,
			@ModelAttribute("emptyProdottoAttribute") ProdottoWeb emptyProduct) {
		if (sessionUser.isLoggedIn() && sessionUser.getProfile().getName().equalsIgnoreCase("admin")) {

			model.addAttribute("creaProdottoForm", emptyProduct);
			return "creaProdotto";
		} else {
			
			if(!sessionUser.getProfile().getName().equalsIgnoreCase("user")) {
				model.addAttribute("userLoginForm", sessionUser);
				model.addAttribute("errorOccurred", true);
				model.addAttribute("message", "Non sei autorizzato ad utilizzare questa risorsa");
				return "paginaLogin";
			}
			
			else {
				model.addAttribute("errorOccurred", true);
				model.addAttribute("message", "Non sei autorizzato ad entrare qui");
				return "homeInterna";
			}
		}
	}

	@PostMapping("/crea")
	public String creaPOST(Model model, @ModelAttribute("loginSessionAttribute") Utente sessionUser,
			@ModelAttribute("creaProdottoForm") ProdottoWeb nuovoProdotto) {
		if (sessionUser.isLoggedIn() && sessionUser.getProfile().getName().equalsIgnoreCase("admin")) {
			String nome = nuovoProdotto.getName();
			Float prezzo;
			try {
				
				if(nome.equals("") && nuovoProdotto.getPrice().equals("")) {
					
					try {
						throw new Exception();
					} catch (Exception e) {
						model.addAttribute("errorOccurred", true);
						model.addAttribute("message", "Inserire dati");
						
						return "creaProdotto";
					}
				}
				
				String prezzoString = nuovoProdotto.getPrice().replaceAll(",", ".");

				prezzo = Float.parseFloat(prezzoString);
			} catch (NumberFormatException e) {
				// TODO: errore nel prezzo
				e.printStackTrace();
				// TODO dati errati
				model.addAttribute("errorOccured", true);
				model.addAttribute("message", "Errore dati inseriti: prezzo");

				return "esito";
			}
			if (nome != null && !nome.equals("") && prezzo != null && prezzo > 0) {

				if (prodottoService.creaProdotto(new Prodotto(null, nome, prezzo)) != null) {
					// TODO esito positivo
					model.addAttribute("success", true);
					model.addAttribute("message", "Prodotto creato");
					return "esito";
				} else {
					// TODO esito negativo (errore nella creazione)
					model.addAttribute("errorOccured", true);
					model.addAttribute("message", "Errore nella creazione del prodotto");
					return "esito";
				}

			} else {

				// TODO dati errati
				model.addAttribute("errorOccured", true);
				model.addAttribute("message", "Errore dati inseriti");
				return "esito";
			}

		} else {

			model.addAttribute("userLoginForm", sessionUser);
			model.addAttribute("errorOccured", true);
			model.addAttribute("message", "Non sei autorizzato ad utilizzare questa risorsa");
			return "paginaLogin";

		}
	}
}
