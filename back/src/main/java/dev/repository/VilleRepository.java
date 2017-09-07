package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entite.Ville;

public interface VilleRepository extends JpaRepository<Ville, Integer> {
	
}
