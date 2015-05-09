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
	@SuppressWarnings("unused")
	private static int id = 0;
	
	private String titre;
	private int annee;
	private String synopsis;
	private ArrayList<String> acteurs;
	private ArrayList<String> genres;
	private String duree;
	private String type;
	private String realisateur;
	private int note;
	private boolean vu;
	
	/**
	 * Constructeur de Ressource
	 */
	public Ressource(String titre, int annee, String synopsis,
			ArrayList<String> acteurs, ArrayList<String> genres, int duree,
			String type, String realisateur) {
		
		this.titre = titre;
		this.annee = annee;
		this.synopsis = synopsis;
		this.acteurs = acteurs;
		this.genres = genres;
		this.type = type;
		this.realisateur = realisateur;
		this.note = -1;
		this.vu = false;
		
		if(duree == -1){
			this.duree = "Inconnue";
		}else this.duree = duree + " mins";
		
		id++;
	}
	
	/**
	 * Getter note
	 * @return the note
	 */
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

	/**
	 * Getter vu
	 * @return the vu
	 */
	public boolean isVu() {
		return vu;
	}

	/**
	 * Setter vu
	 * @param vu : the vu to set
	 */
	public void setVu(boolean vu) {
		this.vu = vu;
	}

	/**
	 * Getter type
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Getter titre
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * Affiche la ressource à l'écran
	 */
	public String toString() {
		if (this.type == "Serie") {
			return "Série \n Titre = " + titre + "\n Année de sortie = " + annee + 
					"\n Synopsis = "+ synopsis + "\n Acteurs = " + acteurs +
					"\n Genres = " + genres + "\n Duree : " + duree + "\n Type = " + type +
					"\n Note = " + note + "\n Vu = " + vu;
		}else {
			return "Film \n Titre = " + titre + "\n Année de sortie = " + annee + 
					"\n Synopsis = " + synopsis + "\n Acteurs = " + acteurs + 
					"\n Genres = " + genres + "\n Duree : " + duree + "\n Type = " + type +
					"\n Realisateur = " + realisateur + "\n Note = " + note + "\n Vu = " + vu;
		}
		
		/*
		 * 
		 * Eh eh mon petit Gui par exemple si le film n'as pas de
		 * note plutot que d'afficher -1 tu peux mettre non renseigné
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
}
