package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.Controleur;
import controleur.RH;
import controleur.SNCF;

public class VueConnexion extends JFrame implements ActionListener {

	private JPanel panelForm = new JPanel();
	private JTextField txtEmail = new JTextField();
	private JPasswordField txtMdp = new JPasswordField();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btValider = new JButton("Valider");
	
	public VueConnexion() {
		this.setTitle("SCNF -- Client Lourd -- 2025");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.setBounds(100, 100, 960, 500);
		this.getContentPane().setBackground(Color.gray);
		
		//installation du Panel
		this.panelForm.setBackground(Color.gray);
		this.panelForm.setBounds(600, 150, 320, 260);
		this.panelForm.setLayout(new GridLayout(3, 2));
		this.panelForm.add(new JLabel("Email :"));
		this.panelForm.add(this.txtEmail);
		this.panelForm.add(new JLabel("MDP :"));
		this.panelForm.add(this.txtMdp);
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btValider);
		this.add(this.panelForm);
		
		//installation du logo
		ImageIcon uneImage = new ImageIcon("src/images/Logo.png");
		JLabel unLogo = new JLabel(uneImage);
		unLogo.setBounds(10, 10, 500, 400);
		this.add(unLogo);
		
		//rendre les boutons ecoutables
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.txtEmail.setText("");
			this.txtMdp.setText("");
		}
		else if (e.getSource() == this.btValider) {
			String email = this.txtEmail.getText();
			String mdp = new String(this.txtMdp.getPassword());
			
			RH unRH = Controleur.selectWhereRH(email, mdp);
			if (unRH == null) {
				JOptionPane.showMessageDialog(this, "Veuillez vérifier vos identifiants !");
			} else {
				JOptionPane.showMessageDialog(this, 
						"Bienvenue M." + unRH.getNom() + " " + unRH.getPrenom() + "\n"
						+ "\n Responsabilité : " + unRH.getResponsabilite()
						+ "\n\n Poste : " + unRH.getPoste());
				
				//save user connecte
				SNCF.setUserConnecte(unRH);
				
				//ouverture de l'application
				SNCF.creerVueGenerale(true);
				SNCF.rendreVisible(false);
			}
		}
		
	}
}
