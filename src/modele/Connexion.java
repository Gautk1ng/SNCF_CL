package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	private String serveur, bdd, user, mdp;
	private Connection maConnexion;
	
	public Connexion(String serveur, String bdd, String user, String mdp) {
		this.serveur = serveur;
		this.bdd = bdd;
		this.user = user;
		this.mdp = mdp;
		this.maConnexion = null;
	}

	public void chargerPilote() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} 
		catch(ClassNotFoundException exp) {
			System.out.println("Absence du pilote JDBC.");
		}
	}
	public void seConnecter () {
		this.chargerPilote();
		String url = "jdbc:mysql://" + this.serveur + "/" + this.bdd;
		try {
			this.maConnexion = DriverManager.getConnection(url, this.user, this.mdp);
		} 
		catch(SQLException exp) {
			System.out.println("Connexion impossible a url : " + url);
		}
	}
	public void deConnecter () {
		try {
			if (this.maConnexion != null) {
				this.maConnexion.close();
			}
		} 
		catch(SQLException exp){
			System.out.println("Impossible de fermer la connexion JDBC");
		}
	}
	public Connection getMaConnexion () {
		return this.maConnexion;
	}
}
