/**
 * Classe Vidéothèque, représente un ensemble de ressources
 * @author Kilian Cuny
 * @author Guillaume Haben
 * 
 * @version 1.0
 */

import java.io.*;
import java.util.*;

@SuppressWarnings("serial")
public class Videotheque implements Serializable{

	// Table des films
	HashMap<Integer, ArrayList<Ressource>> tab_film;
	
	// Table des séries
	HashMap<Integer, ArrayList<Ressource>> tab_serie;
	
	/**
	 * Constructeur initialisant les deux Tables
	 */
	public Videotheque(){
		this.reintialiser();
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
	 * Créer une nouvelle Vidéothèque
	 * @param fichier : nom du fichier
	 */
	public void creer(String fichier){
		Analyse.charger(this, fichier);
		
		// Sauvegarde automatique de la Vidéothèque
		this.sauvegarder("myBibli.bi");
	}
	
	/**
	 * Réinitialise la Vidéothèque
	 */
	public void reintialiser(){
		tab_film = new HashMap<Integer, ArrayList<Ressource>>(27);
		tab_serie = new HashMap<Integer, ArrayList<Ressource>>(27);
		
		for(int i=0; i<27; i++){
			tab_film.put(i, new ArrayList<Ressource>());
			tab_serie.put(i, new ArrayList<Ressource>());
		}
	}
	
	/**
	 * Ajoute une Ressource
	 * @param cle : clé de la Hashmap
	 * @param r : Ressource
	 * @param type : type de Ressource
	 */
	public void ajouter(int cle, Ressource r, String type) {
		if(type.compareTo("Film")==0){
			tab_film.get(cle).add(r);
		}else tab_serie.get(cle).add(r);	
	}
	
	/**
	 * Sauvegarde la Vidéothèque courante dans un fichier binaire
	 * @param fichier : Nom du fichier
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void sauvegarder(String fichier) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier));
			oos.writeObject(this);
			oos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Charge la Vidéothèque courante à partir d'un fichier de sauvegarde
	 * @param fichier : Nom du fichier
	 */
	public void charger(String fichier) {
		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier));
			Videotheque v;
			v = (Videotheque) ois.readObject();
			ois.close();
			
			this.tab_film = v.tab_film;
			this.tab_serie = v.tab_serie;

		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
