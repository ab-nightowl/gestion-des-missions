package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entite.Administrateur;

public interface AdministrateursRepository extends JpaRepository<Administrateur, Integer> {
	Administrateur findOneByMatricule(String matricule);
}