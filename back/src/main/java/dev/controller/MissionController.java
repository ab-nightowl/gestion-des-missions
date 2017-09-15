package dev.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.entite.Mission;
import dev.entite.Statut;
import dev.repository.MissionRepository;

@RestController
@RequestMapping("/missions")
public class MissionController {
	
	@Autowired
	private MissionRepository missionRepo;
	
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	private List<Mission> listerMissions() {
		return missionRepo.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/echues/lister")
	private List<Mission> listerMissionsEchues() {
		return listerMissions().stream()
				.filter(mission -> LocalDate.now()
						.isAfter(mission.getDateFin()))
				.collect(Collectors.toList());
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/lister/{id}")
	private List<Mission> listerMissionsByUtilisateurId(@PathVariable("id") String matricule) {
		return missionRepo.findByUtilisateurMatricule(matricule);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/lister/Detail/{id}")
	private Mission listerMissionsById(@PathVariable("id") Integer id) {
		return missionRepo.findById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/lister")
	private void creerMission(@RequestBody Mission mission) {
		mission.setStatut(Statut.INITIALE);
		
		missionRepo.save(mission);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/lister")
	private void validerNoteDeFraisMission(@RequestBody Mission mission) {
		mission.setNoteDeFraisValider(true);
		missionRepo.save(mission);
	}

}
