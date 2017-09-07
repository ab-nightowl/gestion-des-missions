package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entite.NatureMission;

public interface NatureMissionRepository extends JpaRepository<NatureMission, Integer> {

}
