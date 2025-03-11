package controleur;

public class Commande {
	private int idcommande;
	private String dateCommande, description, statutValidation;
	private int quantite, idpersonne, idtypecommande, idpersonne_1, idpersonne_2;
	
	public Commande(int idcommande, String dateCommande, String description, String statutValidation, int quantite,
			int idpersonne, int idtypecommande, int idpersonne_1, int idpersonne_2) {
		this.idcommande = idcommande;
		this.dateCommande = dateCommande;
		this.description = description;
		this.statutValidation = statutValidation;
		this.quantite = quantite;
		this.idpersonne = idpersonne;
		this.idtypecommande = idtypecommande;
		this.idpersonne_1 = idpersonne_1;
		this.idpersonne_2 = idpersonne_2;
	}
	
	public Commande(String dateCommande, String description, String statutValidation, int quantite,
			int idpersonne, int idtypecommande, int idpersonne_1, int idpersonne_2) {
		this.idcommande = 0;
		this.dateCommande = dateCommande;
		this.description = description;
		this.statutValidation = statutValidation;
		this.quantite = quantite;
		this.idpersonne = idpersonne;
		this.idtypecommande = idtypecommande;
		this.idpersonne_1 = idpersonne_1;
		this.idpersonne_2 = idpersonne_2;
	}

	public int getIdcommande() {
		return idcommande;
	}

	public void setIdcommande(int idcommande) {
		this.idcommande = idcommande;
	}

	public String getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(String dateCommande) {
		this.dateCommande = dateCommande;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatutValidation() {
		return statutValidation;
	}

	public void setStatutValidation(String statutValidation) {
		this.statutValidation = statutValidation;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getIdpersonne() {
		return idpersonne;
	}

	public void setIdpersonne(int idpersonne) {
		this.idpersonne = idpersonne;
	}

	public int getIdtypecommande() {
		return idtypecommande;
	}

	public void setIdtypecommande(int idtypecommande) {
		this.idtypecommande = idtypecommande;
	}

	public int getIdpersonne_1() {
		return idpersonne_1;
	}

	public void setIdpersonne_1(int idpersonne_1) {
		this.idpersonne_1 = idpersonne_1;
	}

	public int getIdpersonne_2() {
		return idpersonne_2;
	}

	public void setIdpersonne_2(int idpersonne_2) {
		this.idpersonne_2 = idpersonne_2;
	}
	
}
