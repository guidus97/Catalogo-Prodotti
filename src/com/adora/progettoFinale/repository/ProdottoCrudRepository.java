package com.adora.progettoFinale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adora.progettoFinale.model.Prodotto;

public interface ProdottoCrudRepository extends CrudRepository<Prodotto, Integer>{

	List<Prodotto> findByName(String name);
	List<Prodotto> findByPrice(Float price);
	List<Prodotto> findByNameAndPrice(String name, Float price);
	
	@Query("select prod from Prodotto prod where prod.price = :price or prod.price < :price")
	public List<Prodotto> findByPriceLessThanEqual(@Param("price")Float price);

}
