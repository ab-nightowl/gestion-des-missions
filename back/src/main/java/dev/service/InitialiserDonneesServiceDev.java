package dev.service;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import dev.entite.Mission;
import dev.entite.NatureMission;
import dev.entite.Statut;
import dev.entite.Statut.STATUTS;
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
		em.persist(mission2);
		
		Mission mission3 = new Mission();
		mission3.setDateDebut(LocalDate.now());
		mission3.setDateFin(LocalDate.of(2017, 10, 1));
		mission3.setNatureMissionInit(natureFormation);
		mission3.setVilleDepart(bordeaux);
		mission3.setVilleArrivee(nantes);
		mission3.setTransport(train);
		mission3.setPrime(200);
		mission3.setStatut(validee);
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
		em.persist(mission4);
		
	}
	
}
