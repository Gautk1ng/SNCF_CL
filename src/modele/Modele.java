package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Absence;
import controleur.Conge;
import controleur.Employe;
import controleur.RH;
import controleur.TypeConge;

public class Modele {

	private static Connexion uneConnexion = new Connexion("localhost", "sncf", "root", "");
	
	/************************ Gestion des absences *********************************/
	public static void insertAbsence (Absence uneAbsence) {
		String requete = "insert into absence values (null, '" 
				+ uneAbsence.getDateDebut() + "','" 
				+ uneAbsence.getDateFin() + "','" 
				+ uneAbsence.getMotif() + "','" 
				+ uneAbsence.getTypeAbsence() + "','"
				+ uneAbsence.getIdpersonne() + "','" 
				+ uneAbsence.getIdpersonne_1() + "','"
				+ uneAbsence.getStatut() + "');";
		executerRequete(requete);
	}
	public static void deleteAbsence (int idAbsence) {
		String requete = "delete from absence where id_absence = " + idAbsence + ";";
		executerRequete(requete);
	}
	public static void updateAbsence (Absence uneAbsence) {
		String requete = "update absence set date_debut ='" + uneAbsence.getDateDebut() 
				+ "', date_fin = '"+ uneAbsence.getDateFin() 
				+ "', motif = '" + uneAbsence.getMotif() 
				+ "', typeAbsence = '" + uneAbsence.getTypeAbsence() 
				+ "', id_personne = '" + uneAbsence.getIdpersonne() 
				+ "', id_personne_1 = '" + uneAbsence.getIdpersonne_1() 
				+ "', statut = '" + uneAbsence.getStatut() 
				+ "' where id_absence = " + uneAbsence.getIdabsence() + ";";
		executerRequete(requete);
	}
	public static ArrayList<Absence> selectAllAbsences() {
		ArrayList<Absence> lesAbsences = new ArrayList<Absence>();
		String requete = "select * from absence;";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete);
			// Parcourir les resultat et instancier la classe Absence
			while (lesResultats.next()) {
				// Instanciation de la classe : objet Absence
				Absence uneAbsence = new Absence (
					lesResultats.getInt("id_absence"), lesResultats.getString("date_debut"),
					lesResultats.getString("date_fin"), lesResultats.getString("motif"),
					lesResultats.getString("typeAbsence"), lesResultats.getInt("id_personne"),
					lesResultats.getInt("id_personne_1"), lesResultats.getString("statut"));
				// Insertion de l'objet dans l'ArrayList
				lesAbsences.add(uneAbsence);
				}
			unStat.close();
			uneConnexion.deConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesAbsences;
	}
	public static ArrayList<Absence> selectLikeAbsences (String filtre) {
		ArrayList<Absence> lesAbsences = new ArrayList<Absence>();
		String requete = "select * from absence where motif like '%" + filtre + "%' or typeabsence like '%" + filtre + "%';";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete);
			// Parcourir les resultat et instancier la classe Absence
			while (lesResultats.next()) {
				// Instanciation de la classe : objet Absence
				Absence uneAbsence = new Absence (
					lesResultats.getInt("id_absence"), lesResultats.getString("date_debut"),
					lesResultats.getString("date_fin"), lesResultats.getString("motif"),
					lesResultats.getString("typeAbsence"), lesResultats.getInt("id_personne"),
					lesResultats.getInt("id_personne_1"), lesResultats.getString("statut"));
				// Insertion de l'objet dans l'ArrayList
				lesAbsences.add(uneAbsence);
				}
			unStat.close();
			uneConnexion.deConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesAbsences;
	}
	public static Absence selectWhereAbsence (int idAbsence) {
		String requete = "select * from absence where idabsence = " + idAbsence + ";";
		Absence uneAbsence = null;
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				// Instanciation de la classe : objet Absence
				uneAbsence = new Absence (
					unResultat.getInt("id_absence"), unResultat.getString("date_debut"),
					unResultat.getString("date_fin"), unResultat.getString("motif"),
					unResultat.getString("typeAbsence"), unResultat.getInt("id_personne"),
					unResultat.getInt("id_personne_1"), unResultat.getString("statut"));
				}
			unStat.close();
			uneConnexion.deConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return uneAbsence;
	}
	
	/************************ Gestion des conges **********************************/
	public static void insertConge (Conge uneConge) {
		String requete = "insert into Conge values (null, '" 
				+ uneConge.getDateDebut() + "','" 
				+ uneConge.getDateFin() + "','" 
				+ uneConge.getDescription() + "','" 
				+ uneConge.getStatut() + "','"
				+ uneConge.getIdpersonne() + "','" 
				+ uneConge.getIdpersonne_1() + "','"
				+ uneConge.getIdtypeconge()+ "');";
		executerRequete(requete);
	}
	public static void deleteConge (int idConge) {
		String requete = "delete from Conge where id_conge = " + idConge + ";";
		executerRequete(requete);
	}
	public static void updateConge (Conge uneConge) {
		String requete = "update Conge set "
				+ "date_debut ='" + uneConge.getDateDebut() 
				+ "', date_fin = '"+ uneConge.getDateFin() 
				+ "', description = '" + uneConge.getDescription() 
				+ "', statut = '" + uneConge.getStatut() 
				+ "', id_typeConge = '" + uneConge.getIdtypeconge() 
				+ "', id_personne_1 = '" + uneConge.getIdpersonne_1() 
				+ "' where id_conge = " + uneConge.getIdconge() + ";";
		executerRequete(requete);
	}
	public static ArrayList<Conge> selectAllConges() {
		ArrayList<Conge> lesConges = new ArrayList<Conge>();
		String requete = "select * from conge;";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete);
			// Parcourir les resultat et instancier la classe Conge
			while (lesResultats.next()) {
				// Instanciation de la classe : objet Conge
				Conge uneConge = new Conge (
					lesResultats.getInt("id_conge"), lesResultats.getString("date_debut"),
					lesResultats.getString("date_fin"), lesResultats.getString("description"),
					lesResultats.getString("statut"), lesResultats.getInt("id_personne"),
					lesResultats.getInt("id_personne_1"), lesResultats.getInt("id_typeConge"));
				// Insertion de l'objet dans l'ArrayList
				lesConges.add(uneConge);
				}
			unStat.close();
			uneConnexion.deConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesConges;
	}
	
	public static ArrayList<Conge> selectLikeConges (String filtre) {
		ArrayList<Conge> lesConges = new ArrayList<Conge>();
		String requete = "select * from conge where statut like '%" + filtre + "%' or description like '%" + filtre + "%';";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete);
			// Parcourir les resultat et instancier la classe Absence
			while (lesResultats.next()) {
				// Instanciation de la classe : objet Absence
				Conge unConge = new Conge (
					lesResultats.getInt("id_conge"), lesResultats.getString("date_debut"),
					lesResultats.getString("date_fin"), lesResultats.getString("description"),
					lesResultats.getString("statut"), lesResultats.getInt("id_personne"),
					lesResultats.getInt("id_personne_1"), lesResultats.getInt("id_typeConge"));
				// Insertion de l'objet dans l'ArrayList
				lesConges.add(unConge);
				}
			unStat.close();
			uneConnexion.deConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesConges;
	}
	public static Conge selectWhereConge (int idConge) {
		String requete = "select * from conge where id_conge = " + idConge + ";";
		Conge uneConge = null;
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete);
			if(lesResultats.next()) {
				// Instanciation de la classe : objet Conge
				uneConge = new Conge (
					lesResultats.getInt("id_conge"), lesResultats.getString("date_debut"),
					lesResultats.getString("date_fin"), lesResultats.getString("description"),
					lesResultats.getString("statut"), lesResultats.getInt("id_personne"),
					lesResultats.getInt("id_personne_1"), lesResultats.getInt("id_typeConge"));
				}
			unStat.close();
			uneConnexion.deConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return uneConge;
	}
	
	/************************ Gestion des TypeConges *************************************/
	public static ArrayList<TypeConge> selectAllTypes() {
		ArrayList<TypeConge> lesTypes = new ArrayList<TypeConge>();
		String requete = "select * from typeconge;";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete);
			// Parcourir les resultat et instancier la classe TypeConge
			while (lesResultats.next()) {
				// Instanciation de la classe : objet TypeConge
				TypeConge unType = new TypeConge (lesResultats.getInt("id_typeConge"), lesResultats.getString("libelle"));
				// Insertion de l'objet dans l'ArrayList
				lesTypes.add(unType);
				}
			unStat.close();
			uneConnexion.deConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesTypes;
	}
	
	/************************ Gestion des Employes *************************************/
	public static ArrayList<Employe> selectAllEmployes() {
		ArrayList<Employe> lesEmployes = new ArrayList<Employe>();
		String requete = "select * from vueEmploye;";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete);
			// Parcourir les resultat et instancier la classe Employe
			while (lesResultats.next()) {
				// Instanciation de la classe : objet Employe
				Employe unEmploye = new Employe (
						lesResultats.getInt("id_personne"), lesResultats.getString("nom"),
						lesResultats.getString("email"), lesResultats.getString("mdp"),
						lesResultats.getString("statut"), lesResultats.getString("prenom"),
						lesResultats.getString("date_embauche"), lesResultats.getString("poste")
						);
				// Insertion de l'objet dans l'ArrayList
				lesEmployes.add(unEmploye);
				}
			unStat.close();
			uneConnexion.deConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesEmployes;
	}
	
	/************************ Gestion des RH *************************************/
	public static void updateRH (RH unRH) {
		String requete = "call updateRH('" + unRH.getIdpersonne() +"','" + unRH.getNom() +"','" + unRH.getEmail() +"'"
				+ ",'" + unRH.getStatut() + "','" + unRH.getPrenom() + "','" + unRH.getDateEmbauche() + "'"
				+ ",'" + unRH.getPoste() + "','" + unRH.getResponsabilite() +"');";
		executerRequete(requete);
	}
	
	public static RH selectWhereRH (String email, String mdp) {
		String requete = "select * from vueRH where email = '" + email + "' and mdp = '" + mdp +"';";
		RH unRH = null;
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete);
			if(lesResultats.next()) {
				// Instanciation de la classe : objet RH
				unRH = new RH (
					lesResultats.getInt("id_personne"), lesResultats.getString("nom"),
					lesResultats.getString("email"), lesResultats.getString("mdp"),
					lesResultats.getString("statut"), lesResultats.getString("prenom"),
					lesResultats.getString("date_embauche"), lesResultats.getString("poste"),
					lesResultats.getString("responsabilite"));
				}
			unStat.close();
			uneConnexion.deConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return unRH;
	}
	
	/************************ Autres méthodes ************************************/
	public static void executerRequete (String requete) {
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneConnexion.deConnecter();
		} 
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
	}
}
