package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entite.Statut;

public interface StatutRepository extends JpaRepository<Statut, Integer> {

}
