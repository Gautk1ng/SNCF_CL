package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class PanelPrincipal extends JPanel {
	
	private Image imageFond;
	
	public PanelPrincipal(String titre) {
//		this.setBackground(Color.cyan);
		this.setBounds(0, 60, 1000, 500);
		this.setLayout(null);
		
		Font unePolice = new Font("Arial", Font.BOLD, 20);
		
		JLabel lbTitre = new JLabel(titre);
		lbTitre.setBounds(400, 30, 400, 30);
		lbTitre.setFont(unePolice);
		
		this.add(lbTitre);
		
		this.imageFond = new ImageIcon("src/images/FondEcran.jpg").getImage();
		this.setVisible(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imageFond, 0, 0, getWidth(), getHeight(), this);
	}
}