package dev.entite;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NatureMission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String libelle;
	private boolean facture;
	private boolean versementPrime;
	private double tauxPrime;
	private double tjm;
	private double plafondFrais;
	private boolean depassementFrais;
	private boolean actif;
	private LocalDate dateDebutValidite;
	
	public NatureMission() {
		super();
	}
	
	public NatureMission(String libelle, boolean facture, boolean versementPrime, double tauxPrime, double tjm,
			double plafondFrais, boolean depassementFrais, boolean actif, LocalDate dateDebutValidite) {
		super();
		this.libelle = libelle;
		this.facture = facture;
		this.versementPrime = versementPrime;
		this.tauxPrime = tauxPrime;
		this.tjm = tjm;
		this.plafondFrais = plafondFrais;
		this.depassementFrais = depassementFrais;
		this.actif = actif;
		this.dateDebutValidite = dateDebutValidite;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getLibelle() {
		return libelle;
	}
	
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public boolean isFacture() {
		return facture;
	}
	
	public void setFacture(boolean facture) {
		this.facture = facture;
	}
	
	public boolean isVersementPrime() {
		return versementPrime;
	}
	
	public void setVersementPrime(boolean versementPrime) {
		this.versementPrime = versementPrime;
	}
	
	public double getTauxPrime() {
		return tauxPrime;
	}
	
	public void setTauxPrime(double tauxPrime) {
		this.tauxPrime = tauxPrime;
	}
	
	public double getTjm() {
		return tjm;
	}
	
	public void setTjm(double tjm) {
		this.tjm = tjm;
	}
	
	public double getPlafondFrais() {
		return plafondFrais;
	}
	
	public void setPlafondFrais(double plafondFrais) {
		this.plafondFrais = plafondFrais;
	}
	
	public boolean isDepassementFrais() {
		return depassementFrais;
	}
	
	public void setDepassementFrais(boolean depassementFrais) {
		this.depassementFrais = depassementFrais;
	}
	
	public boolean isActif() {
		return actif;
	}
	
	public void setActif(boolean actif) {
		this.actif = actif;
	}
	
	public LocalDate getDateDebutValidite() {
		return dateDebutValidite;
	}
	
	public void setDateDebutValidite(LocalDate dateDebutValidite) {
		this.dateDebutValidite = dateDebutValidite;
	}
	
	public boolean compareTo(NatureMission nature) {
		if (this == nature) {
			return true;
		}
		if (!(nature instanceof NatureMission)) {
			return false;
		}
		
		NatureMission that = nature;
		return (this.libelle == that.libelle) && (this.facture == that.facture)
				&& (this.versementPrime == that.versementPrime) && (this.tauxPrime == that.tauxPrime)
				&& (this.tjm == that.tjm) && (this.plafondFrais == that.plafondFrais)
				&& (this.depassementFrais == that.depassementFrais) && (this.actif == that.actif)
				&& (this.dateDebutValidite == that.dateDebutValidite);
	}
	
	public boolean hasAllAttributes() {
		return this.getLibelle()
				.length() > 0 && this.getPlafondFrais() > 0 && this.getTauxPrime() > 0 && this.getTjm() > 0;
	}
}
