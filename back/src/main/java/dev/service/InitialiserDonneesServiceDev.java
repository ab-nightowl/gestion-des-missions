package dev.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import dev.entite.Frais;
import dev.entite.Mission;
import dev.entite.NatureFrais;
import dev.entite.NatureMission;
import dev.entite.Statut;
import dev.entite.Transport;
import dev.entite.Ville;

@Service
@EnableTransactionManagement
@Transactional
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@Autowired
	private EntityManager em;

	@Override
	public void initialiser() {

		List<String> natureFrais = new ArrayList<>();
		natureFrais.add("Hôtel");
		natureFrais.add("Taxi");
		for (String nf : natureFrais) {
			em.persist(new NatureFrais(nf));
		}

		Mission mission1 = new Mission(LocalDate.now(), LocalDate.now(), 1, 1, 2, 3, 2);
		Mission mission2 = new Mission(LocalDate.now(), LocalDate.now(), 2, 3, 1, 2, 2);
		em.persist(mission1);
		em.persist(mission2);

		NatureMission natureMission1 = new NatureMission("Conseil", true, true, 3.5, 654, 132, true, false);
		NatureMission natureMission2 = new NatureMission("Formation", true, true, 3.5, 798, 100, false, true);
		em.persist(natureMission1);
		em.persist(natureMission2);

		Ville ville1 = new Ville("Marseille");
		Ville ville2 = new Ville("Nantes");
		Ville ville3 = new Ville("Angers");
		Ville ville4 = new Ville("Bordeaux");
		Ville ville5 = new Ville("Bourg-en-Bresse");
		em.persist(ville1);
		em.persist(ville2);
		em.persist(ville3);
		em.persist(ville4);
		em.persist(ville5);

		Transport transport1 = new Transport("Vélo");
		Transport transport2 = new Transport("Voiture");
		Transport transport3 = new Transport("Train");
		Transport transport4 = new Transport("Avion");
		Transport transport5 = new Transport("Bâteau");
		Transport transport6 = new Transport("Taxi");
		em.persist(transport1);
		em.persist(transport2);
		em.persist(transport3);
		em.persist(transport4);
		em.persist(transport5);
		em.persist(transport6);

		Statut statut1 = new Statut("Initial");
		Statut statut2 = new Statut("En attente de validation");
		Statut statut3 = new Statut("Validé");
		Statut statut4 = new Statut("En cours");
		Statut statut5 = new Statut("Terminé");
		em.persist(statut1);
		em.persist(statut2);
		em.persist(statut3);
		em.persist(statut4);
		em.persist(statut5);

		Frais frais1 = new Frais(LocalDate.now(), 1, 255, mission1);
		Frais frais2 = new Frais(LocalDate.now(), 2, 745, mission1);
		Frais frais3 = new Frais(LocalDate.now(), 2, 100, mission2);
		Frais frais4 = new Frais(LocalDate.now(), 1, 900, mission2);
		em.persist(frais1);
		em.persist(frais2);
		em.persist(frais3);
		em.persist(frais4);
	}

}