package controleur;

public class Absence {
	private int idabsence;
	private String dateDebut, dateFin, motif, typeAbsence;
	private int idpersonne, idpersonne_1;
	private String statut;
	
	public Absence(int idabsence, String dateDebut, String dateFin, String motif, String typeAbsence, int idpersonne,
			int idpersonne_1, String statut) {
		this.idabsence = idabsence;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.motif = motif;
		this.typeAbsence = typeAbsence;
		this.idpersonne = idpersonne;
		this.idpersonne_1 = idpersonne_1;
		this.statut = statut;
	}
	
	public Absence(String dateDebut, String dateFin, String motif, String typeAbsence, int idpersonne,
			int idpersonne_1, String statut) {
		this.idabsence = 0;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.motif = motif;
		this.typeAbsence = typeAbsence;
		this.idpersonne = idpersonne;
		this.idpersonne_1 = idpersonne_1;
		this.statut = statut;
	}

	public int getIdabsence() {
		return idabsence;
	}

	public void setIdabsence(int idabsence) {
		this.idabsence = idabsence;
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

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public String getTypeAbsence() {
		return typeAbsence;
	}

	public void setTypeAbsence(String typeAbsence) {
		this.typeAbsence = typeAbsence;
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
	
	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	} 
	
}
