package dev.service;

import java.time.LocalDate;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import dev.entite.Administrateur;
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
		
		String robertMatricule = "bd540e65"; // admin
		String jenniferMatricule = "e300fb12"; // manager
		String bernardMatricule = "478389f2"; // employe
		
		Stream.of(robertMatricule, "75e8048c")
				.forEach(matricule -> em.persist(new Administrateur(matricule)));
		
		NatureMission natureConseil = new NatureMission("Conseil", true, true, 3.5, 654, 132, true, false,
				LocalDate.now());
		NatureMission natureExpertise = new NatureMission("Expertise technique", true, true, 4.5, 750, 150, true, true,
				LocalDate.of(2016, 6, 20));
		NatureMission natureFormation = new NatureMission("Formation", true, true, 4, 700, 100, false, true,
				LocalDate.of(2017, 8, 15));
		em.persist(natureConseil);
		em.persist(natureExpertise);
		em.persist(natureFormation);
		
		Ville villeNantes = new Ville("Nantes");
		Ville villeBordeaux = new Ville("Bordeaux");
		Ville villeAngers = new Ville("Angers");
		Ville villeMarseille = new Ville("Marseille");
		Ville villeParis = new Ville("Paris");
		em.persist(villeNantes);
		em.persist(villeBordeaux);
		em.persist(villeAngers);
		em.persist(villeMarseille);
		em.persist(villeParis);
		
		Transport transportAvion = new Transport("Avion");
		Transport transportVoiture = new Transport("Voiture de service");
		Transport transportTrain = new Transport("Train");
		Transport transportCovoit = new Transport("Covoiturage");
		em.persist(transportAvion);
		em.persist(transportVoiture);
		em.persist(transportTrain);
		em.persist(transportCovoit);
		
		NatureFrais natureFraisHotel = new NatureFrais("Hôtel");
		NatureFrais natureFraisTaxi = new NatureFrais("Taxi");
		NatureFrais natureFraisRestaurant = new NatureFrais("Restaurant");
		NatureFrais natureFraisTrain = new NatureFrais("Train");
		em.persist(natureFraisHotel);
		em.persist(natureFraisTaxi);
		em.persist(natureFraisRestaurant);
		em.persist(natureFraisTrain);
		
		Statut statutInitiale = Statut.INITIALE;
		Statut statutEnAttente = Statut.EN_ATTENTE_VALIDATION;
		Statut statutValidee = Statut.VALIDEE;
		Statut statutRejetee = Statut.REJETEE;
		
		// Robert - START
		// ***********************************************************************************
		Mission missionRobert1 = new Mission();
		missionRobert1.setDateDebut(LocalDate.of(2017, 9, 1));
		missionRobert1.setDateFin(LocalDate.of(2017, 9, 6));
		missionRobert1.setNatureMissionInit(natureConseil);
		missionRobert1.setVilleDepart(villeNantes);
		missionRobert1.setVilleArrivee(villeMarseille);
		missionRobert1.setTransport(transportAvion);
		missionRobert1.setStatut(statutInitiale);
		missionRobert1.setUtilisateurMatricule(robertMatricule);
		em.persist(missionRobert1);
		
		Frais fraisRobertM1F1 = new Frais(LocalDate.of(2016, 9, 2), natureFraisTaxi, 12, missionRobert1);
		em.persist(fraisRobertM1F1);
		Frais fraisRobertM1F2 = new Frais(LocalDate.of(2016, 9, 4), natureFraisRestaurant, 25, missionRobert1);
		em.persist(fraisRobertM1F2);
		Frais fraisRobertM1F3 = new Frais(LocalDate.of(2016, 9, 5), natureFraisTrain, 32, missionRobert1);
		em.persist(fraisRobertM1F3);
		
		Mission missionRobert2 = new Mission();
		missionRobert2.setDateDebut(LocalDate.of(2017, 3, 1));
		missionRobert2.setDateFin(LocalDate.of(2017, 3, 6));
		missionRobert2.setNatureMissionInit(natureExpertise);
		missionRobert2.setVilleDepart(villeAngers);
		missionRobert2.setVilleArrivee(villeParis);
		missionRobert2.setTransport(transportTrain);
		missionRobert2.setStatut(statutInitiale);
		missionRobert2.setUtilisateurMatricule(robertMatricule);
		em.persist(missionRobert2);
		
		Frais fraisRobertM2F1 = new Frais(LocalDate.of(2016, 3, 3), natureFraisTaxi, 12, missionRobert2);
		em.persist(fraisRobertM2F1);
		Frais fraisRobertM2F2 = new Frais(LocalDate.of(2016, 3, 4), natureFraisTaxi, 28, missionRobert2);
		em.persist(fraisRobertM2F2);
		Frais fraisRobertM2F3 = new Frais(LocalDate.of(2016, 3, 5), natureFraisTaxi, 12, missionRobert2);
		em.persist(fraisRobertM2F3);
		Frais fraisRobertM2F4 = new Frais(LocalDate.of(2016, 3, 6), natureFraisRestaurant, 40, missionRobert2);
		em.persist(fraisRobertM2F4);
		
		Mission missionRobert3 = new Mission();
		missionRobert3.setDateDebut(LocalDate.of(2016, 10, 1));
		missionRobert3.setDateFin(LocalDate.of(2016, 11, 6));
		missionRobert3.setNatureMissionInit(natureFormation);
		missionRobert3.setVilleDepart(villeBordeaux);
		missionRobert3.setVilleArrivee(villeNantes);
		missionRobert3.setTransport(transportCovoit);
		missionRobert3.setStatut(statutInitiale);
		missionRobert3.setUtilisateurMatricule(robertMatricule);
		em.persist(missionRobert3);
		
		Frais fraisRobertM3F1 = new Frais(LocalDate.of(2016, 10, 3), natureFraisHotel, 58, missionRobert3);
		em.persist(fraisRobertM3F1);
		Frais fraisRobertM3F2 = new Frais(LocalDate.of(2016, 10, 20), natureFraisTaxi, 20, missionRobert3);
		em.persist(fraisRobertM3F2);
		Frais fraisRobertM3F3 = new Frais(LocalDate.of(2016, 11, 1), natureFraisRestaurant, 80, missionRobert3);
		em.persist(fraisRobertM3F3);
		Frais fraisRobertM3F4 = new Frais(LocalDate.of(2016, 11, 2), natureFraisHotel, 74, missionRobert3);
		em.persist(fraisRobertM3F4);
		// Robert - END
		// ***********************************************************************************
		
		Mission mission2 = new Mission();
		mission2.setDateDebut(LocalDate.of(2017, 9, 1));
		mission2.setDateFin(LocalDate.of(2017, 9, 5));
		mission2.setNatureMissionInit(natureExpertise);
		mission2.setVilleDepart(villeNantes);
		mission2.setVilleArrivee(villeParis);
		mission2.setTransport(transportVoiture);
		mission2.setStatut(statutEnAttente);
		mission2.setUtilisateurMatricule("56eb7d01");
		em.persist(mission2);
		
		Mission mission3 = new Mission();
		mission3.setDateDebut(LocalDate.of(2017, 10, 1));
		mission3.setDateFin(LocalDate.of(2017, 10, 31));
		mission3.setNatureMissionInit(natureFormation);
		mission3.setVilleDepart(villeBordeaux);
		mission3.setVilleArrivee(villeNantes);
		mission3.setTransport(transportTrain);
		mission3.setPrime(200.0);
		mission3.setStatut(statutValidee);
		mission3.setUtilisateurMatricule("0d36bbdd");
		em.persist(mission3);
		
		Mission mission4 = new Mission();
		mission4.setDateDebut(LocalDate.of(2017, 9, 1));
		mission4.setDateFin(LocalDate.of(2017, 9, 6));
		mission4.setNatureMissionInit(natureFormation);
		mission4.setVilleDepart(villeAngers);
		mission4.setVilleArrivee(villeNantes);
		mission4.setTransport(transportCovoit);
		mission4.setPrime(1000.0);
		mission4.setStatut(statutRejetee);
		mission4.setUtilisateurMatricule("8dd0b708");
		em.persist(mission4);
		
		Mission mission5 = new Mission();
		mission5.setDateDebut(LocalDate.of(2017, 9, 1));
		mission5.setDateFin(LocalDate.of(2017, 9, 8));
		mission5.setNatureMissionInit(natureExpertise);
		mission5.setVilleDepart(villeNantes);
		mission5.setVilleArrivee(villeAngers);
		mission5.setTransport(transportVoiture);
		mission5.setStatut(statutEnAttente);
		mission5.setUtilisateurMatricule("56eb7d01");
		em.persist(mission5);
		
		// Jennifer - START
		// ***********************************************************************************
		Mission missionJennifer1 = new Mission();
		missionJennifer1.setDateDebut(LocalDate.of(2017, 9, 1));
		missionJennifer1.setDateFin(LocalDate.of(2017, 9, 8));
		missionJennifer1.setNatureMissionInit(natureConseil);
		missionJennifer1.setVilleDepart(villeMarseille);
		missionJennifer1.setVilleArrivee(villeAngers);
		missionJennifer1.setTransport(transportVoiture);
		missionJennifer1.setStatut(statutEnAttente);
		missionJennifer1.setUtilisateurMatricule(jenniferMatricule);
		em.persist(missionJennifer1);
		
		Frais fraisJenniferM1F1 = new Frais(LocalDate.of(2016, 9, 5), natureFraisHotel, 20, missionJennifer1);
		em.persist(fraisJenniferM1F1);
		Frais fraisJenniferM1F2 = new Frais(LocalDate.of(2016, 9, 7), natureFraisTaxi, 10, missionJennifer1);
		em.persist(fraisJenniferM1F2);
		Frais fraisJenniferM1F3 = new Frais(LocalDate.of(2016, 9, 1), natureFraisRestaurant, 30, missionJennifer1);
		em.persist(fraisJenniferM1F3);
		Frais fraisJenniferM1F4 = new Frais(LocalDate.of(2016, 9, 2), natureFraisHotel, 120, missionJennifer1);
		em.persist(fraisJenniferM1F4);
		// Jennifer - END
		// ***********************************************************************************
		
		Frais frais2 = new Frais(LocalDate.now(), natureFraisTaxi, 745, missionRobert1);
		Frais frais3 = new Frais(LocalDate.now(), natureFraisTaxi, 100, mission2);
		Frais frais4 = new Frais(LocalDate.now(), natureFraisHotel, 900, mission2);
		em.persist(frais2);
		em.persist(frais3);
		em.persist(frais4);
		
	}
	
}
