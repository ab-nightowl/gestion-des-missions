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
		missionRobert1.setStatut(statutValidee);
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
		missionRobert2.setStatut(statutValidee);
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
		missionRobert3.setStatut(statutValidee);
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
		
		Mission missionRobert4 = new Mission();
		missionRobert4.setDateDebut(LocalDate.of(2015, 6, 1));
		missionRobert4.setDateFin(LocalDate.of(2015, 6, 7));
		missionRobert4.setNatureMissionInit(natureExpertise);
		missionRobert4.setVilleDepart(villeParis);
		missionRobert4.setVilleArrivee(villeNantes);
		missionRobert4.setTransport(transportTrain);
		missionRobert4.setStatut(statutValidee);
		missionRobert4.setUtilisateurMatricule(robertMatricule);
		em.persist(missionRobert4);
		
		Frais fraisRobertM4F1 = new Frais(LocalDate.of(2015, 6, 4), natureFraisHotel, 20, missionRobert4);
		em.persist(fraisRobertM4F1);
		Frais fraisRobertM4F2 = new Frais(LocalDate.of(2015, 6, 5), natureFraisTaxi, 12, missionRobert4);
		em.persist(fraisRobertM4F2);
		Frais fraisRobertM4F3 = new Frais(LocalDate.of(2015, 6, 1), natureFraisRestaurant, 60, missionRobert4);
		em.persist(fraisRobertM4F3);
		Frais fraisRobertM4F4 = new Frais(LocalDate.of(2015, 6, 2), natureFraisHotel, 74, missionRobert4);
		em.persist(fraisRobertM4F4);
		Frais fraisRobertM4F5 = new Frais(LocalDate.of(2015, 6, 3), natureFraisRestaurant, 50, missionRobert4);
		em.persist(fraisRobertM4F5);
		Frais fraisRobertM4F6 = new Frais(LocalDate.of(2015, 6, 6), natureFraisRestaurant, 40, missionRobert4);
		em.persist(fraisRobertM4F6);
		
		Mission missionRobert5 = new Mission();
		missionRobert5.setDateDebut(LocalDate.of(2015, 8, 18));
		missionRobert5.setDateFin(LocalDate.of(2015, 8, 22));
		missionRobert5.setNatureMissionInit(natureExpertise);
		missionRobert5.setVilleDepart(villeParis);
		missionRobert5.setVilleArrivee(villeNantes);
		missionRobert5.setTransport(transportTrain);
		missionRobert5.setStatut(statutValidee);
		missionRobert5.setUtilisateurMatricule(robertMatricule);
		em.persist(missionRobert5);
		
		Frais fraisRobertM5F1 = new Frais(LocalDate.of(2015, 8, 18), natureFraisHotel, 20, missionRobert5);
		em.persist(fraisRobertM5F1);
		Frais fraisRobertM5F2 = new Frais(LocalDate.of(2015, 8, 20), natureFraisTaxi, 8, missionRobert5);
		em.persist(fraisRobertM5F2);
		Frais fraisRobertM5F3 = new Frais(LocalDate.of(2015, 8, 20), natureFraisRestaurant, 10, missionRobert5);
		em.persist(fraisRobertM5F3);
		
		Mission missionRobert6 = new Mission();
		missionRobert6.setDateDebut(LocalDate.of(2015, 7, 18));
		missionRobert6.setDateFin(LocalDate.of(2015, 7, 22));
		missionRobert6.setNatureMissionInit(natureExpertise);
		missionRobert6.setVilleDepart(villeParis);
		missionRobert6.setVilleArrivee(villeNantes);
		missionRobert6.setTransport(transportTrain);
		missionRobert6.setStatut(statutValidee);
		missionRobert6.setUtilisateurMatricule(robertMatricule);
		em.persist(missionRobert6);
		
		Frais fraisRobertM6F1 = new Frais(LocalDate.of(2015, 7, 18), natureFraisHotel, 200, missionRobert6);
		em.persist(fraisRobertM6F1);
		Frais fraisRobertM6F2 = new Frais(LocalDate.of(2015, 7, 20), natureFraisTaxi, 70, missionRobert6);
		em.persist(fraisRobertM6F2);
		Frais fraisRobertM6F3 = new Frais(LocalDate.of(2015, 7, 20), natureFraisRestaurant, 60, missionRobert6);
		em.persist(fraisRobertM6F3);
		
		Mission missionRobert7 = new Mission();
		missionRobert7.setDateDebut(LocalDate.of(2015, 2, 18));
		missionRobert7.setDateFin(LocalDate.of(2015, 2, 28));
		missionRobert7.setNatureMissionInit(natureExpertise);
		missionRobert7.setVilleDepart(villeParis);
		missionRobert7.setVilleArrivee(villeNantes);
		missionRobert7.setTransport(transportTrain);
		missionRobert7.setStatut(statutValidee);
		missionRobert7.setUtilisateurMatricule(robertMatricule);
		em.persist(missionRobert7);
		
		Frais fraisRobertM7F1 = new Frais(LocalDate.of(2015, 2, 28), natureFraisHotel, 200, missionRobert7);
		em.persist(fraisRobertM7F1);
		Frais fraisRobertM7F2 = new Frais(LocalDate.of(2015, 2, 20), natureFraisTaxi, 70, missionRobert7);
		em.persist(fraisRobertM7F2);
		Frais fraisRobertM7F3 = new Frais(LocalDate.of(2015, 2, 21), natureFraisRestaurant, 120, missionRobert7);
		em.persist(fraisRobertM7F3);
		Frais fraisRobertM7F4 = new Frais(LocalDate.of(2015, 2, 18), natureFraisHotel, 225, missionRobert7);
		em.persist(fraisRobertM7F4);
		Frais fraisRobertM7F5 = new Frais(LocalDate.of(2015, 2, 22), natureFraisTaxi, 70, missionRobert7);
		em.persist(fraisRobertM7F5);
		Frais fraisRobertM7F6 = new Frais(LocalDate.of(2015, 2, 25), natureFraisRestaurant, 250, missionRobert7);
		em.persist(fraisRobertM7F6);
		
		Mission missionRobert8 = new Mission();
		missionRobert8.setDateDebut(LocalDate.of(2015, 10, 18));
		missionRobert8.setDateFin(LocalDate.of(2015, 10, 28));
		missionRobert8.setNatureMissionInit(natureExpertise);
		missionRobert8.setVilleDepart(villeParis);
		missionRobert8.setVilleArrivee(villeNantes);
		missionRobert8.setTransport(transportTrain);
		missionRobert8.setStatut(statutValidee);
		missionRobert8.setUtilisateurMatricule(robertMatricule);
		em.persist(missionRobert8);
		
		Frais fraisRobertM8F1 = new Frais(LocalDate.of(2015, 10, 28), natureFraisHotel, 200, missionRobert8);
		em.persist(fraisRobertM8F1);
		Frais fraisRobertM8F2 = new Frais(LocalDate.of(2015, 10, 20), natureFraisTaxi, 70, missionRobert8);
		em.persist(fraisRobertM8F2);
		Frais fraisRobertM8F3 = new Frais(LocalDate.of(2015, 10, 21), natureFraisRestaurant, 120, missionRobert8);
		em.persist(fraisRobertM8F3);
		Frais fraisRobertM8F4 = new Frais(LocalDate.of(2015, 10, 18), natureFraisHotel, 225, missionRobert8);
		em.persist(fraisRobertM8F4);
		Frais fraisRobertM8F5 = new Frais(LocalDate.of(2015, 10, 22), natureFraisTaxi, 70, missionRobert8);
		em.persist(fraisRobertM8F5);
		Frais fraisRobertM8F6 = new Frais(LocalDate.of(2015, 10, 25), natureFraisRestaurant, 20, missionRobert8);
		em.persist(fraisRobertM8F6);
		
		Mission missionRobert9 = new Mission();
		missionRobert9.setDateDebut(LocalDate.of(2015, 8, 19));
		missionRobert9.setDateFin(LocalDate.of(2015, 8, 29));
		missionRobert9.setNatureMissionInit(natureExpertise);
		missionRobert9.setVilleDepart(villeParis);
		missionRobert9.setVilleArrivee(villeNantes);
		missionRobert9.setTransport(transportTrain);
		missionRobert9.setStatut(statutValidee);
		missionRobert9.setUtilisateurMatricule(robertMatricule);
		em.persist(missionRobert9);
		
		Frais fraisRobertM9F1 = new Frais(LocalDate.of(2015, 8, 29), natureFraisHotel, 100, missionRobert9);
		em.persist(fraisRobertM9F1);
		Frais fraisRobertM9F2 = new Frais(LocalDate.of(2015, 8, 20), natureFraisTaxi, 16, missionRobert9);
		em.persist(fraisRobertM9F2);
		Frais fraisRobertM9F3 = new Frais(LocalDate.of(2015, 8, 21), natureFraisRestaurant, 28, missionRobert9);
		em.persist(fraisRobertM9F3);
		
		Mission missionRobert10 = new Mission();
		missionRobert10.setDateDebut(LocalDate.of(2017, 9, 12));
		missionRobert10.setDateFin(LocalDate.of(2017, 10, 10));
		missionRobert10.setNatureMissionInit(natureExpertise);
		missionRobert10.setVilleDepart(villeParis);
		missionRobert10.setVilleArrivee(villeNantes);
		missionRobert10.setTransport(transportTrain);
		missionRobert10.setStatut(statutRejetee);
		missionRobert10.setUtilisateurMatricule(robertMatricule);
		em.persist(missionRobert10);
		
		Mission missionRobert11 = new Mission();
		missionRobert11.setDateDebut(LocalDate.of(2017, 6, 5));
		missionRobert11.setDateFin(LocalDate.of(2017, 6, 10));
		missionRobert11.setNatureMissionInit(natureFormation);
		missionRobert11.setVilleDepart(villeParis);
		missionRobert11.setVilleArrivee(villeNantes);
		missionRobert11.setTransport(transportTrain);
		missionRobert11.setStatut(statutValidee);
		missionRobert11.setUtilisateurMatricule(robertMatricule);
		em.persist(missionRobert11);
		
		Frais fraisRobertM11F1 = new Frais(LocalDate.of(2017, 6, 6), natureFraisHotel, 100, missionRobert11);
		em.persist(fraisRobertM11F1);
		Frais fraisRobertM11F2 = new Frais(LocalDate.of(2017, 6, 7), natureFraisTaxi, 16, missionRobert11);
		em.persist(fraisRobertM11F2);
		Frais fraisRobertM11F3 = new Frais(LocalDate.of(2017, 6, 6), natureFraisRestaurant, 28, missionRobert11);
		em.persist(fraisRobertM11F3);
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
