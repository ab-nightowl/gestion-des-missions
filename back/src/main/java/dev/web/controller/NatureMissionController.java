package dev.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.entite.NatureMission;
import dev.repository.NatureMissionRepository;

@RestController
@RequestMapping("/naturesMissions")
public class NatureMissionController {
	
	@Autowired
	private NatureMissionRepository natureMissionRepo;
	
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	private List<NatureMission> listerNaturesMissions() {
		return natureMissionRepo.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/lister")
	private void creerNatureMission(@RequestBody NatureMission natureMission) {
		natureMissionRepo.save(natureMission);
	}
	
}
