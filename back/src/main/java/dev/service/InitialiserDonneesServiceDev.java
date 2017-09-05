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
		utilisateurs.put("8b2d3ac7", ROLES.ROLE_MANAGER);
		utilisateurs.put("a8fc21fc", ROLES.ROLE_MANAGER);
		utilisateurs.put("e300fb12", ROLES.ROLE_MANAGER);
		utilisateurs.put("7befca85", ROLES.ROLE_MANAGER);
		utilisateurs.put("6c8be60e", ROLES.ROLE_MANAGER);
		utilisateurs.put("56eb7d01", ROLES.ROLE_EMPLOYE);
		utilisateurs.put("0d36bbdd", ROLES.ROLE_EMPLOYE);
		utilisateurs.put("f26eac86", ROLES.ROLE_MANAGER);
		utilisateurs.put("89dde79f", ROLES.ROLE_MANAGER);
		utilisateurs.put("8dd0b708", ROLES.ROLE_EMPLOYE);
		utilisateurs.put("e353c695", ROLES.ROLE_EMPLOYE);
		utilisateurs.put("478389f2", ROLES.ROLE_EMPLOYE);
		utilisateurs.put("740ef3fd", ROLES.ROLE_EMPLOYE);
		utilisateurs.put("26a79080", ROLES.ROLE_EMPLOYE);
		utilisateurs.put("099124f1", ROLES.ROLE_EMPLOYE);
		utilisateurs.put("6b7325b5", ROLES.ROLE_EMPLOYE);
		utilisateurs.put("ede47266", ROLES.ROLE_EMPLOYE);
		utilisateurs.put("3dfba579", ROLES.ROLE_EMPLOYE);
		utilisateurs.put("749cb358", ROLES.ROLE_EMPLOYE);
		utilisateurs.put("29bed9a2", ROLES.ROLE_EMPLOYE);
		utilisateurs.put("37db541d", ROLES.ROLE_EMPLOYE);
		utilisateurs.put("184c03f5", ROLES.ROLE_EMPLOYE);
		utilisateurs.put("4aef1639", ROLES.ROLE_EMPLOYE);
		utilisateurs.put("ed44692f", ROLES.ROLE_EMPLOYE);
		utilisateurs.put("a35a5a9f", ROLES.ROLE_EMPLOYE);
		utilisateurs.put("ba9ac63f", ROLES.ROLE_EMPLOYE);
		utilisateurs.put("a658c524", ROLES.ROLE_EMPLOYE);
		utilisateurs.put("7f157294", ROLES.ROLE_EMPLOYE);
		
		utilisateurs.entrySet()
				.stream()
				.forEach(e -> em.persist(new Utilisateur(e.getKey(), e.getValue())));
	}
	
}
