package dev.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import dev.entite.Utilisateur;

@Service
public class UtilisateurService {
	
	private List<Utilisateur> utilisateurs;
	private ObjectMapper objectMapper;
	
	public UtilisateurService() {
		this.objectMapper = new ObjectMapper();
		// Set pretty printing of json
		this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		this.utilisateurs = getListUtilisateursFromGithub();
	}
	
	public List<Utilisateur> getListUtilisateursFromGithub() {
		String server = "https://raw.github.com/DiginamicFormation/ressources-atelier/master/users.json";
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		headers.add("Accept", "*/*");
		
		HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
		ResponseEntity<String> responseEntity = rest.exchange(server, HttpMethod.GET, requestEntity, String.class);
		
		String utilisateursRetrieved = responseEntity.getBody();
		
		TypeReference<List<Utilisateur>> mapTypeUser = new TypeReference<List<Utilisateur>>() {
		};
		List<Utilisateur> utilisateurs = null;
		try {
			utilisateurs = objectMapper.readValue(utilisateursRetrieved, mapTypeUser);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return utilisateurs;
	}
	
	public List<Utilisateur> getListeUtilisateurs() {
		return this.utilisateurs;
	}
	
	public String getJsonUtilisateurs() {
		try {
			return this.objectMapper.writeValueAsString(getListeUtilisateurs());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Optional<Utilisateur> getManagerByUtilisateurMatricule(String matricule) {
		return this.getListeUtilisateurs()
				.stream()
				.filter(utilisateur -> utilisateur.getSubalternes()
						.stream()
						.anyMatch(subMatricule -> subMatricule.equals(matricule)))
				.findAny();
	}
}
