package dev.service;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import dev.entite.Utilisateur;
import dev.entite.Utilisateur.ROLES;

@Service
@EnableTransactionManagement
@Transactional
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {
	
	@Autowired
	private EntityManager em;
	
	@Override
	public void initialiser() {
		
		Map<String, ROLES> utilisateurs = new HashMap<>();
		
		utilisateurs.put("bd540e65", ROLES.ROLE_ADMINISTRATEUR);
		utilisateurs.put("75e8048c", ROLES.ROLE_ADMINISTRATEUR);
		
		utilisateurs.entrySet()
				.stream()
				.forEach(e -> em.persist(new Utilisateur(e.getKey(), e.getValue())));
	}
	
}
