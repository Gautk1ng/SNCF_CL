package controleur;

public class Conge {
	private int idconge;
	private String dateDebut, dateFin, description, statut;
	private int idpersonne, idpersonne_1, idtypeconge;
	
	public Conge(int idconge, String dateDebut, String dateFin, String description, String statut, int idpersonne,
			int idpersonne_1, int idtypeconge) {
		this.idconge = idconge;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.description = description;
		this.statut = statut;
		this.idpersonne = idpersonne;
		this.idpersonne_1 = idpersonne_1;
		this.idtypeconge = idtypeconge;
	}
	
	public Conge(String dateDebut, String dateFin, String description, String statut, int idpersonne,
			int idpersonne_1, int idtypeconge) {
		this.idconge = 0;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.description = description;
		this.statut = statut;
		this.idpersonne = idpersonne;
		this.idpersonne_1 = idpersonne_1;
		this.idtypeconge = idtypeconge;
	}

	public int getIdconge() {
		return idconge;
	}

	public void setIdconge(int idconge) {
		this.idconge = idconge;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public int getIdpersonne() {
		return idpersonne;
	}

	public void setIdpersonne(int idpersonne) {
		this.idpersonne = idpersonne;
	}

	public int getIdpersonne_1() {
		return idpersonne_1;
	}

	public void setIdpersonne_1(int idpersonne_1) {
		this.idpersonne_1 = idpersonne_1;
	}

	public int getIdtypeconge() {
		return idtypeconge;
	}

	public void setIdtypeconge(int idtypeconge) {
		this.idtypeconge = idtypeconge;
	}
	
}
