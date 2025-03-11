package controleur;

public class TypeCommande {
	private int idtypecommande;
	private String libelle;
	
	public TypeCommande(int idtypecommande, String libelle) {
		this.idtypecommande = idtypecommande;
		this.libelle = libelle;
	}
	
	public TypeCommande(String libelle) {
		this.idtypecommande = 0;
		this.libelle = libelle;
	}

	public int getIdtypecommande() {
		return idtypecommande;
	}

	public void setIdtypecommande(int idtypecommande) {
		this.idtypecommande = idtypecommande;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
}
