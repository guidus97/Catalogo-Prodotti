package com.adora.progettoFinale.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = {"com.adora.progettoFinale.repository", "com.adora.progettoFinale.service"})
@EnableTransactionManagement
public class JpaConfig
{

	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactory()
	{
		LocalEntityManagerFactoryBean localEntityManagerFactoryBean = new LocalEntityManagerFactoryBean();
		localEntityManagerFactoryBean.setPersistenceUnitName("CatalogoProdotti");
		return localEntityManagerFactoryBean;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf)
	{
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(emf);
		return jpaTransactionManager; 
	}
	
}