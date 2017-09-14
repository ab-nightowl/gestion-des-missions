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
	
	public boolean hasSameAttributesValues(NatureMission nature) {
		if (this == nature) {
			return true;
		}
		
		return (this.facture == nature.facture) && (this.versementPrime == nature.versementPrime)
				&& (this.areFloatsEqual(this.tauxPrime, nature.tauxPrime))
				&& (this.areFloatsEqual(this.tjm, nature.tjm))
				&& (this.areFloatsEqual(this.plafondFrais, nature.plafondFrais))
				&& (this.depassementFrais == nature.depassementFrais);
	}
	
	public boolean hasAllAttributes() {
		return this.getLibelle()
				.length() > 0 && this.getPlafondFrais() > 0 && this.getTauxPrime() > 0 && this.getTjm() > 0;
	}
	
	private boolean areFloatsEqual(double fl1, double fl2) {
		return Math.abs(fl1 - fl2) < 0.00000001;
	}
}
