package dev.entite;

public enum Statut {
	INITIALE, EN_ATTENTE_VALIDATION, VALIDEE, REJETEE;
	
	private Statut() {
	}
	
	@Override
	public String toString() {
		return this.name();
	}
}
