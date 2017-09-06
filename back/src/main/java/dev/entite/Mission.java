package dev.entite;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private Integer naturesMissionInitId;
	private Integer natureMissionId;
	private Integer naturesMissionInitId;
	private Integer villeDepartId;
	private Integer villeArriveeId;
	private Integer transportId;
	private double prime;
	private Integer utilisateurId;
	private Integer statutId;

	public Mission() {
		super();
	}

	public Mission(LocalDate dateDebut, LocalDate dateFin, Integer naturesMissionInitId, Integer villeDepartId,
			Integer villeArriveeId, Integer transportId, Integer utilisateurId) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.naturesMissionInitId = naturesMissionInitId;
		this.villeDepartId = villeDepartId;
		this.villeArriveeId = villeArriveeId;
		this.transportId = transportId;
		this.utilisateurId = utilisateurId;
		this.statutId = 1;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public Integer getNatureMissionId() {
		return natureMissionId;
	}

	public void setNatureMissionId(Integer natureMissionId) {
		this.natureMissionId = natureMissionId;
	}
	
	public Integer getVilleDepartId() {
		return villeDepartId;
	}

	public void setVilleDepartId(Integer villeDepartId) {
		this.villeDepartId = villeDepartId;
	}

	public Integer getVilleArriveeId() {
		return villeArriveeId;
	}

	public void setVilleArriveeId(Integer villeArriveeId) {
		this.villeArriveeId = villeArriveeId;
	}

	public Integer getTransportId() {
		return transportId;
	}

	public void setTransportId(Integer transportId) {
		this.transportId = transportId;
	}

	public double getPrime() {
		return prime;
	}

	public void setPrime(double prime) {
		this.prime = prime;
	}

	public Integer getUtilisateurId() {
		return utilisateurId;
	}

	public void setUtilisateurId(Integer utilisateurId) {
		this.utilisateurId = utilisateurId;
	}

	public Integer getStatutId() {
		return statutId;
	}

	public void setStatutId(Integer statutId) {
		this.statutId = statutId;
	}

	public Integer getNaturesMissionInitId() {
		return naturesMissionInitId;
	}

	public void setNaturesMissionInitId(Integer naturesMissionInitId) {
		this.naturesMissionInitId = naturesMissionInitId;
	}
}
