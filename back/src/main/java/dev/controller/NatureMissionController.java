package dev.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.entite.NatureMission;
import dev.repository.MissionRepository;
import dev.repository.NatureMissionRepository;

@RestController
@RequestMapping("/naturesMissions")
public class NatureMissionController {
	
	@Autowired
	private NatureMissionRepository natureMissionRepo;
	
	@Autowired
	private MissionRepository missionRepo;
	
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	private List<NatureMission> listerNaturesMissions() {
		return natureMissionRepo.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	private String creerNatureMission(@RequestBody NatureMission natureMission) {
		List<NatureMission> natures = natureMissionRepo.findAll();
		
		// Tous les attributs sont obligatoires
		if (!natureMission.hasAllAttributes()) {
			return "{ \"msg\": \"Tous les champs sont obligatoires.\" }";
		}
		
		// Jamais deux natures avec tous les memes attributs
		if (natures.stream()
				.anyMatch(nature -> {
					System.out.println(nature.hasSameAttributesValues(natureMission));
					return nature.hasSameAttributesValues(natureMission);
				})) {
			return "{ \"msg\": \"Une nature avec les mêmes attributs existe déjà\" }";
		}
		
		// Un code nature est unique
		if (natures.stream()
				.anyMatch(nature -> nature.getLibelle()
						.equals(natureMission.getLibelle()))) {
			return "{ \"msg\": \"Une nature avec le même libelle existe déjà\" }";
		}
		
		// Le plafond de frais quotidien doit être strictement positif
		if (!(natureMission.getPlafondFrais() > 0)) {
			return "{ \"msg\": \"Le plafond de frais doit être strictement positif. La valeur entrée était: "
					+ natureMission.getPlafondFrais() + "\" }";
		}
		
		// La valeur du % doit être inférieure à 10
		if (!(natureMission.getTauxPrime() >= 0 && natureMission.getTauxPrime() <= 9.99)) {
			return "{ \"msg\": \"Le taux de prime doit être compris entre 0 et 9.99. La valeur entrée était: "
					+ natureMission.getTauxPrime() + "\" }";
		}
		
		natureMission.setDateDebutValidite(LocalDate.now());
		natureMission.setActif(true);
		natureMissionRepo.save(natureMission);
		return "{ \"msg\": \"success\" }";
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/supprimer/{natureId}")
	private String supprimerNatureMission(@PathVariable("natureId") Integer natureId) {
		NatureMission nature = natureMissionRepo.findOne(natureId);
		if (nature == null) {
			return "{ \"msg\": \"Nature mission avec id [" + natureId + "] n'existe pas\" }";
		}
		
		boolean natureIsUsed = missionRepo.findAll()
				.stream()
				.anyMatch(mission -> mission.getNatureMissionInit()
						.getId() == natureId);
		
		if (natureIsUsed) {
			nature.setActif(false);
			natureMissionRepo.save(nature);
		} else {
			natureMissionRepo.delete(natureId);
		}
		
		return "{ \"msg\": \"success\" }";
	}
}
