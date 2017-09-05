package dev.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Utilisateur {
	public enum ROLES {
		ROLE_ADMINISTRATEUR, ROLE_MANAGER, ROLE_EMPLOYE
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true)
	private String matricule;
	@Enumerated(EnumType.STRING)
	private ROLES role;
	
	public Utilisateur() {
	}
	
	public Utilisateur(String matricule, ROLES role) {
		super();
		this.matricule = matricule;
		this.role = role;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getMatricule() {
		return matricule;
	}
	
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	
	public ROLES getRole() {
		return role;
	}
	
	public void setRole(ROLES role) {
		this.role = role;
	}
}
