package controleur;

public class TypeConge {
	private int idtypeconge;
	private String libelle;
	
	public TypeConge(int idtypeconge, String libelle) {
		this.idtypeconge = idtypeconge;
		this.libelle = libelle;
	}
	
	public TypeConge(String libelle) {
		this.idtypeconge = 0;
		this.libelle = libelle;
	}

	public int getIdtypeconge() {
		return idtypeconge;
	}

	public void setIdtypeconge(int idtypeconge) {
		this.idtypeconge = idtypeconge;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
}
