package dev.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.entite.Frais;
import dev.entite.NatureFrais;
import dev.repository.FraisRepository;
import dev.repository.MissionRepository;
import dev.repository.NaturesFraisRepository;

@RestController
public class NaturesFraisController {
	@Autowired
	NaturesFraisRepository repoNaturesFrais;
	@Autowired
	MissionRepository repoMission;
	@Autowired
	FraisRepository repoFrais;

	@RequestMapping(method = RequestMethod.GET, path = "/listNaturesFrais")
	public List<NatureFrais> listNaturesFrais() {
		return repoNaturesFrais.findAll();
	}

	@RequestMapping(method = RequestMethod.POST, path = "/listNaturesFrais")
	protected void doPostNatureFrais(HttpServletRequest req, @RequestBody Frais frais) {
		frais.setMission(repoMission.findById(1));
		repoFrais.save(frais);

	}
}
