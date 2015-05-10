/**
 * Classe Ressource, représente un film ou une série
 * @author Guillaume Haben
 * @author Kilian Cuny
 *
 * @verion 1.0
 */

import java.io.Serializable;
import java.util.*;

@SuppressWarnings("serial")
public class Ressource implements Serializable, Comparable<Ressource> {
	private static int id = 1;
	
	@SuppressWarnings("unused")
	private int id_ressource = id;
	private int annee, duree, note;
	private String titre, synopsis, type, realisateur;
	private ArrayList<String> acteurs, genres;
	private boolean vu;
	
	
	/**
	 * Constructeur de Ressource
	 */
	public Ressource(String titre, int annee, String synopsis,
			ArrayList<String> acteurs, ArrayList<String> genres, int duree,
			String type, String realisateur) {
		
		this.titre = titre;
		this.synopsis = synopsis;
		this.type = type;
		this.note = -1;
		this.vu = false;
		
		if(acteurs == null){
			this.acteurs = new ArrayList<String>();
		}this.acteurs = acteurs;
		
		if(genres == null){
			this.genres = new ArrayList<String>();
		}this.genres = genres;
		
		if(realisateur == null){
			realisateur = "Inconnu";
		}else this.realisateur = realisateur;
		
		if(annee > 1900 && annee < 2050){
			this.annee = annee;
		}else this.annee = 0;
		
		if(duree == -1){
			this.duree = 0;
		}else this.duree = duree;
		
		id++;
	}
	
	/**
	 * Affiche la ressource à l'écran
	 */
	public String toString() {
		if(this.type == "Serie") {
			return "Série \n Titre = " + titre + "\n Année de sortie = " + annee + 
					"\n Synopsis = "+ synopsis + "\n Acteurs = " + acteurs +
					"\n Genres = " + genres + "\n Duree : " + duree + "\n Type = " + type +
					"\n Note = " + note + "\n Vu = " + vu;
		}else {
			return "Film \n Titre = " + titre + "\n Année de sortie = " + annee + 
					"\n Synopsis = " + synopsis + "\n Acteurs = " + acteurs + 
					"\n Genres = " + genres + "\n Duree : " + duree + "mins\n Type = " + type +
					"\n Realisateur = " + realisateur + "\n Note = " + note + "\n Vu = " + vu;
		}
		
		/*
		 * Eh eh mon petit Gui par exemple si le film n'as pas de
		 * note plutot que d'afficher -1 tu peux mettre non renseigné
		 * pareil date et duree
		 * 
		 * Le plus simple c'est que tu crée un string et tu concatène après un test
		 * c'est moins joli mais c'est pas grave
		 * 
		 * Désolé pour le important de Fb en fait je pensais que l'affichage d'une Arraylist 
		 * planterait et qu'il faudrait la parcourir mais non ca marche chatteux ! :)
		 * 
		 */
	}
	
	/**
	 * Permet de comparer des ressources
	 * @param arg: Ressource
	 */
	public int compareTo(Ressource arg) {
		return this.titre.compareTo(arg.getTitre());
	}
	
	//******* GETTER ET SETTER *******//
	
	public int getNote() {
		return note;
	}

	/**
	 * Setter note (compris entre 0 et 10)
	 * @param note : the note to set
	 */
	public void setNote(int note) {
		if(note>=0 && note<=10){
			this.note = note;
		}else {
			if(note>10) this.note = 10;
			if(note<0) this.note = -1;
		}
	}

	public boolean isVu() {
		return vu;
	}

	/**
	 * Setter vu
	 * @param vu : the vu to set
	 */
	public void setVu(boolean vu) {
		if(vu){
			// Ajouter film PREFERENCE
		}else{
			// Oter film PREFERENCES
		}
		this.vu = vu;
	}

	public ArrayList<String> getActeur() {
		return acteurs;
	}
	
	public int getAnnee() {
		return annee;
	}
	
	public ArrayList<String> getGenre() {
		return genres;
	}
	
	public String getRealisateur() {
		return realisateur;
	}
	
	public String getTitre() {
		return titre;
	}
	
	public String getType() {
		return type;
	}
}
