package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entite.NatureFrais;

public interface NaturesFraisRepository extends JpaRepository<NatureFrais, Integer> {
	NatureFrais findById(Integer id);
}
