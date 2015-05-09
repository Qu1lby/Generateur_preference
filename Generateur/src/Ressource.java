/**
 * Classe Ressource, repr�sente un film ou une s�rie
 * @author Guillaume Haben
 * @author Kilian Cuny
 *
 * @verion 1.0
 */

import java.io.Serializable;
import java.util.*;

@SuppressWarnings("serial")
public class Ressource implements Serializable {
	private static int id = 0;
	
	//private int identifiant;
	private String titre;
	private int annee;
	private String synopsis;
	private ArrayList<String> acteurs;
	private ArrayList<String> genres;
	private String type;
	private String realisateur;
	private int note;
	private boolean vu;
	
	/**
	 * Constructeur
	 */
	public Ressource(String titre, int annee, String synopsis,
			ArrayList<String> acteurs, ArrayList<String> genres, String type,
			String realisateur, int note, boolean vu) {
		
		//this.identifiant = id;
		this.titre = titre;
		this.annee = annee;
		this.synopsis = synopsis;
		this.acteurs = acteurs;
		this.genres = genres;
		this.type = type;
		this.realisateur = realisateur;
		this.note = note;
		this.vu = vu;
		id++;
	}
	
	/**
	 * Getter
	 * @return note
	 */
	public int getNote() {
		return note;
	}

	/**
	 * Setter
	 * @param note : the note to set
	 */
	public void setNote(int note) {
		this.note = note;
	}

	/**
	 * Getter
	 * @return vu
	 */
	public boolean isVu() {
		return vu;
	}

	/**
	 * Setter
	 * @param vu : the vu to set
	 */
	public void setVu(boolean vu) {
		this.vu = vu;
	}

	/**
	 * Getter
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Affiche la ressource � l'�cran
	 */
	public String toString() {
		if (this.type == "Serie") {
			return "S�rie \n Titre = " + titre + "\n Ann�e de sortie = " + annee + "\n Synopsis = "
				+ synopsis + "\n Acteurs = " + acteurs + "\n Genres = " + genres
				+ "\n Type = " + type + "\n Note = " + note + "\n Vu = " + vu;
		}
		else {
			return "Film \n Titre = " + titre + "\n Ann�e de sortie = " + annee + "\n Synopsis = "
					+ synopsis + "\n Acteurs = " + acteurs + "\n Genres = " + genres
					+ "\n Type = " + type + "\n Realisateur = " + realisateur + "\n Note = "
					+ note + "\n Vu = " + vu;
		}
	}
}
