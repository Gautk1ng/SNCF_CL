package controleur;

public class Prestataire extends Personne{
	private String contactNom, contactPrenom, adresse;

	public Prestataire(int idpersonne, String nom, String email, String mdp, String statut, String contactNom,
			String contactPrenom, String adresse) {
		super(idpersonne, nom, email, mdp, statut);
		this.contactNom = contactNom;
		this.contactPrenom = contactPrenom;
		this.adresse = adresse;
	}
	
	public Prestataire(String nom, String email, String mdp, String statut, String contactNom,
			String contactPrenom, String adresse) {
		super(nom, email, mdp, statut);
		this.contactNom = contactNom;
		this.contactPrenom = contactPrenom;
		this.adresse = adresse;
	}

	public String getContactNom() {
		return contactNom;
	}

	public void setContactNom(String contactNom) {
		this.contactNom = contactNom;
	}

	public String getContactPrenom() {
		return contactPrenom;
	}

	public void setContactPrenom(String contactPrenom) {
		this.contactPrenom = contactPrenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
}
