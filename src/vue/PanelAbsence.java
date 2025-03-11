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
import controleur.Controleur;
import controleur.Employe;
import controleur.SNCF;
import controleur.Tableau;

public class PanelAbsence extends PanelPrincipal implements ActionListener {

	private JPanel panelForm = new JPanel();
	private JTextField txtDateDebut = new JTextField();
	private JTextField txtDateFin = new JTextField();
	private JTextField txtMotif = new JTextField();
	private JTextField txtType = new JTextField();
	private JComboBox<String> txtIDEmploye = new JComboBox<String>();
	private JTextField txtIDRH = new JTextField();
	private JComboBox<String> txtStatut = new JComboBox<String>();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btValider = new JButton("Valider");
	private JButton btSupprimer = new JButton("Supprimer");
	
	private JTable tableAbsences;
	private Tableau tableauAbsences;
	
	private JPanel panelFiltre = new JPanel();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton("Filtrer");
	
	private JLabel nbAbsences = new JLabel();
	
	public PanelAbsence() {
		super("Gestion des absences");
		
		this.panelForm.setBackground(Color.cyan);
		this.panelForm.setLayout(new GridLayout(8, 2));
		this.panelForm.setBounds(50, 150, 320, 240);
		this.panelForm.add(new JLabel("Date début :"));
		this.panelForm.add(this.txtDateDebut);
		
		this.panelForm.add(new JLabel("Date Fin :"));
		this.panelForm.add(this.txtDateFin);
		
		this.panelForm.add(new JLabel("Motif Absence :"));
		this.panelForm.add(this.txtMotif);
		
		this.panelForm.add(new JLabel("Type Absence :"));
		this.panelForm.add(this.txtType);
		
		this.panelForm.add(new JLabel("Employe ID :"));
		this.panelForm.add(this.txtIDEmploye);
		
		this.panelForm.add(new JLabel("RH ID :"));
		this.panelForm.add(this.txtIDRH);
		
		this.panelForm.add(new JLabel("Statut :"));
		this.panelForm.add(this.txtStatut);
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btValider);
		
		this.add(this.panelForm);
		
		this.txtStatut.addItem("en cours");
		this.txtStatut.addItem("validee");
		this.txtStatut.addItem("refusee");
		
		this.remplirID();
		
		//user connecte RH
		this.txtIDRH.setText("" + SNCF.getUserConnecte().getIdpersonne());
		this.txtIDRH.setEditable(false);
		
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
		String entetes [] = {"ID", "Date Début", "Date Fin", "Motif", "Type", "Employe", "RH", "Statut"};
		
		this.tableauAbsences = new Tableau(this.obtenirDonnees(""), entetes);
		this.tableAbsences = new JTable(this.tableauAbsences);
		
		JScrollPane uneScroll = new JScrollPane(this.tableAbsences);
		uneScroll.setBounds(420, 150, 500, 260);
		this.add(uneScroll);
		
		this.btSupprimer.setBounds(100, 400, 140, 30);
		this.add(this.btSupprimer);
		this.btSupprimer.addActionListener(this);
		this.btSupprimer.setBackground(Color.red);
		this.btSupprimer.setVisible(false);
		
		this.tableAbsences.addMouseListener(new MouseListener() {
			
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
					numLigne = tableAbsences.getSelectedRow();
					txtDateDebut.setText(tableauAbsences.getValueAt(numLigne, 1).toString());
					txtDateFin.setText(tableauAbsences.getValueAt(numLigne, 2).toString());
					txtMotif.setText(tableauAbsences.getValueAt(numLigne, 3).toString());
					txtType.setText(tableauAbsences.getValueAt(numLigne, 4).toString());
					txtIDRH.setText(tableauAbsences.getValueAt(numLigne, 6).toString());
					
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
		
		//installation nbAbsences
		this.nbAbsences.setBounds(550, 430, 400, 20);
		this.add(this.nbAbsences);
		
		this.nbAbsences.setText("Nombre d'absences : " + this.tableauAbsences.getRowCount());
	}
	
	public void remplirID() {
		//remplir les id des employes
		ArrayList<Employe> lesEmployes = Controleur.selectAllEmployes();
		this.txtIDEmploye.removeAllItems();
		for (Employe unEmploye : lesEmployes) {
			this.txtIDEmploye.addItem(unEmploye.getIdpersonne()+"-"+unEmploye.getNom());
		}
	}

	public Object [][] obtenirDonnees(String filtre) {
		ArrayList<Absence> lesAbsences;
		
		if (filtre.equals("")) {
			lesAbsences = Controleur.selectAllAbsences();
		} else {
			lesAbsences = Controleur.selectLikeAbsences(filtre);
		}
		Object [][] matrice = new Object[lesAbsences.size()][8];
		
		int i = 0;
		for (Absence uneAbsence : lesAbsences) {
			matrice [i][0] = uneAbsence.getIdabsence();
			matrice [i][1] = uneAbsence.getDateDebut();
			matrice [i][2] = uneAbsence.getDateFin();
			matrice [i][3] = uneAbsence.getMotif();
			matrice [i][4] = uneAbsence.getTypeAbsence();
			matrice [i][5] = uneAbsence.getIdpersonne();
			matrice [i][6] = uneAbsence.getIdpersonne_1();
			matrice [i][7] = uneAbsence.getStatut();
			i++;
		}
		return matrice;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.txtDateDebut.setText("");
			this.txtDateFin.setText("");
			this.txtMotif.setText("");
			this.txtType.setText("");
			this.txtIDRH.setText("");
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
		}
		else if (e.getSource() == this.btSupprimer) {
			//récupérer l'id de la promotion a supprimer
			int numLigne = 0;
			numLigne = this.tableAbsences.getSelectedRow();
			int idAbsence = Integer.parseInt(this.tableauAbsences.getValueAt(numLigne, 0).toString());
			//supprimer dans la BDD
			Controleur.deleteAbsence(idAbsence);
			//actualiser l'affichage
			this.tableauAbsences.setDonnees(this.obtenirDonnees(""));
			this.nbAbsences.setText("Nombre d'absences : " + this.tableauAbsences.getRowCount());
			//message de confirmation
			JOptionPane.showMessageDialog(this,  "Suppression réussie de la promotion.");
			//vider les champs
			this.txtDateDebut.setText("");
			this.txtDateFin.setText("");
			this.txtMotif.setText("");
			this.txtType.setText("");

			this.txtIDRH.setText("");
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
			String dateDebut = this.txtDateDebut.getText();
			String dateFin = this.txtDateFin.getText();
			String motif = this.txtMotif.getText();
			String typeAbsence = this.txtType.getText();
			String [] tab = this.txtIDEmploye.getSelectedItem().toString().split("-");
			int idEmploye = Integer.parseInt(tab[0]);
			int idRH = Integer.parseInt(this.txtIDRH.getText());
			String statut = this.txtStatut.getSelectedItem().toString();
			
			//instanciation de la classe Absence
			Absence uneAbsence = new Absence (dateDebut, dateFin, motif, typeAbsence, idEmploye, idRH, statut);
			
			//insertion dans la BDD
			Controleur.insertAbsence(uneAbsence);
			
			//actualiser l'affichage
			this.tableauAbsences.setDonnees(this.obtenirDonnees(""));
			this.nbAbsences.setText("Nombre d'absences : " + this.tableauAbsences.getRowCount());
			
			JOptionPane.showMessageDialog(this, "Insertion réussie de l'absence", "Insertion Absence", JOptionPane.OK_OPTION);
			
			//on vide les champs
			this.txtDateDebut.setText("");
			this.txtDateFin.setText("");
			this.txtMotif.setText("");
			this.txtType.setText("");

			this.txtIDRH.setText("");
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
			//on récupere les infos
			int numLigne = 0;
			numLigne = this.tableAbsences.getSelectedRow();
			int idAbsence = Integer.parseInt(this.tableauAbsences.getValueAt(numLigne, 0).toString());
			
			String dateDebut = this.txtDateDebut.getText();
			String dateFin = this.txtDateFin.getText();
			String motif = this.txtMotif.getText();
			String typeAbsence = this.txtType.getText();
			String [] tab = this.txtIDEmploye.getSelectedItem().toString().split("-");
			int idEmploye = Integer.parseInt(tab[0]);
			int idRH = Integer.parseInt(this.txtIDRH.getText());
			String statut = this.txtStatut.getSelectedItem().toString();
			
			//instancier la classe Promotion
			Absence uneAbsence = new Absence(idAbsence, dateDebut, dateFin, motif, typeAbsence, idEmploye, idRH, statut);
			
			//on modifie dans la BDD
			Controleur.updateAbsence(uneAbsence);
			
			//actualiser l'affichage des données
			this.tableauAbsences.setDonnees(this.obtenirDonnees(""));
			
			//on confirme la modification
			JOptionPane.showMessageDialog(this, "Modification réussie de l'Absence.");
			
			//on vide les champs
			this.txtDateDebut.setText("");
			this.txtDateFin.setText("");
			this.txtMotif.setText("");
			this.txtType.setText("");

			this.txtIDRH.setText("");
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
		}
		else if (e.getSource() == this.btFiltrer) {
			
			String filtre = this.txtFiltre.getText();
			
			this.tableauAbsences.setDonnees(this.obtenirDonnees(filtre));
		}
		
	}
}
