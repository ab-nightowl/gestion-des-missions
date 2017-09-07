package dev.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.entite.Ville;
import dev.repository.VilleRepository;

@RestController
@RequestMapping("/villes")
public class VilleController {
	
	@Autowired
	private VilleRepository villeRepo;
	
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	private List<Ville> listerVilles() {
		return villeRepo.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/lister")
	private void creerVille(@RequestBody Ville ville) {
		villeRepo.save(ville);
	}
	
}
