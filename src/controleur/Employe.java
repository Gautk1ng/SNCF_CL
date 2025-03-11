package controleur;

public class Employe extends Personne {
	protected String prenom;
	protected String dateEmbauche;
	protected String poste;
	
	public Employe(int idpersonne, String nom, String email, String mdp, String statut, String prenom,
			String dateEmbauche, String poste) {
		super(idpersonne, nom, email, mdp, statut); // Personne
		this.prenom = prenom;
		this.dateEmbauche = dateEmbauche;
		this.poste = poste;
	}
	
	public Employe(String nom, String email, String mdp, String statut, String prenom,
			String dateEmbauche, String poste) {
		super(nom, email, mdp, statut); // Personne
		this.prenom = prenom;
		this.dateEmbauche = dateEmbauche;
		this.poste = poste;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDateEmbauche() {
		return dateEmbauche;
	}

	public void setDateEmbauche(String dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}

	public String getPoste() {
		return poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}
	
}
