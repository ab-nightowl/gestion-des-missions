package dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.entite.Statut;
import dev.repository.StatutRepository;

@RestController
@RequestMapping("/statuts")
public class StatutController {
	
	@Autowired
	private StatutRepository statutRepo;
	
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	private List<Statut> listerStatuts() {
		return statutRepo.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/lister")
	private void creerStatut(@RequestBody Statut statut) {
		statutRepo.save(statut);
	}
	
}
