package dev.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.entite.Frais;

public interface FraisRepository extends JpaRepository<Frais, Integer> {

	@Query("SELECT mission.id as missionId, SUM(montant) as sumMontant FROM Frais GROUP BY mission.id")
	List<Map<String, Number>> sumFrais();

	List<Frais> findByMissionId(Integer idMission);

}
