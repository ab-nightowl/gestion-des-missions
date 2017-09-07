package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entite.Mission;

public interface MissionRepository extends JpaRepository<Mission, Integer> {
	Mission findById(Integer id);
}
