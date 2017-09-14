package dev.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.entite.Frais;
import dev.repository.FraisRepository;
import dev.repository.MissionRepository;

@RestController
@RequestMapping("/frais")
public class FraisController {

	@Autowired
	FraisRepository repoFrais;
	@Autowired
	MissionRepository repoMission;

	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	public List<Map<String, Number>> listFrais() {
		return repoFrais.sumFrais();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/listerParM/{id}")
	public List<Frais> listFraisMission(@PathVariable("id") Integer id) {
		return repoFrais.findByMissionId(id);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/listerParI/{id}")
	public Frais listFrais(@PathVariable("id") Integer id) {
		return repoFrais.findOne(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/lister/{id}")
	public void supprFraisMission(@PathVariable("id") Integer id) {
		repoFrais.delete(id);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/lister/{idMission}")
	protected void doPutFrais(HttpServletRequest req, @RequestBody Frais frais,
			@PathVariable("idMission") Integer idMission) {
		frais.setMission(repoMission.findById(idMission));
		repoFrais.save(frais);

	}

}
