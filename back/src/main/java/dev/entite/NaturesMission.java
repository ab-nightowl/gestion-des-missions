package dev.entite;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NaturesMission {
	
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
	
	public NaturesMission() {
		
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
}
