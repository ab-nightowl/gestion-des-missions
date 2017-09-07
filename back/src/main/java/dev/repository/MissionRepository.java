package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entite.Mission;

public interface MissionRepository extends JpaRepository<Mission, Integer> {
	Mission findById(Integer id);

	List<Mission> findByUtilisateurMatricule(String matricule);
}
