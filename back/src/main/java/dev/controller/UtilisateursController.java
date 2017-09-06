package dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.entite.Utilisateur;
import dev.entite.UtilisateurGitHub;
import dev.repository.UtilisateursRepository;
import dev.service.UtilisateurService;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateursController {
	
	@Autowired
	UtilisateursRepository repoUtilisateurs;
	
	@Autowired
	UtilisateurService serviceUtilisateur;
	
	@RequestMapping(method = RequestMethod.GET, path = "")
	public List<UtilisateurGitHub> getListUsers() {
		return serviceUtilisateur.getListeUtilisateurs();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public Utilisateur getUserById(@PathVariable(value = "id") Integer id) {
		return repoUtilisateurs.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "matricule/{matricule}")
	public Utilisateur getUserByMatricule(@PathVariable(value = "matricule") String matricule) {
		return repoUtilisateurs.findOneByMatricule(matricule);
	}
}
