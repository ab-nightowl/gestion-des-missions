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

import dev.entite.Frais;
import dev.entite.Mission;
import dev.entite.NatureMission;
import dev.entite.Statut;
import dev.entite.Statut.STATUTS;
import dev.entite.Transport;
import dev.entite.Ville;
import dev.entite.Administrateur;
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

		NatureMission natureConseil = new NatureMission("Conseil", true, true, 3.5, 654, 132, true, false);
		NatureMission natureExpertise = new NatureMission("Expertise technique", true, true, 4.5, 750, 150, true, true);
		NatureMission natureFormation = new NatureMission("Formation", true, true, 4, 700, 100, false, true);
		em.persist(natureConseil);
		em.persist(natureExpertise);
		em.persist(natureFormation);

		Ville nantes = new Ville("Nantes");
		Ville bordeaux = new Ville("Bordeaux");
		Ville angers = new Ville("Angers");
		Ville marseille = new Ville("Marseille");
		Ville paris = new Ville("Paris");
		em.persist(nantes);
		em.persist(bordeaux);
		em.persist(angers);
		em.persist(marseille);
		em.persist(paris);

		Transport avion = new Transport("Avion");
		Transport voiture = new Transport("Voiture de service");
		Transport train = new Transport("Train");
		Transport covoit = new Transport("Covoiturage");
		em.persist(avion);
		em.persist(voiture);
		em.persist(train);
		em.persist(covoit);

		Statut initiale = new Statut(STATUTS.DEMANDE_INITIALE);
		Statut enAttente = new Statut(STATUTS.DEMANDE_EN_ATTENTE_VALIDATION);
		Statut validee = new Statut(STATUTS.DEMANDE_VALIDEE);
		Statut rejetee = new Statut(STATUTS.DEMANDE_REJETEE);
		em.persist(initiale);
		em.persist(enAttente);
		em.persist(validee);
		em.persist(rejetee);

		Mission mission1 = new Mission();
		mission1.setDateDebut(LocalDate.of(2017, 10, 1));
		mission1.setDateFin(LocalDate.of(2017, 10, 6));
		mission1.setNatureMissionInit(natureConseil);
		mission1.setVilleDepart(nantes);
		mission1.setVilleArrivee(marseille);
		mission1.setTransport(avion);
		mission1.setPrime(100);
		mission1.setStatut(initiale);
		mission1.setUtilisateurMatricule("bd540e65");
		em.persist(mission1);

		Mission mission2 = new Mission();
		mission2.setDateDebut(LocalDate.of(2017, 10, 10));
		mission2.setDateFin(LocalDate.of(2017, 10, 22));
		mission2.setNatureMissionInit(natureExpertise);
		mission2.setVilleDepart(nantes);
		mission2.setVilleArrivee(paris);
		mission2.setTransport(voiture);
		mission2.setPrime(300);
		mission2.setStatut(enAttente);
		mission2.setUtilisateurMatricule("56eb7d01");
		em.persist(mission2);

		Mission mission3 = new Mission();
		mission3.setDateDebut(LocalDate.of(2017, 10, 1));
		mission3.setDateFin(LocalDate.of(2017, 10, 31));
		mission3.setNatureMissionInit(natureFormation);
		mission3.setVilleDepart(bordeaux);
		mission3.setVilleArrivee(nantes);
		mission3.setTransport(train);
		mission3.setPrime(200);
		mission3.setStatut(validee);
		mission3.setUtilisateurMatricule("0d36bbdd");
		em.persist(mission3);

		Mission mission4 = new Mission();
		mission4.setDateDebut(LocalDate.of(2017, 9, 15));
		mission4.setDateFin(LocalDate.of(2017, 12, 15));
		mission4.setNatureMissionInit(natureFormation);
		mission4.setVilleDepart(angers);
		mission4.setVilleArrivee(nantes);
		mission4.setTransport(covoit);
		mission4.setPrime(1000);
		mission4.setStatut(rejetee);
		mission4.setUtilisateurMatricule("8dd0b708");
		em.persist(mission4);

		Stream.of("bd540e65", "75e8048c")
				.forEach(matricule -> em.persist(new Administrateur(matricule)));

		List<String> natureFrais = new ArrayList<>();
		natureFrais.add("Hôtel");
		natureFrais.add("Taxi");
		for (String nf : natureFrais) {
			em.persist(new NatureFrais(nf));
		}

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
