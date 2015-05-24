/**
 * Classe Fenêtre, représente la JFrame de l'application
 * @author Kilian Cuny
 * @author Guillaume Haben
 * @version 2.0
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;

@SuppressWarnings("serial")
public class Fenetre extends JFrame implements ActionListener{
	
	public Videotheque ma_videotheque;
	public String menu;
	public String text_recherche;
	public Ressource r_recherche;
	public boolean r_modifier;
	
	/**
	 * Constructeur -> Handle fermeture et configuration par défaut
	 */
	public Fenetre() {
		 ma_videotheque = new Videotheque();
		 menu = "Chargement";
		 text_recherche = "";
		 r_modifier = false;
		 
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
							JOptionPane.showMessageDialog(null, "La sauvegarde a retourné une erreur\n", "",
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
		this.setLocationRelativeTo(null);
	}
	
	
	/**
	 * Selectionne l'affichage adéquat
	 */
	public void display() {
		if(menu.compareTo("Chargement") == 0)
			this.Menu_chargement();
		
		if(menu.compareTo("Principal") == 0)
			this.Menu_principal();
		
		if(menu.compareTo("Recherche") == 0)
			this.Menu_recherche();
		
		if(menu.compareTo("Ajouter") == 0)
			this.Menu_ajouter();
		
		if(menu.compareTo("Recommandation") == 0)
			this.Menu_recommandation();
		
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
		
		Font font = new Font("Arial", Font.CENTER_BASELINE,15);
		
		final JTextField recherche = new JTextField(30);
		recherche.setFont(font);
		recherche.setText(text_recherche);
		
		JLabel lab_recherche = new JLabel("Recherche : ");
		lab_recherche.setFont(font);
		
		JButton valider = new JButton("Lancer");
		valider.setFocusPainted(false);
		valider.setContentAreaFilled(false);
		
		// Panneau haut du Centre
		JPanel haut = new JPanel(new BorderLayout());
		JPanel haut_centre = new JPanel(new FlowLayout(FlowLayout.CENTER));
		haut_centre.add(lab_recherche);
		haut_centre.add(recherche);
		haut_centre.add(valider);
		haut.add(new JLabel(" "), BorderLayout.NORTH);
		haut.add(haut_centre,BorderLayout.CENTER);
		
		// Panneaux middle du Centre
		JPanel middle = new JPanel(new BorderLayout());
		JPanel middle_haut = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel middle_center = new JPanel(new BorderLayout());
		JPanel middle_center_top = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel middle_center_center = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel middle_bas = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		
		JButton recommandation = new JButton("Recommandation");
		recommandation.setFocusPainted(false);
		recommandation.setBackground(Color.white);
		
		JButton reinitialiser = new JButton("Réinitialiser mes Préférences");
		reinitialiser.setFocusPainted(false);
		reinitialiser.setContentAreaFilled(false);
		
		JButton ajouter = new JButton("Ajouter une Ressource");
		ajouter.setFocusPainted(false);
		ajouter.setContentAreaFilled(false);
		
		DefaultListModel<String> listModel_film = new DefaultListModel<String>();
		DefaultListModel<String> listModel_serie = new DefaultListModel<String>();
		
		for (Entry<Integer, ArrayList<Ressource>> entry : ma_videotheque.tab_film.entrySet()) {
			//Si la valeur de l'entrée n'est pas nulle (si des films existent pour la lettre actuelle)
			if (!entry.getValue().isEmpty()) {
				for (Ressource R : ma_videotheque.tab_film.get(entry.getKey())) {
					if(R.isVu()){
						listModel_film.addElement("- "+R.getTitre()+" -");
					}else listModel_film.addElement(R.getTitre());
				}
			}
		}
		
		for (Entry<Integer, ArrayList<Ressource>> entry : ma_videotheque.tab_serie.entrySet()) {
			//Si la valeur de l'entrée n'est pas nulle (si des films existent pour la lettre actuelle)
			if (!entry.getValue().isEmpty()) {
				for (Ressource R : ma_videotheque.tab_serie.get(entry.getKey())) {
					if(R.isVu()){
						listModel_serie.addElement("- "+R.getTitre()+" -");
					}else listModel_serie.addElement(R.getTitre());
				}
			}
		}
		
		JList<String> list_film = new JList<String>(listModel_film);
		JList<String> list_serie = new JList<String>(listModel_serie);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(list_film);
		list_film.setVisibleRowCount(12);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setViewportView(list_serie);
		list_serie.setVisibleRowCount(12);
		
		list_film.setBackground(Color.white);
		list_serie.setBackground(Color.white);
		
		middle.add(middle_bas,BorderLayout.SOUTH);
		middle.add(middle_haut, BorderLayout.NORTH);
		middle.add(middle_center, BorderLayout.CENTER);
		middle_center.add(middle_center_top, BorderLayout.NORTH);
		middle_center.add(middle_center_center, BorderLayout.CENTER);
		
		
		JPanel liste_film_panel = new JPanel();
        liste_film_panel.setLayout(new BoxLayout(liste_film_panel, BoxLayout.Y_AXIS));
        liste_film_panel.add(new JLabel(" "));
		liste_film_panel.add(new JLabel("Films :"));
        liste_film_panel.add(scrollPane);
        
		
		JPanel liste_serie_panel = new JPanel();
		liste_serie_panel.setLayout(new BoxLayout(liste_serie_panel, BoxLayout.Y_AXIS));
		liste_serie_panel.add(new JLabel(" "));
		liste_serie_panel.add(new JLabel("Séries :"));
		liste_serie_panel.add(scrollPane2);
		
		middle_center_top.add(recommandation);
		middle_center_center.add(new JLabel(" "));
		middle_center_center.add(liste_film_panel);
		middle_center_center.add(liste_serie_panel);
		
		middle_center.add(new JLabel(" "), BorderLayout.SOUTH);
		middle_haut.add(new JLabel(" "), BorderLayout.NORTH);
		middle_bas.add(ajouter);
		middle_bas.add(reinitialiser);
		
		Centre.add(haut, BorderLayout.NORTH);
		Centre.add(middle, BorderLayout.CENTER);
		
		// Listenners RECHERCHE / REINTIALISER / AJOUTER
		valider.addActionListener(this);
		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(recherche.getText()!=null && recherche.getText().compareTo("")!=0){
					r_recherche = ma_videotheque.recherche(recherche.getText());

					if(r_recherche!=null){
						menu = "Recherche";
					}else{
						JOptionPane.showMessageDialog(null, "Aucune ressource de ce nom\n          n'est disponible", "",
								JOptionPane.INFORMATION_MESSAGE);
						text_recherche = recherche.getText(); 
					}
				}else text_recherche = "";
			}
		});
		
		ajouter.addActionListener(this);
		ajouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu = "Ajouter";
			}
		});
		
		recommandation.addActionListener(this);
		recommandation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu = "Recommandation";
			}
		});
		
		reinitialiser.addActionListener(this);
		reinitialiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = "Réinitialiser mes préfèrences ?\n";
				String Texte[] = { "Confirmer", "Retour" };
				int retour = 0;
				retour = JOptionPane.showOptionDialog(null, msg,"", JOptionPane.YES_NO_OPTION, retour,
						null, Texte, Texte[1]);
				if(retour == 0){
					ma_videotheque.reintialiser_pref();
				}
			}
		});
		
		MouseAdapter ma = new MouseAdapter() {
		    @SuppressWarnings("rawtypes")
			public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		        	int index = list.locationToIndex(evt.getPoint());
		            ListModel dlm = list.getModel();
		            String item = (String) dlm.getElementAt(index);;
		            list.ensureIndexIsVisible(index);
		            if(item.contains("-")){
		            	item = item.substring(2, item.length()-2);
		            }
		        	recherche.setText(item);
		        }
		    }
		};
		
		list_film.addMouseListener(ma);
		list_serie.addMouseListener(ma);
		
		this.setContentPane(Centre);
	}

	
	/**
	 * Menu Recherche
	 */
	public void Menu_recherche(){
		final JPanel Centre = new JPanel();
		Centre.setLayout(new BorderLayout());
		
		Font font = new Font("Arial", Font.CENTER_BASELINE,20);
		Font font2 = new Font("Arial", Font.CENTER_BASELINE,15);
		
		final JTextField recherche = new JTextField(30);
		recherche.setFont(font2);
		recherche.setText(text_recherche);
		
		JLabel lab_recherche = new JLabel("Recherche : ");
		lab_recherche.setFont(font2);
		
		JButton valider = new JButton("Lancer");
		valider.setFocusPainted(false);
		valider.setContentAreaFilled(false);
		
		// Panneau haut du Centre
		JPanel haut = new JPanel(new BorderLayout());
		JPanel haut_centre = new JPanel(new FlowLayout(FlowLayout.CENTER));
		haut_centre.add(lab_recherche);
		haut_centre.add(recherche);
		haut_centre.add(valider);
		
		haut.add(new JLabel(" "), BorderLayout.NORTH);
		haut.add(haut_centre,BorderLayout.CENTER);
		
		
		// Panneau middle du Centre
		JPanel midd = new JPanel(new FlowLayout());
		JPanel middle = new JPanel(new BorderLayout());
		JPanel middle_haut_bas = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel middle_c = new JPanel(new GridLayout(10,1));
		JPanel middle_b = new JPanel(new FlowLayout(FlowLayout.CENTER));
		middle_haut_bas.add(middle_c);
		middle.add(middle_haut_bas, BorderLayout.NORTH);
		middle.add(middle_b, BorderLayout.CENTER);
		
		JLabel titre = new JLabel(r_recherche.getTitre());
		titre.setFont(font);
		titre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
		JLabel annee = new JLabel("("+r_recherche.getAnnee()+")");
		annee.setFont(font2);
		annee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
		JTextArea synopsis = new JTextArea(r_recherche.getSynopsis());
		synopsis.setFont(font2);
		synopsis.setLineWrap(true);
		synopsis.setWrapStyleWord(true);
		synopsis.setEditable(false);
		synopsis.setOpaque(false);
		synopsis.setSize(500, 100);

		JLabel director = new JLabel("Réalisateur : "+r_recherche.getRealisateur());
		director.setFont(font2);
		director.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
		JLabel casting = new JLabel(""+r_recherche.getActeur());
		casting.setFont(font2);
		casting.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
		JLabel genre = new JLabel(""+r_recherche.getGenre());
		genre.setFont(font2);
		genre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
		JLabel duree = new JLabel("Durée : "+r_recherche.getDuree()+" min");
		duree.setFont(font2);
		duree.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
		middle_c.add(new JLabel());
		middle_c.add(titre);
		middle_c.add(annee);
		middle_c.add(new JLabel());
		middle_c.add(director);
		middle_c.add(casting);
		middle_c.add(genre);
		middle_c.add(duree);	
		
		JPanel bord = new JPanel();
		bord.setLayout(new BoxLayout(bord, BoxLayout.Y_AXIS));
		
		ArrayList<Ressource> arr = new ArrayList<Ressource>();
		arr.add(r_recherche);
		Similarite.init(ma_videotheque, arr);
		bord.add(new JLabel("Ressources similaires :"));
		bord.add(new JLabel(" "));
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for(Association a : r_recherche.getSimilaire()){
			listModel.addElement(a.getRessemblance().getTitre());
		}
		
		JList<String> list_film = new JList<String>(listModel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(list_film);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(150,130));
		scrollPane.getViewport().setOpaque(false);
		
		

		list_film.setVisibleRowCount(5);
		
		bord.add(scrollPane);
		midd.add(middle);
		midd.add(new JLabel("  "));
		midd.add(bord);
		
		
		if(r_recherche.isVu()){
			JLabel note = new JLabel("Note : "+r_recherche.getNote());
			note.setFont(font2);
			note.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			middle_c.add(note);
		}
		
		middle_c.add(new JLabel(""));
		middle_b.add(synopsis);
		
		// Panneau bas du Centre
		JPanel bas = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		JButton retour = new JButton("Menu Principal");
		retour.setFocusPainted(false);
		retour.setContentAreaFilled(false);
		
		JButton supprimer = new JButton("Supprimer");
		supprimer.setFocusPainted(false);
		supprimer.setContentAreaFilled(false);
		
		JButton note= new JButton("Vu / Noter");
		note.setFocusPainted(false);
		note.setContentAreaFilled(false);
		
		JButton modifier= new JButton("Modifier");
		modifier.setFocusPainted(false);
		modifier.setContentAreaFilled(false);
		
		if(!r_recherche.isVu())
			bas.add(note);
		
		bas.add(modifier);
		bas.add(supprimer);
		bas.add(retour);
		
		Centre.add(haut, BorderLayout.NORTH);
		Centre.add(midd, BorderLayout.CENTER);
		Centre.add(bas, BorderLayout.SOUTH);
		
		// Listenners NOTER / MODIFIER / SUPPRIMER / RETOUR
		valider.addActionListener(this);
		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(recherche.getText()!=null && recherche.getText().compareTo("")!=0){
					Ressource tmp = r_recherche;
					r_recherche = null;
					r_recherche = ma_videotheque.recherche(recherche.getText());

					if(r_recherche!=null){
						menu = "Recherche";
					}else{
						JOptionPane.showMessageDialog(null, "Aucune ressource de ce nom\n          n'est disponible", "",
								JOptionPane.INFORMATION_MESSAGE);
						text_recherche = r_recherche.getTitre(); 
						r_recherche = tmp;
					}
				}else text_recherche = "";
			}
		});
		
		MouseAdapter ma = new MouseAdapter() {
		    @SuppressWarnings("rawtypes")
			public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		        	int index = list.locationToIndex(evt.getPoint());
		            ListModel dlm = list.getModel();
		            String item = (String) dlm.getElementAt(index);;
		            list.ensureIndexIsVisible(index);
		        	recherche.setText(item);
		        }
		    }
		};
		
		list_film.addMouseListener(ma);
		
		retour.addActionListener(this);
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu = "Principal";
				r_recherche = null;
			}
		});
		
		supprimer.addActionListener(this);
		supprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ma_videotheque.supprimer(r_recherche);
				menu = "Principal";
			}
		});
		
		modifier.addActionListener(this);
		modifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu = "Ajouter";
				r_modifier = true;
			}
		});
		
		note.addActionListener(this);
		note.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Noter ce film");
				String s_note = JOptionPane.showInputDialog(frame, "Quelle note souhaitez-vous\nattribuer ? (0 à 10) ");
				int note;
				try{
					note = Integer.parseInt(s_note);
					r_recherche.setNote(note);
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Note invalide, veuillez recommencer !", "",
						JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		this.setContentPane(Centre);
	}

	
	/**
	 * Menu ajouter
	 */
	public void Menu_ajouter(){
		final JPanel Centre = new JPanel();
		Centre.setLayout(new BorderLayout());
		
		Font font = new Font("Arial", Font.CENTER_BASELINE,20);
		Font font2 = new Font("Arial", Font.CENTER_BASELINE,15);
	
		// Panneau middle du Centre
		JPanel middle = new JPanel(new BorderLayout());
		JPanel middle_haut_bas = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel middle_c = new JPanel();
		middle_c.setLayout(new BoxLayout(middle_c, BoxLayout.Y_AXIS));
		JPanel middle_b = new JPanel(new FlowLayout(FlowLayout.CENTER));
		middle_haut_bas.add(middle_c);
		middle.add(middle_haut_bas, BorderLayout.NORTH);
		middle.add(middle_b, BorderLayout.CENTER);
		

		final JTextField titre = new JTextField(35);
		titre.setFont(font2);
		titre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter("####");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}	

		final JFormattedTextField  annee = new JFormattedTextField(mask);
		annee.setFont(font2);
		annee.setColumns(4);
		annee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		

		final JTextArea synopsis = new JTextArea();
		synopsis.setFont(font2);
		synopsis.setLineWrap(true);
		synopsis.setWrapStyleWord(true);
		synopsis.setPreferredSize(new Dimension(400, 100));
		Border myBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		synopsis.setBorder(myBorder);

		final JTextField director = new JTextField(31);
		director.setFont(font2);
		director.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
		final JTextField casting = new JTextField(34);
		casting.setFont(font2);
		casting.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
		final JTextField genre = new JTextField(35);
		genre.setFont(font2);
		genre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
		MaskFormatter mask2 = null;
		try {
			mask2 = new MaskFormatter("***");
			mask2.setValidCharacters("0123456789 ");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}	

		final JFormattedTextField duree = new JFormattedTextField(mask2);
		duree.setFont(font2);
		duree.setColumns(4);
		duree.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
		JPanel un = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel deux = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel trois = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel quatre = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel cinq = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel six = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel sept = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel huit = new JPanel(new FlowLayout(FlowLayout.CENTER));

		JLabel titre_aff = new JLabel("Ajouter une Ressource");
		titre_aff.setFont(font);
		
		JPanel haut_centre = new JPanel(new FlowLayout(FlowLayout.CENTER));
		haut_centre.add(titre_aff);
		JPanel haut = new JPanel(new BorderLayout());
		haut.add(new JLabel(" "), BorderLayout.NORTH);
		haut.add(haut_centre, BorderLayout.CENTER);

		middle_c.add(new JLabel(" "));
		
		un.add(new JLabel("TITRE :   "));
		un.add(titre);
		middle_c.add(un);
		
		trois.add(new JLabel("REALISATEUR :   "));
		trois.add(director);
		middle_c.add(trois);
		
		quatre.add(new JLabel("ACTEUR :   "));
		quatre.add(casting);
		middle_c.add(quatre);
		
		cinq.add(new JLabel("GENRE :   "));
		cinq.add(genre);
		middle_c.add(cinq);
		
		deux.add(new JLabel("ANNEE :   "));
		deux.add(annee);
		middle_c.add(deux);
		
		six.add(new JLabel("DUREE :   "));
		six.add(duree);
		middle_c.add(six);	
		
		sept.add(new JLabel("SYNOPSIS :   "));
		sept.add(synopsis);
		middle_c.add(sept);
		
		String[] liste = { "Film", "Serie"};
		@SuppressWarnings({ "rawtypes", "unchecked" })
		final
		JComboBox List = new JComboBox(liste);
		List.setSelectedIndex(0);
		huit.add(new JLabel("TYPE :   "));
		huit.add(List);
		middle_c.add(huit);
		
		if(r_modifier){
			titre.setText(r_recherche.getTitre());
			annee.setText(""+r_recherche.getAnnee());
			director.setText(r_recherche.getRealisateur());
			casting.setText(r_recherche.getActeur().toString().substring(1,
					r_recherche.getActeur().toString().length()-1));
			genre.setText(r_recherche.getGenre().toString().substring(1,
					r_recherche.getGenre().toString().length()-1));
			if(r_recherche.getDuree()==0)
				duree.setText("0");
			else duree.setText(""+r_recherche.getDuree());
			synopsis.setText(r_recherche.getSynopsis());
			if(r_recherche.getTitre().compareTo("Serie")==0){
				List.setSelectedIndex(1);
			}
		}

		// Panneau bas du Centre
		JPanel bas = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		JButton retour = new JButton("Menu Principal");
		retour.setFocusPainted(false);
		retour.setContentAreaFilled(false);
		
		JButton ajouter = new JButton("Ajouter/Modifier");
		ajouter.setFocusPainted(false);
		ajouter.setContentAreaFilled(false);
		
		bas.add(ajouter);
		bas.add(retour);
		
		Centre.add(haut, BorderLayout.NORTH);
		Centre.add(middle, BorderLayout.CENTER);
		Centre.add(bas, BorderLayout.SOUTH);

		
		// Listenners  AJOUTER / RETOUR
		retour.addActionListener(this);
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu = "Principal";
				r_recherche = null;
			}
		});
		
		ajouter.addActionListener(this);
		ajouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String r_title = titre.getText();
				if(r_title.compareTo("")!=0){
				
					String r_date = annee.getText();
					if(r_date.compareTo("    ")==0)
						r_date="2015";
					
					String r_director = director.getText();
					String r_casting = casting.getText();
					String r_genre = genre.getText();
					String r_duree = duree.getText().trim();
					if(r_duree.compareTo("   ")==0)
						r_duree="-1";
					
					String r_synopsis = synopsis.getText();
					String r_type = List.getSelectedItem().toString();
					
					String[] ss = r_casting.split(", ");
					ArrayList<String> l_casting = new ArrayList<String>();
					for(String s : ss){
						l_casting.add(s);
					}
					
					String[] sg = r_genre.split(", ");
					ArrayList<String> l_genre = new ArrayList<String>();
					for(String s : sg){
						l_genre.add(s);
					}
					
					Ressource new_r = new Ressource(r_title, Integer.parseInt(r_date), r_synopsis, l_casting, l_genre, Integer.parseInt(r_duree), r_type, r_director);
					
					if(!r_modifier){
						ma_videotheque.ajouter(Analyse.Hashage(new_r.getTitre().charAt(0)), new_r, r_type);
						menu = "Principal";
					}else{
						int note = r_recherche.getNote();
						boolean vu = r_recherche.isVu();
						new_r.setNote(note);
						new_r.setVu(vu);
						
						ma_videotheque.supprimer(r_recherche);
						ma_videotheque.ajouter(Analyse.Hashage(new_r.getTitre().charAt(0)), new_r, r_type);
						menu = "Recherche";
						r_recherche = new_r;
					}
				
				}else JOptionPane.showMessageDialog(null, "Ajouter un titre", "",
						JOptionPane.ERROR_MESSAGE);
				
			}
		});
		
		this.setContentPane(Centre);
		
	}
	
	
	
	/** 
	 * Menu recommandation
	 */
	public void Menu_recommandation(){
		
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