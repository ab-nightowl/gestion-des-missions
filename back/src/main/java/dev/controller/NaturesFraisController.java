package dev.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/listNatures")
public class NaturesFraisController {
	@Autowired
	NaturesFraisRepository repoNaturesFrais;
	@Autowired
	MissionRepository repoMission;
	@Autowired
	FraisRepository repoFrais;

	@RequestMapping(method = RequestMethod.GET, path = "/frais")
	public List<NatureFrais> listNaturesFrais() {
		return repoNaturesFrais.findAll();
	}

	@RequestMapping(method = RequestMethod.POST, path = "/frais/{idMission}/{idNature}")
	protected void doPostNatureFrais(HttpServletRequest req, @RequestBody Frais frais,
			@PathVariable("idMission") Integer idMission, @PathVariable("idNature") Integer idNature) {
		frais.setMission(repoMission.findById(idMission));
		frais.setNature(repoNaturesFrais.findById(idNature));
		repoFrais.save(frais);

	}
}
