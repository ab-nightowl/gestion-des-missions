package dev.entite;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Frais {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate dateCreation;
	@ManyToOne
	private NatureFrais nature;
	private Integer montant;
	@ManyToOne
	private Mission mission;

	public Frais() {
		super();
	}

	public Frais(LocalDate dateCreation, NatureFrais nature, Integer montant, Mission mission) {
		super();
		this.dateCreation = dateCreation;
		this.nature = nature;
		this.montant = montant;
		this.mission = mission;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}

	public NatureFrais getNature() {
		return nature;
	}

	public void setNature(NatureFrais nature) {
		this.nature = nature;
	}

	public Integer getMontant() {
		return montant;
	}

	public void setMontant(Integer montant) {
		this.montant = montant;
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

}
