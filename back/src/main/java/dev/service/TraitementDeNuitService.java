package dev.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import dev.entite.Mission;
import dev.entite.NatureMission;
import dev.entite.Statut;
import dev.repository.FraisRepository;
import dev.repository.MissionRepository;

@Component
public class TraitementDeNuitService {
	private static final Logger log = LoggerFactory.getLogger(TraitementDeNuitService.class);
	
	@Autowired
	MissionRepository repoMission;
	@Autowired
	FraisRepository repoFrais;
	
	@Scheduled(cron = "0 0 0 * * *")
	// @Scheduled(cron = "*/5 * * * * *")
	public void traitement() {
		log.info("Traitement de nuit: début");
		
		List<Mission> missions = repoMission.findAll();
		
		// Set status to DEMANDE_EN_ATTENTE_VALIDATION for missions with status
		// DEMANDE_INITIALE
		missions.stream()
				.filter(mission -> mission.getStatut()
						.name()
						.equals(Statut.DEMANDE_INITIALE.name()))
				.forEach(mission -> {
					mission.setStatut(Statut.DEMANDE_EN_ATTENTE_VALIDATION);
					repoMission.save(mission);
				});
		
		missions.stream()
				.filter(mission -> {
					return LocalDate.now()
							.isAfter(mission.getDateFin())
							&& !Optional.ofNullable(mission.getPrime())
									.isPresent();
				})
				.forEach(mission -> {
					// Computing Primes - START
					// Nature mission init
					NatureMission natureMission = mission.getNatureMissionInit();
					// Nb jours mission
					long nbJoursMission = ChronoUnit.DAYS.between(mission.getDateDebut(), mission.getDateFin()) + 1;
					// Somme frais
					Optional<Double> sommeFrais = Optional.ofNullable(repoFrais.sumFraisByMissionId(mission.getId()));
					
					if (sommeFrais.isPresent()) {
						// déduction=sommeDesFrais-(plafondDeFrais)*(nbJoursMission)
						double deduction = sommeFrais.get() - natureMission.getPlafondFrais() * nbJoursMission;
						deduction = Math.max(0, deduction);
						// Prime = (nbJoursMission)*TJM*%Prime/100-déduction
						double prime = nbJoursMission * natureMission.getTjm() * natureMission.getTauxPrime() / 100
								- deduction;
						prime = Math.max(0, prime);
						// Computing Primes - END
						mission.setPrime(prime);
					}
					repoMission.save(mission);
				});
		
		log.info("Traitement de nuit: fin");
	}
}
