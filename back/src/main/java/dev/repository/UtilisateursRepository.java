package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entite.Utilisateur;

public interface UtilisateursRepository extends JpaRepository<Utilisateur, Integer> {
	Utilisateur findOneByMatricule(String matricule);
}