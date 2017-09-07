package dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.entite.Mission;
import dev.repository.MissionRepository;

@RestController
public class MissionController {

	@Autowired
	MissionRepository repoMission;

	@RequestMapping(method = RequestMethod.GET, path = "/listMission")
	// @Secured("ROLE_ADMINISTRATEUR")
	public List<Mission> listMission() {
		return repoMission.findAll();
	}

}
