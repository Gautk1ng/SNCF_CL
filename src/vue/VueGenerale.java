package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controleur.SNCF;

public class VueGenerale extends JFrame implements ActionListener {
	
	private JPanel panelMenu = new JPanel();
	
	private JButton btProfil = new JButton("Profil");
	private JButton btAbsences = new JButton("Absences");
	private JButton btConges = new JButton("Conges");
	private JButton btCommandes = new JButton("Commandes");
	private JButton btEmployes = new JButton("Employes");
	private JButton btPrestataire = new JButton("Prestataire");
	private JButton btQuitter = new JButton("Quitter");
	
	//instanciation des panels
	private PanelProfil unPanelProfil = new PanelProfil();
	private PanelAbsence unPanelAbsence = new PanelAbsence();
	private PanelConge unPanelConge = new PanelConge();
	private PanelCommande unPanelCommande = new PanelCommande();
	private PanelEmploye unPanelEmploye = new PanelEmploye();
	private PanelPrestataire unPanelPrestataire = new PanelPrestataire();
	
	public VueGenerale() {
		this.setTitle("Application Scolarité Efrei 2025");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBounds(100, 100, 1000, 600);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.darkGray);
		
		//construction du panel menu
		this.panelMenu.setBackground(Color.darkGray);
		this.panelMenu.setBounds(50, 10, 900, 40);
		this.panelMenu.setLayout(new GridLayout(1, 6));
		this.panelMenu.add(this.btProfil);
		this.panelMenu.add(this.btAbsences);
		this.panelMenu.add(this.btConges);
		this.panelMenu.add(this.btCommandes);
		this.panelMenu.add(this.btEmployes);
		this.panelMenu.add(this.btPrestataire);
		this.panelMenu.add(this.btQuitter);
		this.add(this.panelMenu);
		
		//rendre les boutons ecoutables
		this.btProfil.addActionListener(this);
		this.btAbsences.addActionListener(this);
		this.btConges.addActionListener(this);
		this.btCommandes.addActionListener(this);
		this.btEmployes.addActionListener(this);
		this.btPrestataire.addActionListener(this);
		this.btQuitter.addActionListener(this);
		
		//Ajout des panels dans la fenetre
		this.add(unPanelProfil);
		this.add(unPanelAbsence);
		this.add(unPanelConge);
		this.add(unPanelCommande);
		this.add(unPanelEmploye);
		this.add(unPanelPrestataire);

		this.setVisible(true);
	}
	
	public void afficherPanel (int choix) {
		unPanelProfil.setVisible(false);
		unPanelAbsence.setVisible(false);
		unPanelConge.setVisible(false);
		unPanelCommande.setVisible(false);
		unPanelEmploye.setVisible(false);
		unPanelPrestataire.setVisible(false);
		
		switch(choix) {
		case 1 : unPanelProfil.setVisible(true); break;
		case 2 : unPanelAbsence.setVisible(true); break;
		case 3 : unPanelConge.setVisible(true); break;
		case 4 : unPanelCommande.setVisible(true); break;
		case 5 : unPanelEmploye.setVisible(true); break;
		case 6 : unPanelPrestataire.setVisible(true); break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btQuitter) {
			int retour = JOptionPane.showConfirmDialog(this, 
					"Voulez-vous quitter l'application ?",
					"Quitter l'Application", JOptionPane.YES_NO_OPTION);
			
			if(retour == 0) {
				SNCF.creerVueGenerale(false);
				SNCF.rendreVisible(true);	
			}
		}
		else if(e.getSource() == this.btProfil) {
			this.afficherPanel(1);
		}
		else if(e.getSource() == this.btAbsences) {
			this.afficherPanel(2);
		}
		else if(e.getSource() == this.btConges) {
			this.afficherPanel(3);
		}
		else if(e.getSource() == this.btCommandes) {
			this.afficherPanel(4);
		}
		else if(e.getSource() == this.btEmployes) {
			this.afficherPanel(5);
		}
		else if(e.getSource() == this.btPrestataire) {
			this.afficherPanel(6);
		}
		
	}

}
