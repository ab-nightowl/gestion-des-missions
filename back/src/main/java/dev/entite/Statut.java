package dev.entite;

public enum Statut {
	DEMANDE_INITIALE, DEMANDE_EN_ATTENTE_VALIDATION, DEMANDE_VALIDEE, DEMANDE_REJETEE;
	
	private Statut() {
	}
	
	@Override
	public String toString() {
		return this.name();
	}
}
