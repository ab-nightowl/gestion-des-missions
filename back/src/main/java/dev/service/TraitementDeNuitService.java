package dev.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import dev.repository.MissionRepository;

@Component
public class TraitementDeNuitService {
	private static final Logger log = LoggerFactory.getLogger(TraitementDeNuitService.class);
	
	@Autowired
	MissionRepository repoMission;
	
	// @Scheduled(cron = "0 0 0 * * *")
	@Scheduled(cron = "*/5 * * * * *")
	public void traitement() {
		log.info("Traitement de nuit: début");
		// repoMission.findAll()
		// .stream()
		// .filter(mission -> {
		// return true;
		// });
		log.info("Traitement de nuit: fin");
	}
}
