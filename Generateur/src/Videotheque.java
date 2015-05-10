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
	public HashMap<Integer, ArrayList<Ressource>> tab_film;
	
	// Table des séries
	public HashMap<Integer, ArrayList<Ressource>> tab_serie;
	
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
	public int creer(String fichier){
		return Analyse.charger(this, fichier);
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
	 * Ajoute une Ressource et trie alphabétiquement la liste
	 * @param cle : clé de la Hashmap
	 * @param r : Ressource
	 * @param type : type de la Ressource
	 */
	public void ajouter(int cle, Ressource r, String type) {
		if(type.compareTo("Film")==0){
			tab_film.get(cle).add(r);
			Collections.sort(tab_film.get(cle));
		}else {
			tab_serie.get(cle).add(r);	
			Collections.sort(tab_serie.get(cle));
		}
	}

	/**
	 * Recherche une ressource dans la Vidéothèque
	 * @param titre : titre recherché
	 */
	public String recherche(String titre){
		int cle = Analyse.Hashage(titre.charAt(0));
		ArrayList<Ressource> liste_film = tab_film.get(cle);
		ArrayList<Ressource> liste_serie = tab_serie.get(cle);
		
		for(Ressource f : liste_film){
			if (f.getTitre().toUpperCase().compareTo(titre.toUpperCase())==0){
				return f.toString();
			}
		}
		
		for(Ressource s : liste_serie){
			if (s.getTitre().toUpperCase().compareTo(titre.toUpperCase())==0){
				return s.toString();
			}
		}
		return "Film inconnu";
	}
	
	/**
	 * Sauvegarde la Vidéothèque courante dans un fichier binaire
	 * @param fichier : Nom du fichier
	 */
	public void sauvegarder(String fichier){
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
	 * @return code de retour
	 */
	public int charger(String fichier) {
		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier));
			Videotheque v;
			v = (Videotheque) ois.readObject();
			ois.close();
			
			this.tab_film = v.tab_film;
			this.tab_serie = v.tab_serie;
			
			return 0;

		} catch (IOException e) {
			System.out.println("Chargement corrompu : ");
			e.printStackTrace();
			return -1;
		} catch (ClassNotFoundException e) {
			System.out.println("Chargement corrompu : ");
			e.printStackTrace();
			return -1;
		}
	}
}
