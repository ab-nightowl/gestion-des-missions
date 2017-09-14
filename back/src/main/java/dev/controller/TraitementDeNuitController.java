package dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.service.TraitementDeNuitService;

@RestController
@RequestMapping("/tdn")
public class TraitementDeNuitController {
	
	@Autowired
	TraitementDeNuitService serviceTdn;
	
	@RequestMapping(method = RequestMethod.GET, path = "/execute")
	public String executeTraitementDeNuit() {
		serviceTdn.traitement();
		return "{ \"msg\": \"Traitement de nuit exécuté avec succès.\" }";
	}
	
}
