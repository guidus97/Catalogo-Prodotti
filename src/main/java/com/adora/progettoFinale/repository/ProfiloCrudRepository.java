package com.adora.progettoFinale.repository;

import org.springframework.data.repository.CrudRepository;

import com.adora.progettoFinale.model.Profilo;

public interface ProfiloCrudRepository extends CrudRepository<Profilo, Integer>{

	Profilo findByName(String name);
}
