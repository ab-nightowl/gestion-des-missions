package dev.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.entite.Utilisateur;
import dev.entite.Utilisateur.ROLES;
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
	
	@RequestMapping(method = RequestMethod.GET, path = "role")
	public String getUserByMatricule(@RequestParam("userEmail") String email) {
		
		Optional<UtilisateurGitHub> userFound = getListUsers().stream()
				.filter(user -> {
					return user.getEmail()
							.equals(email);
				})
				.findAny();
		
		String role = userFound.get()
				.getSubalternes().length > 0 ? ROLES.ROLE_MANAGER.name() : ROLES.ROLE_EMPLOYE.name();
		
		if (userFound.isPresent()) {
			Utilisateur userRoleFound = repoUtilisateurs.findOneByMatricule(userFound.get()
					.getMatricule());
			
			if (userRoleFound != null) {
				role = repoUtilisateurs.findOneByMatricule(userFound.get()
						.getMatricule())
						.getRole()
						.name();
			}
		}
		
		return "{ \"role\": \"" + role + "\"}";
	}
}
