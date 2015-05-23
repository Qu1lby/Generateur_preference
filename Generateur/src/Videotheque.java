/**
 * Classe Vidéothèque, représente un ensemble de Ressource
 * @author Kilian Cuny
 * @author Guillaume Haben
 * @version 1.0
 */

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

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
	 * @return code_retour
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
	 * Supprime une Ressource
	 * @param r : Ressource
	 */
	public void supprimer(Ressource r) {
		int cle = Analyse.Hashage(r.getTitre().charAt(0));
		tab_film.get(cle).remove(r);
	}

	/**
	 * Recherche une ressource dans la Vidéothèque
	 * @param titre : titre recherché
	 * @return Ressource ou null
	 */
	public Ressource recherche(String titre){
		int cle = Analyse.Hashage(titre.charAt(0));
		ArrayList<Ressource> liste_film = tab_film.get(cle);
		ArrayList<Ressource> liste_serie = tab_serie.get(cle);
		
		for(Ressource f : liste_film){
			if (f.getTitre().toUpperCase().compareTo(titre.toUpperCase())==0){
				return f;
			}
		}
		
		for(Ressource s : liste_serie){
			if (s.getTitre().toUpperCase().compareTo(titre.toUpperCase())==0){
				return s;
			}
		}
		return null;
	}
	
	/**
	 * Récupère ou Initialise les Similarités d'une Ressource
	 * @param titre
	 * @return Liste des Similarites
	 */
	public ArrayList<Association> getSimilarite(String titre){
		Ressource r = this.recherche(titre);
		if(r!=null && r.getSimilaire().size()==0){
			ArrayList<Ressource> list = new ArrayList<Ressource>();
			list.add(r);
			Similarite.init(this, list);
		}else{
			if(r == null) return null;
		}
		return r.getSimilaire();
	}
	
	/**
	 * Liste les films vu par l'utilisateur
	 * @return ArrayList : Film vu
	 */
	public ArrayList<Ressource> list_vu(){
		ArrayList<Ressource> list = new ArrayList<Ressource>();
		
		for (Entry<Integer, ArrayList<Ressource>> entry : this.tab_film.entrySet()) {
			if (!entry.getValue().isEmpty()) {
				for (Ressource r : this.tab_film.get(entry.getKey())) {
					if(r.isVu()) list.add(r);
				}
			}
		}
		
		for (Entry<Integer, ArrayList<Ressource>> entry : this.tab_serie.entrySet()) {
			if (!entry.getValue().isEmpty()) {
				for (Ressource r : this.tab_serie.get(entry.getKey())) {
					if(r.isVu()) list.add(r);
				}
			}
		}

		return list;
	}
	
	/**
	 * Note moyenne de la vidéothèque
	 * @return int : Note moyenne
	 */
	public double note_moyenne() {
		double note = 0;
		int compt = 0;
		for (Entry<Integer, ArrayList<Ressource>> entry : this.tab_film.entrySet()) {
			if (!entry.getValue().isEmpty()) {
				for (Ressource r : this.tab_film.get(entry.getKey())) {
					if(r.getNote() <= 10 && r.getNote() >= 0) {
						note += r.getNote();
						compt++;
					}
				}
			}
		}
		
		for (Entry<Integer, ArrayList<Ressource>> entry : this.tab_serie.entrySet()) {
			if (!entry.getValue().isEmpty()) {
				for (Ressource r : this.tab_serie.get(entry.getKey())) {
					if(r.getNote() <= 10 && r.getNote() >= 0) {
						note += r.getNote();
						compt++;
					}
				}
			}
		}
		note = note / compt;
		return note;
	}
	
	/**
	 * Liste dont la note est supérieure à la moyenne
	 * @return ArrayList : Film note > note_moyenne
	 */
	public ArrayList<Ressource> list_films_sup_moy(){
		ArrayList<Ressource> nouvelle_liste = new ArrayList<Ressource>();
		ArrayList<Ressource> liste_vue = this.list_vu();
		double note_moy = this.note_moyenne();
		for(int i=0; i<liste_vue.size(); i++) {
			if(liste_vue.get(i).getNote() >= note_moy) {
				nouvelle_liste.add(liste_vue.get(i));
			}
		}
		
		return nouvelle_liste;
	}
	
	
	/**
	 * Sauvegarde la Vidéothèque courante dans un fichier binaire
	 * @param fichier : Nom du fichier
	 */
	public int sauvegarder(String fichier){
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier));
			oos.writeObject(this);
			oos.close();
			return 0;
			
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
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