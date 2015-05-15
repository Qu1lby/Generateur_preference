/**
 * Classe Fenêtre, représente la JFrame de l'application
 * @author Kilian Cuny
 * @author Guillaume Haben
 * @version 2.0
 */

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class Fenetre extends JFrame {

	public Fenetre() {

		// Panneau principal
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		
		// Panneau Principal
		final JPanel Centre = new JPanel();
		Centre.setLayout(new BorderLayout());
		this.setContentPane(Centre);
		
		// Boutons
		JButton j1_creer = new JButton("Créer");
		JButton j1_charger = new JButton("Charger");
		
		JLabel lab1_1 = new JLabel(
				"");

		JLabel lab1_2 = new JLabel(
				"<html><br><br><br>Comment voulez-vous initaliser votre Vidéothèque ?<br><br><br></html>");
		
		// Polices
		Font font = new Font("Arial", Font.CENTER_BASELINE, 20);
		Font font2 = new Font("Arial", Font.CENTER_BASELINE, 30);

		j1_creer.setFont(font);
		j1_charger.setFont(font);
		lab1_1.setFont(font2);
		lab1_2.setFont(font2);

		j1_creer.setBackground(Color.white);
		j1_charger.setBackground(Color.white);
		
		j1_creer.setFocusPainted(false);
		j1_charger.setFocusPainted(false);
		
		FlowLayout fl = new FlowLayout();
		fl.setHgap(100);
	
		// Panneau haut du Centre
		JPanel haut = new JPanel(new FlowLayout(FlowLayout.CENTER));
		haut.add(lab1_1);
		haut.add(lab1_2);
		
		// Panneau bas du Centre
		JPanel bas = new JPanel(fl);

		bas.add(j1_creer);
		bas.add(j1_charger);
		
		Centre.add(haut, BorderLayout.NORTH);
		Centre.add(bas, BorderLayout.CENTER);
		
		// Paramètre de la Fenêtre
		Image icon = Toolkit.getDefaultToolkit().getImage("./img/icon.png");
		if(icon!= null) this.setIconImage(icon);
		this.setTitle(" - Générateur de Recommandation - ");
		this.setMinimumSize(new Dimension((int) (dimension.getWidth() / 1.5), (int) (dimension.getHeight() / 1.5)));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Fenetre f = new Fenetre();
	}

}