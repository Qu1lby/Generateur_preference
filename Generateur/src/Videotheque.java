/**
 * Classe Vid�oth�que, repr�sente un ensemble de ressources
 * @author Kilian Cuny
 * @author Guillaume Haben
 * 
 * @version 1.0
 */

import java.io.*;
import java.util.*;

public class Videotheque {

	// Table des films
	HashMap<Integer, ArrayList<Ressource>> tab_film;
	
	// Table des s�ries
	HashMap<Integer, ArrayList<Ressource>> tab_serie;
	
	/**
	 * Constructeur initialisant les deux Tables
	 */
	public Videotheque(){
		tab_film = new HashMap<Integer, ArrayList<Ressource>>(27);
		tab_serie = new HashMap<Integer, ArrayList<Ressource>>(27);
	}
	
	/**
	 * Setter Tab_Film
	 * @param tab_film
	 */
	public void setTab_film(HashMap<Integer, ArrayList<Ressource>> tab_film) {
		this.tab_film = tab_film;
	}

	/**
	 * Setter Tab_Serie
	 * @param tab_serie
	 */
	public void setTab_serie(HashMap<Integer, ArrayList<Ressource>> tab_serie) {
		this.tab_serie = tab_serie;
	}

	/**
	 * Cr�er une nouvelle Vid�oth�que
	 * 
	 * @param fichier : nom du fichier
	 */
	public void creer(String fichier){
		Analyse.charger(this, fichier);
	}
	
	/**
	 * R�initialise la Biblioth�que
	 */
	public void reintialiser(){
		tab_film = new HashMap<Integer, ArrayList<Ressource>>(27);
		tab_serie = new HashMap<Integer, ArrayList<Ressource>>(27);
	}
	
	/**
	 * Sauvegarde la Vid�oth�que courante dans un fichier binaire
	 * 
	 * @param fichier : Nom du fichier
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void sauvegarder(String fichier) throws FileNotFoundException, IOException {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier));
			oos.writeObject(this);
			oos.close();
			
		} catch (IOException e) {
			System.out.println("Erreur lors de la sauvegarde !");
		}
	}

	/**
	 * Charge la Vid�oth�que courante � partir d'un fichier de sauvegarde
	 * 
	 * @param fichier : Nom du fichier
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void charger(String fichier) throws FileNotFoundException, IOException,
			ClassNotFoundException {
		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier));
			Videotheque v = (Videotheque) ois.readObject();
			ois.close();
			this.tab_film = v.tab_film;
			this.tab_serie = v.tab_serie;

		} catch (IOException e) {
			System.out.println("Erreur lors du chargement !");
		}
	}
}
