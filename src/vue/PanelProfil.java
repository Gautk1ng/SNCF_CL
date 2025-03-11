package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.Controleur;
import controleur.SNCF;
import controleur.RH;

public class PanelProfil extends PanelPrincipal implements ActionListener{

	private JTextArea txtInfos = new JTextArea();
	private static RH unRH;
	
	private JButton btModifier = new JButton("Modifier");
	private JPanel panelForm = new JPanel();
	private JTextField txtNom = new JTextField();
	private JTextField txtPrenom = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JTextField txtStatut = new JTextField();
	private JTextField txtDateEmbauche = new JTextField();
	private JTextField txtPoste = new JTextField();
	private JTextField txtResponsabilite = new JTextField();
	private JPasswordField txtMdp1 = new JPasswordField();
	private JPasswordField txtMdp2 = new JPasswordField();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btValider = new JButton("Valider");
	
	public PanelProfil() {
		super ("Gestion du profil");
		
		unRH = SNCF.getUserConnecte();
		
		this.txtInfos.setBackground(Color.cyan);
		this.txtInfos.setBounds(50, 100, 350, 330);
		this.txtInfos.setText(
				"\n ________________ INFOS RH ________________\n\n"
				+" Nom de RH : " + unRH.getNom() + "\n\n"
				+" Prénom de RH : " + unRH.getPrenom() + "\n\n"
				+" Email de RH : " + unRH.getEmail() + "\n\n"
				+" Statut de RH : " + unRH.getStatut() + "\n\n"
				+" Date de l'embauche : " + unRH.getDateEmbauche() + "\n\n"
				+" Poste occupé : " + unRH.getPoste() + "\n\n"
				+" Responsabilité RH : " + unRH.getResponsabilite() + "\n\n"
				+"_____________________________________________\n\n"
				);
		this.txtInfos.setEditable(false);
		this.add(this.txtInfos);
		
		this.btModifier.setBounds(150, 420, 120, 40);
		this.add(this.btModifier);
		
		this.panelForm.setBackground(Color.cyan);
		this.panelForm.setBounds(450, 100, 400, 350);
		this.panelForm.setLayout(new GridLayout(10, 2));
		this.panelForm.add(new JLabel("Nom RH :"));
		this.panelForm.add(this.txtNom);
		this.panelForm.add(new JLabel("Prénom RH :"));
		this.panelForm.add(this.txtPrenom);
		this.panelForm.add(new JLabel("Email RH :"));
		this.panelForm.add(this.txtEmail);
		this.panelForm.add(new JLabel("Statut RH :"));
		this.panelForm.add(this.txtStatut);
		this.panelForm.add(new JLabel("Date Embauche :"));
		this.panelForm.add(this.txtDateEmbauche);
		this.panelForm.add(new JLabel("Poste occupé :"));
		this.panelForm.add(this.txtPoste);
		this.panelForm.add(new JLabel("Responsabilité :"));
		this.panelForm.add(this.txtResponsabilite);
		this.panelForm.add(new JLabel("Mot de Passe :"));
		this.panelForm.add(this.txtMdp1);
		this.panelForm.add(new JLabel("Confirmation :"));
		this.panelForm.add(this.txtMdp2);
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btValider);
		this.add(this.panelForm);
		
		this.panelForm.setVisible(false);
		
		this.btModifier.addActionListener(this);
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btModifier) {
			this.txtNom.setText(unRH.getNom());
			this.txtPrenom.setText(unRH.getPrenom());
			this.txtEmail.setText(unRH.getEmail());
			this.txtStatut.setText(unRH.getStatut());
			this.txtDateEmbauche.setText(unRH.getDateEmbauche());
			this.txtPoste.setText(unRH.getPoste());
			this.txtResponsabilite.setText(unRH.getResponsabilite());
			
			this.panelForm.setVisible(true);
		}
		else if(e.getSource() == this.btAnnuler) {
			this.txtMdp1.setText("");
			this.txtMdp2.setText("");
			this.panelForm.setVisible(false);
		}
		else if(e.getSource() == this.btValider) {
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String email = this.txtEmail.getText();
			String statut = this.txtStatut.getText();
			
			String dateEmbauche = this.txtDateEmbauche.getText();
			String poste = this.txtPoste.getText();
			String responsabilite = this.txtResponsabilite.getText();
			
			String mdp1 = new String(this.txtMdp1.getPassword());
			String mdp2 = new String(this.txtMdp2.getPassword());
			
			if(nom.equals("") || prenom.equals("") || email.equals("") || statut.equals("") 
					|| dateEmbauche.equals("") || poste.equals("") || responsabilite.equals("") 
					|| mdp1.equals("") || mdp2.equals("")) 
			{
				JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
			} else {
				if(mdp1.equals(mdp2)) {
					//instancier un USER
					unRH.setNom(nom);
					unRH.setPrenom(prenom);
					unRH.setEmail(email);
					unRH.setStatut(statut);
					unRH.setDateEmbauche(dateEmbauche);
					unRH.setPoste(poste);
					unRH.setResponsabilite(responsabilite);
					unRH.setMdp(mdp1);
					
					//Update dans la BDD
					Controleur.updateRH(unRH);
					this.txtMdp1.setText("");
					this.txtMdp2.setText("");
					this.panelForm.setVisible(false);
					this.txtInfos.setText(
							"\n ________________ INFOS RH ________________\n\n"
							+" Nom du RH : " + unRH.getNom() + "\n\n"
							+" Prénom du RH : " + unRH.getPrenom() + "\n\n"
							+" Email du RH : " + unRH.getEmail() + "\n\n"
							+" Statut du RH : " + unRH.getStatut() + "\n\n"
							+" Date de l'embauche : " + unRH.getDateEmbauche() + "\n\n"
							+" Poste occupé : " + unRH.getPoste() + "\n\n"
							+" Responsabilité RH: " + unRH.getResponsabilite() + "\n\n"
							+"_____________________________________________\n\n"
							);
					JOptionPane.showMessageDialog(this, "Modification effectuée.");
					
				} else {
					JOptionPane.showMessageDialog(this, "Veuillez vérifier vos MDP.");
				}
			}
		}
	}
}
