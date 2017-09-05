package dev.entite;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private ZonedDateTime dateDebut;
	private ZonedDateTime dateFin;
	private Integer natureMissionId;
	private Integer naturesMissionInitId;
	private Integer villeDepartId;
	private Integer villeArriveeId;
	private Integer transportId;
	private double prime;
	private Integer utilisateurId;
	private Integer statutId;
	
	public Mission() {
		
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public ZonedDateTime getDateDebut() {
		return dateDebut;
	}
	
	public void setDateDebut(ZonedDateTime dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	public ZonedDateTime getDateFin() {
		return dateFin;
	}
	
	public void setDateFin(ZonedDateTime dateFin) {
		this.dateFin = dateFin;
	}
	
	public Integer getNatureMissionId() {
		return natureMissionId;
	}
	
	public void setNatureMissionId(Integer natureMissionId) {
		this.natureMissionId = natureMissionId;
	}
	
	public Integer getNaturesMissionInitId() {
		return naturesMissionInitId;
	}
	
	public void setNaturesMissionInitId(Integer naturesMissionInitId) {
		this.naturesMissionInitId = naturesMissionInitId;
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
}
