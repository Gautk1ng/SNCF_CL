package controleur;

public class Personne {
	protected int idpersonne;
	protected String nom, email, mdp, statut;
	
	public Personne(int idpersonne, String nom, String email, String mdp, String statut) {
		this.idpersonne = idpersonne;
		this.nom = nom;
		this.email = email;
		this.mdp = mdp;
		this.statut = statut;
	}
	
	public Personne(String nom, String email, String mdp, String statut) {
		this.idpersonne = 0;
		this.nom = nom;
		this.email = email;
		this.mdp = mdp;
		this.statut = statut;
	}

	public int getIdpersonne() {
		return idpersonne;
	}

	public void setIdpersonne(int idpersonne) {
		this.idpersonne = idpersonne;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}
	
}
