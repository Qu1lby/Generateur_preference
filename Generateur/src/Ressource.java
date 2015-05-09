/**
 * Classe Ressource, repr�sente une Ressource
 * @author Guillaume Haben
 * @author Kilian Cuny
 *
 * @verion 1.0
 */

import java.io.Serializable;
import java.util.*;

@SuppressWarnings("serial")
public class Ressource implements Serializable {
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
			String type, String realisateur, int note, boolean vu) {
		
		this.titre = titre;
		this.annee = annee;
		this.synopsis = synopsis;
		this.acteurs = acteurs;
		this.genres = genres;
		this.type = type;
		this.realisateur = realisateur;
		this.note = note;
		this.vu = vu;
		
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
	 * Setter note
	 * @param note : the note to set
	 */
	public void setNote(int note) {
		this.note = note;
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
	 * Affiche la ressource � l'�cran
	 */
	public String toString() {
		if (this.type == "Serie") {
			return "S�rie \n Titre = " + titre + "\n Ann�e de sortie = " + annee + 
					"\n Synopsis = "+ synopsis + "\n Acteurs = " + acteurs +
					"\n Genres = " + genres + "\n Duree : " + duree + "\n Type = " + type +
					"\n Note = " + note + "\n Vu = " + vu;
		}else {
			return "Film \n Titre = " + titre + "\n Ann�e de sortie = " + annee + 
					"\n Synopsis = " + synopsis + "\n Acteurs = " + acteurs + 
					"\n Genres = " + genres + "\n Duree : " + duree + "\n Type = " + type +
					"\n Realisateur = " + realisateur + "\n Note = " + note + "\n Vu = " + vu;
		}
		
		/**
		 * 
		 * Eh eh mon petit Gui je t'aime beaucoup mais si tu veux afficher acteur ou genre 
		 * tu vas te retrouver avec une magnifique reference du style @121462132 :)
		 * C'est une liste il faut la parcourir
		 * 
		 * Le plus simple c'est que tu cr�e un string et tu concat�ne variable par variable
		 * c'est moins joli mais c'est plus propre et plus clair :)
		 * 
		 */
	}
}
