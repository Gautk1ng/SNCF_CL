package controleur;

import java.util.ArrayList;

import modele.Modele;

public class Controleur {

	/************************ Gestion des absences *********************************/
	public static void insertAbsence (Absence uneAbsence) {
		Modele.insertAbsence(uneAbsence);
	}
	
	public static void deleteAbsence (int idAbsence) {
		Modele.deleteAbsence(idAbsence);
	}
	
	public static void updateAbsence (Absence uneAbsence) {
		Modele.updateAbsence(uneAbsence);
	}
	
	public static ArrayList<Absence> selectAllAbsences () {
		return Modele.selectAllAbsences();
	}
	
	public static ArrayList<Absence> selectLikeAbsences (String filtre) {
		return Modele.selectLikeAbsences(filtre);
	}
	
	public static Absence selectWhereAbsence (int idAbsence) {
		return Modele.selectWhereAbsence(idAbsence);
	}
	
	/************************ Gestion des conges *********************************/
	public static void insertConge (Conge unConge) {
		Modele.insertConge(unConge);
	}
	
	public static void deleteConge (int idConge) {
		Modele.deleteConge(idConge);
	}
	
	public static void updateConge (Conge unConge) {
		Modele.updateConge(unConge);
	}
	
	public static ArrayList<Conge> selectAllConges () {
		return Modele.selectAllConges();
	}
	
	public static ArrayList<Conge> selectLikeConges (String filtre) {
		return Modele.selectLikeConges(filtre);
	}
	
	public static Conge selectWhereConge (int idConge) {
		return Modele.selectWhereConge(idConge);
	}
	
	/************************ Gestion des typeconges *********************************/
	public static ArrayList<TypeConge> selectAllTypes () {
		return Modele.selectAllTypes();
	}
	
	/************************ Gestion des Employes *********************************/
	public static ArrayList<Employe> selectAllEmployes () {
		return Modele.selectAllEmployes();
	}
	
	/************************ Gestion des RH *************************************/
	public static RH selectWhereRH (String email, String mdp) {
		return Modele.selectWhereRH(email, mdp);
	}
	
	public static void updateRH (RH unRH) {
		Modele.updateRH(unRH);
	}
}
