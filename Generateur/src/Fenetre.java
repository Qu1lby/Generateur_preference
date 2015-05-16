/**
 * Classe Fenêtre, représente la JFrame de l'application
 * @author Kilian Cuny
 * @author Guillaume Haben
 * @version 2.0
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class Fenetre extends JFrame implements ActionListener{
	
	public Videotheque ma_videotheque;
	public String menu;
	public String ig_recherche;

	/**
	 * Constructeur -> Handle fermeture et configuration par défaut
	 */
	public Fenetre() {
		 ma_videotheque = new Videotheque();
		 menu = "Chargement";
		 ig_recherche = "";
		 
		// Fermeture de la Fenêtre avec la croix
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(menu.compareTo("Chargement")!= 0){
					ImageIcon img = new ImageIcon("./img/save.png");
					String msg = "Souhaitez vous enregistrer \nvotre Videothèque.. ?\n";
					String Texte[] = { "Oui..", "Non.." };
					int retour = 0;
					retour = JOptionPane.showOptionDialog(null, msg,"", JOptionPane.YES_NO_OPTION, retour,
							img, Texte, Texte[0]);
					
					if (retour == 1) System.exit(0);
					if (retour == 0){
						try {
							final JFileChooser fc = new JFileChooser("Sauvegarder");
							fc.setCurrentDirectory(new File("."));
							int status = fc.showSaveDialog(null);
							String path = null;
							if (status == JFileChooser.APPROVE_OPTION){
								path = fc.getSelectedFile().getAbsolutePath();
								if(!path.contains(".bi"))
									path += ".bi";
							}
							if(ma_videotheque.sauvegarder(path)== 0) System.exit(0);
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Sauvegarde impossible..\n", "",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		
		// Paramètres de la Fenêtre
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		Image icon = Toolkit.getDefaultToolkit().getImage("./img/icon.png");
		if(icon!= null) this.setIconImage(icon);
		this.setTitle(" - Générateur de Recommandation - ");
		this.setMinimumSize(new Dimension((int) (dimension.getWidth() / 1.5), (int) (dimension.getHeight() / 1.5)));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.display();
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Selectionne l'affichage adéquat
	 */
	public void display() {
		if(menu.compareTo("Chargement")== 0){
			this.Menu_chargement();
		}
		if(menu.compareTo("Principal")== 0){
			this.Menu_principal();
		}
		
		this.pack();
	}

	/**
	 * Menu Chargement
	 */
	public void Menu_chargement(){
		final JPanel Centre = new JPanel();
		Centre.setLayout(new BorderLayout());
		
		// Boutons 
		JButton j1_creer = new JButton("Créer");
		JButton j1_charger = new JButton("Charger");
		JLabel lab1_1 = new JLabel("<html><br><br><br>Comment voulez-vous initaliser "
				+ "votre Vidéothèque ?<br><br><br></html>");
		
		// Polices
		Font font = new Font("Arial", Font.CENTER_BASELINE, 20);
		Font font2 = new Font("Arial", Font.CENTER_BASELINE, 30);
		j1_creer.setFont(font);
		j1_charger.setFont(font);
		lab1_1.setFont(font2);
		
		j1_creer.setBackground(Color.white);
		j1_charger.setBackground(Color.white);
		j1_creer.setFocusPainted(false);
		j1_charger.setFocusPainted(false);
		
		FlowLayout fl = new FlowLayout();
		fl.setHgap(100);
	
		// Panneau haut du Centre
		JPanel haut = new JPanel(new FlowLayout(FlowLayout.CENTER));
		haut.add(lab1_1);
		
		// Panneau bas du Centre
		JPanel bas = new JPanel(fl);
		bas.add(j1_creer);
		bas.add(j1_charger);
		
		Centre.add(haut, BorderLayout.NORTH);
		Centre.add(bas, BorderLayout.CENTER);
		
		this.setContentPane(Centre);
		
		// Listenners CREER / CHARGER
		j1_charger.addActionListener(this);
		j1_charger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					final JFileChooser fc = new JFileChooser("Charger un fichier *.bi");
					String path = null;
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
							"Videotheque bi", "bi");
					fc.setFileFilter(filter);
					fc.setCurrentDirectory(new File("."));
					int status = fc.showOpenDialog(null);
					if (status == JFileChooser.APPROVE_OPTION) 
						path = fc.getSelectedFile().getAbsolutePath();
					if (path != null){
						if(ma_videotheque.charger(path)!=0){
							JOptionPane.showMessageDialog(null, "Chargement impossible..\n", "",
									JOptionPane.ERROR_MESSAGE);
						}else menu = "Principal";
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Chargement impossible..\n", "",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		j1_creer.addActionListener(this);
		j1_creer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ma_videotheque.creer("films.txt") == -1){
					JOptionPane.showMessageDialog(null, "Le fichier d'intialisation est manquant", "",
							JOptionPane.ERROR_MESSAGE);
				}else{
					menu = "Principal";
				}
			}
		});
	}

	/**
	 * Menu Principal
	 */
	public void Menu_principal(){
		final JPanel Centre = new JPanel();
		Centre.setLayout(new BorderLayout());
		
		Font font = new Font("Arial", Font.CENTER_BASELINE, 20);
		Font font2 = new Font("Arial", Font.CENTER_BASELINE,15);
		
		JTextField recherche = new JTextField(30);
		recherche.setFont(font2);
		recherche.setText(ig_recherche);
		
		JLabel lab_recherche = new JLabel("Recherche : ");
		lab_recherche.setFont(font2);
		
		JButton valider = new JButton("Lancer");
		valider.setFocusPainted(false);
		
		// Panneau haut du Centre
		JPanel haut = new JPanel(new BorderLayout());
		JPanel haut_centre = new JPanel(new FlowLayout(FlowLayout.CENTER));
		haut_centre.add(lab_recherche);
		haut_centre.add(recherche);
		haut_centre.add(valider);
		
		haut.add(new JLabel(" "), BorderLayout.NORTH);
		haut.add(haut_centre,BorderLayout.CENTER);
		
		// Panneau bas du Centre
		JPanel bas = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		Centre.add(haut, BorderLayout.NORTH);
		Centre.add(bas, BorderLayout.CENTER);
		
		
		this.setContentPane(Centre);
	}
	/**
	 * Relance l'affichage après une action sur un bouton
	 */
	public void actionPerformed(ActionEvent e) {
		this.display();
	}
	
	public static void main(String[] args) {
		Fenetre f = new Fenetre();
		f.display();
	}
}