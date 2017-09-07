package dev.entite;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Statut {

	public enum STATUTS {
		DEMANDE_INITIALE, DEMANDE_EN_ATTENTE_VALIDATION, DEMANDE_VALIDEE, DEMANDE_REJETEE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Enumerated(EnumType.STRING)
	private STATUTS statut;

	public Statut() {
		super();
	}

	public Statut(STATUTS statut) {
		this.statut = statut;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public STATUTS getStatut() {
		return statut;
	}

	public void setStatut(STATUTS statut) {
		this.statut = statut;
	}
}
