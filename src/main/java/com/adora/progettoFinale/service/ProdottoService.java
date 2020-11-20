package com.adora.progettoFinale.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adora.progettoFinale.model.Prodotto;
import com.adora.progettoFinale.repository.ProdottoCrudRepository;

@Service
public class ProdottoService {

	@Autowired
	private ProdottoCrudRepository p;
	
	public Prodotto creaProdotto(Prodotto p0) {
		if (p0 != null) {
			p.save(p0);
			return p0;
		}
		
		else {
			return null;
		}
	}
	
	public List<Prodotto> cercaPerNome(String nome){
		List<Prodotto> prodotti = p.findByName(nome);
		return prodotti;
	}
	
	public List<Prodotto> cercaPerCosto(Float costo){
		List<Prodotto> prodotti = p.findByPrice(costo);
		return prodotti;
	}
	
	public List<Prodotto> cercaPerNomeECosto(String nome, Float costo){
		List<Prodotto> prodotti = p.findByNameAndPrice(nome, costo);
		return prodotti;
	}
	
	public List<Prodotto> findAll(){
		Iterable<Prodotto> iterable = p.findAll();
		List<Prodotto> prodotti = new ArrayList<Prodotto>();
		iterable.forEach(prodotti::add);
		return prodotti;
	}
	
	public List<Prodotto> findByPriceLessThanEqual(Float price){
		return p.findByPriceLessThanEqual(price);
	}
}
