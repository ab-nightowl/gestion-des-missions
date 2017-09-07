package dev.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
