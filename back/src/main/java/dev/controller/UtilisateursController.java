package dev.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.entite.Administrateur;
import dev.entite.Utilisateur;
import dev.entite.UtilisateurRole;
import dev.repository.AdministrateursRepository;
import dev.service.UtilisateurService;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateursController {
	
	@Autowired
	AdministrateursRepository repoUtilisateurs;
	
	@Autowired
	UtilisateurService serviceUtilisateur;
	
	@RequestMapping(method = RequestMethod.GET, path = "")
	public List<Utilisateur> getListUsers() {
		return serviceUtilisateur.getListeUtilisateurs();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public Administrateur getUserById(@PathVariable(value = "id") Integer id) {
		return repoUtilisateurs.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "role")
	public String getUserByMatricule(@RequestParam("userEmail") String email) {
		
		Optional<Utilisateur> userFound = getListUsers().stream()
				.filter(user -> {
					return user.getEmail()
							.equals(email);
				})
				.findAny();
		
		String role = userFound.get()
				.getSubalternes().length > 0 ? UtilisateurRole.ROLE_MANAGER.toString()
						: UtilisateurRole.ROLE_EMPLOYE.toString();
		
		if (userFound.isPresent()) {
			Administrateur userRoleFound = repoUtilisateurs.findOneByMatricule(userFound.get()
					.getMatricule());
			
			if (userRoleFound != null && repoUtilisateurs.findOneByMatricule(userFound.get()
					.getMatricule()) != null) {
				role = UtilisateurRole.ROLE_ADMINISTRATEUR.toString();
			}
		}
		
		return "{ \"role\": \"" + role + "\"}";
	}
}
