package controleur;

import vue.VueGenerale;
import vue.VueConnexion;

public class SNCF {
	
	private static VueConnexion uneVueConnexion;
	private static VueGenerale uneVueGenerale;
	private static RH userConnecte;
	
	public static RH getUserConnecte() {
		return userConnecte;
	}
	public static void setUserConnecte(RH userConnecte) {
		SNCF.userConnecte = userConnecte;
	}
	
	public static void creerVueGenerale (boolean action) {
		if (action == true) {
			uneVueGenerale = new VueGenerale();
			uneVueGenerale.setVisible(true);
		} else {
			uneVueGenerale.dispose();
		}
	}
	public static void rendreVisible (boolean action) {
		uneVueConnexion.setVisible(action);
	}
	public static void main (String args[]) {
		uneVueConnexion = new VueConnexion();
	}
}
