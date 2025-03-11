package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Absence;
import controleur.Conge;
import controleur.Controleur;
import controleur.Employe;
import controleur.SNCF;
import controleur.Tableau;
import controleur.TypeConge;

public class PanelConge extends PanelPrincipal implements ActionListener {

	private JPanel panelForm = new JPanel();
	private JTextField txtDateDebut = new JTextField();
	private JTextField txtDateFin = new JTextField();
	private JTextField txtDescription = new JTextField();
	private JComboBox<String> txtStatut = new JComboBox<String>();
	private JComboBox<String> txtIDEmploye = new JComboBox<String>();
	private JComboBox<String> txtIDTypeConge = new JComboBox<String>();
	private JTextField txtIDRH = new JTextField();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btValider = new JButton("Valider");
	private JButton btSupprimer = new JButton("Supprimer");
	
	private JTable tableConges;
	private Tableau tableauConges;
	
	private JPanel panelFiltre = new JPanel();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton("Filtrer");
	
	private JLabel nbConges = new JLabel();
	
	public PanelConge() {
		super("Gestion des conges");
		
		this.panelForm.setBackground(Color.cyan);
		this.panelForm.setLayout(new GridLayout(8, 2));
		this.panelForm.setBounds(50, 150, 320, 240);
		this.panelForm.add(new JLabel("Date début :"));
		this.panelForm.add(this.txtDateDebut);
		
		this.panelForm.add(new JLabel("Date Fin :"));
		this.panelForm.add(this.txtDateFin);
		
		this.panelForm.add(new JLabel("Description :"));
		this.panelForm.add(this.txtDescription);
		
		this.panelForm.add(new JLabel("Statut :"));
		this.panelForm.add(this.txtStatut);
		
		this.panelForm.add(new JLabel("Employe ID :"));
		this.panelForm.add(this.txtIDEmploye);
		
		this.panelForm.add(new JLabel("Type Conge ID :"));
		this.panelForm.add(this.txtIDTypeConge);
		
		this.panelForm.add(new JLabel("RH ID :"));
		this.panelForm.add(this.txtIDRH);
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btValider);
		
		this.add(this.panelForm);
		
		this.txtStatut.addItem("En cours");
		this.txtStatut.addItem("Validé");
		this.txtStatut.addItem("Ajouté");
		
		this.remplirID();
		
		//user connecte RH
		this.txtIDRH.setText("" + SNCF.getUserConnecte().getIdpersonne());
		this.txtIDRH.setEditable(false);
		
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
		String entetes [] = {"ID", "Date Début", "Date Fin", "Description", "Statut", "Employé", "TypeCongé"};
		
		this.tableauConges = new Tableau(this.obtenirDonnees(""), entetes);
		this.tableConges = new JTable(this.tableauConges);
		
		JScrollPane uneScroll = new JScrollPane(this.tableConges);
		uneScroll.setBounds(420, 150, 500, 260);
		this.add(uneScroll);
		
		this.btSupprimer.setBounds(100, 400, 140, 30);
		this.add(this.btSupprimer);
		this.btSupprimer.addActionListener(this);
		this.btSupprimer.setBackground(Color.red);
		this.btSupprimer.setVisible(false);
		
		this.tableConges.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int numLigne = 0;
				if (e.getClickCount() >= 1) {
					numLigne = tableConges.getSelectedRow();
					txtDateDebut.setText(tableauConges.getValueAt(numLigne, 1).toString());
					txtDateFin.setText(tableauConges.getValueAt(numLigne, 2).toString());
					txtDescription.setText(tableauConges.getValueAt(numLigne, 3).toString());
					
					btSupprimer.setVisible(true);
					btValider.setText("Modifier");
				}
			}
		});
		
		//installation panel filtre
		this.panelFiltre.setBackground(Color.cyan);
		this.panelFiltre.setBounds(440, 110, 400, 30);
		this.panelFiltre.setLayout(new GridLayout(1,3));
		this.panelFiltre.add(new JLabel("Filtrer par :"));
		this.panelFiltre.add(this.txtFiltre);
		this.panelFiltre.add(this.btFiltrer);
		this.add(this.panelFiltre);
		this.btFiltrer.addActionListener(this);
		
		//installation nbConges
		this.nbConges.setBounds(550, 430, 400, 20);
		this.add(this.nbConges);
		
		this.nbConges.setText("Nombre de congés : " + this.tableauConges.getRowCount());
	}
	
	public void remplirID() {
		//remplir les id des employes
		ArrayList<Employe> lesEmployes = Controleur.selectAllEmployes();
		this.txtIDEmploye.removeAllItems();
		for (Employe unEmploye : lesEmployes) {
			this.txtIDEmploye.addItem(unEmploye.getIdpersonne() + "-" + unEmploye.getNom());
		}
		
		//remplir les id des typeconges
		ArrayList<TypeConge> lesTypes = Controleur.selectAllTypes();
		this.txtIDTypeConge.removeAllItems();
		for (TypeConge unType : lesTypes) {
			this.txtIDTypeConge.addItem(unType.getIdtypeconge() + "-" + unType.getLibelle());
		}
	}

	public Object [][] obtenirDonnees(String filtre) {
		ArrayList<Conge> lesConges;
		
		if (filtre.equals("")) {
			lesConges = Controleur.selectAllConges();
		} else {
			lesConges = Controleur.selectLikeConges(filtre);
		}
		Object [][] matrice = new Object[lesConges.size()][7];
		
		int i = 0;
		for (Conge unConge : lesConges) {
			matrice [i][0] = unConge.getIdconge();
			matrice [i][1] = unConge.getDateDebut();
			matrice [i][2] = unConge.getDateFin();
			matrice [i][3] = unConge.getDescription();
			matrice [i][4] = unConge.getStatut();
			matrice [i][5] = unConge.getIdpersonne_1();
			matrice [i][6] = unConge.getIdtypeconge();
			i++;
		}
		return matrice;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.txtDateDebut.setText("");
			this.txtDateFin.setText("");
			this.txtDescription.setText("");

			btSupprimer.setVisible(false);
			btValider.setText("Valider");
		}
		else if (e.getSource() == this.btSupprimer) {
			//récupérer l'id de la promotion a supprimer
			int numLigne = 0;
			numLigne = this.tableConges.getSelectedRow();
			int idConge = Integer.parseInt(this.tableauConges.getValueAt(numLigne, 0).toString());
			//supprimer dans la BDD
			Controleur.deleteConge(idConge);
			//actualiser l'affichage
			this.tableauConges.setDonnees(this.obtenirDonnees(""));
			this.nbConges.setText("Nombre des congés : " + this.tableauConges.getRowCount());
			//message de confirmation
			JOptionPane.showMessageDialog(this,  "Suppression réussie du congé.");
			//vider les champs
			this.txtDateDebut.setText("");
			this.txtDateFin.setText("");
			this.txtDescription.setText("");

			btSupprimer.setVisible(false);
			btValider.setText("Valider");
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
			String dateDebut = this.txtDateDebut.getText();
			String dateFin = this.txtDateFin.getText();
			String description = this.txtDescription.getText();
			String statut = this.txtStatut.getSelectedItem().toString();
			String tab[] = this.txtIDTypeConge.getSelectedItem().toString().split("-");
			int idTypeConge = Integer.parseInt(tab[0]);
			
			tab = this.txtIDEmploye.getSelectedItem().toString().split("-");
			int idEmploye = Integer.parseInt(tab[0]);
			
			int idRH = Integer.parseInt(this.txtIDRH.getText());
			
			//instanciation de la classe Conge
			Conge unConge = new Conge (dateDebut, dateFin, description, statut, idRH, idEmploye, idTypeConge);
			
			//insertion dans la BDD
			Controleur.insertConge(unConge);
			
			//actualiser l'affichage
			this.tableauConges.setDonnees(this.obtenirDonnees(""));
			this.nbConges.setText("Nombre de congés :" + this.tableauConges.getRowCount());
			
			JOptionPane.showMessageDialog(this, "Insertion réussie du congé", "Insertion Congé", JOptionPane.OK_OPTION);
			
			//on vide les champs
			this.txtDateDebut.setText("");
			this.txtDateFin.setText("");

			btSupprimer.setVisible(false);
			btValider.setText("Valider");
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
			int numLigne = 0;
			numLigne = this.tableConges.getSelectedRow();
			int idConge = Integer.parseInt(this.tableauConges.getValueAt(numLigne, 0).toString());
			String dateDebut = this.txtDateDebut.getText();
			String dateFin = this.txtDateFin.getText();
			String description = this.txtDescription.getText();
			String statut = this.txtStatut.getSelectedItem().toString();
			String tab[] = this.txtIDTypeConge.getSelectedItem().toString().split("-");
			int idTypeConge = Integer.parseInt(tab[0]);
			
			tab = this.txtIDEmploye.getSelectedItem().toString().split("-");
			int idEmploye = Integer.parseInt(tab[0]);
			
			int idRH = Integer.parseInt(this.txtIDRH.getText());
			
			//instanciation de la classe Conge
			Conge unConge = new Conge (idConge, dateDebut, dateFin, description, statut, idRH, idEmploye, idTypeConge);
			
			//insertion dans la BDD
			Controleur.updateConge(unConge);
			
			//actualiser l'affichage
			this.tableauConges.setDonnees(this.obtenirDonnees(""));
			
			JOptionPane.showMessageDialog(this, "Modification réussie du Congé.");
			
			//on vide les champs
			this.txtDateDebut.setText("");
			this.txtDateFin.setText("");

			btSupprimer.setVisible(false);
			btValider.setText("Valider");
		}
		else if (e.getSource() == this.btFiltrer) {
			String filtre = this.txtFiltre.getText();
			
			this.tableauConges.setDonnees(this.obtenirDonnees(filtre));
		}
		
	}
}