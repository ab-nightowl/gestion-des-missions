package dev.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import dev.service.InitialiserDonneesService;

@Controller
public class StartupController {
	private static final Logger LOGGER = LoggerFactory.getLogger(StartupController.class);
	@Autowired
	InitialiserDonneesService initDonnees;
	
	@EventListener(ContextRefreshedEvent.class)
	public void contextRefreshedEvent() {
		LOGGER.info("Initialisation des données");
		initDonnees.initialiser();
	}
}