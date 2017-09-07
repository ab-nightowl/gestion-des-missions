package dev.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import dev.entite.Administrateur;
import dev.entite.Mission;
import dev.entite.NatureFrais;

@Service
@EnableTransactionManagement
@Transactional
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {
	
	@Autowired
	private EntityManager em;
	
	@Override
	public void initialiser() {
		
		Stream.of("bd540e65", "75e8048c")
				.forEach(matricule -> em.persist(new Administrateur(matricule)));
		
		List<String> natureFrais = new ArrayList<>();
		natureFrais.add("Hôtel");
		natureFrais.add("Taxi");
		for (String nf : natureFrais) {
			em.persist(new NatureFrais(nf));
		}
		
		Mission mission1 = new Mission(LocalDate.now(), LocalDate.now(), 1, 1, 2, 3, 2);
		em.persist(mission1);
	}
	
}
