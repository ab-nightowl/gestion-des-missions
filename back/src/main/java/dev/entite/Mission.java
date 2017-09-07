package dev.entite;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Mission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	@ManyToOne
	@JoinColumn(name="nature_mission_init_id")
	private NatureMission natureMissionInit;
	@ManyToOne
	@JoinColumn(name="nature_mission_id")
	private NatureMission natureMission;
	@ManyToOne
	@JoinColumn(name="ville_depart_id")
	private Ville villeDepart;
	@ManyToOne
	@JoinColumn(name="ville_arrivee_id")
	private Ville villeArrivee;
	@ManyToOne
	@JoinColumn(name="transport_id")
	private Transport transport;
	private double prime;
	@ManyToOne
	@JoinColumn(name="statut_id")
	private Statut statut;
	private String utilisateurMatricule;

	public Mission() {
		super();
	}

	public Mission(LocalDate dateDebut, LocalDate dateFin, NatureMission natureMissionInit, NatureMission natureMission,
			Ville villeDepart, Ville villeArrivee, Transport transport, double prime, Statut statut,
			String utilisateurMatricule) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.natureMissionInit = natureMissionInit;
		this.natureMission = natureMission;
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.transport = transport;
		this.prime = prime;
		this.statut = statut;
		this.utilisateurMatricule = utilisateurMatricule;
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

	public NatureMission getNatureMission() {
		return natureMission;
	}

	public void setNatureMission(NatureMission natureMission) {
		this.natureMission = natureMission;
	}

	public NatureMission getNatureMissionInit() {
		return natureMissionInit;
	}

	public void setNatureMissionInit(NatureMission natureMissionInit) {
		this.natureMissionInit = natureMissionInit;
	}

	public Ville getVilleDepart() {
		return villeDepart;
	}

	public void setVilleDepart(Ville villeDepart) {
		this.villeDepart = villeDepart;
	}

	public Ville getVilleArrivee() {
		return villeArrivee;
	}

	public void setVilleArrivee(Ville villeArrivee) {
		this.villeArrivee = villeArrivee;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	public double getPrime() {
		return prime;
	}

	public void setPrime(double prime) {
		this.prime = prime;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public String getUtilisateurMatricule() {
		return utilisateurMatricule;
	}

	public void setUtilisateurMatricule(String utilisateurMatricule) {
		this.utilisateurMatricule = utilisateurMatricule;
	}

}
