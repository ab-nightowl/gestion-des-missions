package dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.entite.Mission;
import dev.repository.MissionRepository;
import dev.repository.StatutRepository;

@RestController
@RequestMapping("/missions")
public class MissionController {
	
	@Autowired
	private MissionRepository missionRepo;
	@Autowired
	private StatutRepository statutRepo;
	
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	private List<Mission> listerMissions() {
		return missionRepo.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/lister")
	private void creerMission(@RequestBody Mission mission) {
		mission.setStatut(statutRepo.findOne(1));
		
		missionRepo.save(mission);
	}
	
}
