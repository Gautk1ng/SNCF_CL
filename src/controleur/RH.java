package controleur;

public class RH extends Employe{
	private String responsabilite;

	public RH(int idpersonne, String nom, String email, String mdp, String statut, String prenom, String dateEmbauche,
			String poste, String responsabilite) {
		super(idpersonne, nom, email, mdp, statut, prenom, dateEmbauche, poste); // Employe
		this.responsabilite = responsabilite;
	}
	
	public RH(String nom, String email, String mdp, String statut, String prenom, String dateEmbauche,
			String poste, String responsabilite) {
		super(nom, email, mdp, statut, prenom, dateEmbauche, poste); // Employe
		this.responsabilite = responsabilite;
	}

	public String getResponsabilite() {
		return responsabilite;
	}

	public void setResponsabilite(String responsabilite) {
		this.responsabilite = responsabilite;
	}
	
}
