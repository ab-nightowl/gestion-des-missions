package dev.entite;

public enum UtilisateurRole {
	ROLE_ADMINISTRATEUR, ROLE_MANAGER, ROLE_EMPLOYE;
	
	private UtilisateurRole() {
	}
	
	@Override
	public String toString() {
		return this.name();
	}
}
