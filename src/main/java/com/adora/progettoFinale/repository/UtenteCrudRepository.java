package com.adora.progettoFinale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.adora.progettoFinale.model.Utente;

public interface UtenteCrudRepository extends CrudRepository<Utente, Integer>{

	List<Utente> findByUserName(String username);
	
	//@Query("select u from Utente u where u.username=:us and u.password=:pw")
	List<Utente> findByUserNameAndPassword(String username,String password);
	
	@Modifying
	@Transactional
	@Query("update Utente u set u.isLoggedIn = :value where u.id = :id")
	void updateLogin(@Param("value") boolean value, @Param("id") Integer id);
}
