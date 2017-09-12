package dev.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.entite.Frais;
import dev.repository.FraisRepository;

@RestController
@RequestMapping("/frais")
public class FraisController {

	@Autowired
	FraisRepository repoFrais;

	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	public List<Map<String, Number>> listFrais() {
		return repoFrais.sumFrais();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/lister/{id}")
	public List<Frais> listFraisMission(@PathVariable("id") Integer id) {
		return repoFrais.findByMissionId(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/lister/{id}")
	public void supprFraisMission(@PathVariable("id") Integer id) {
		System.out.println(id);
		repoFrais.delete(id);
	}

}
