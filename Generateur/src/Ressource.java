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
	 * Constructeur de Ressource :
	 * Effectue les vérifications et changements adéquats
	 * Il est necessaire que le titre du film ne soit pas null
	 * 
	 * @param : Toutes les données nécessaires
	 */
	public Ressource(String titre, int annee, String synopsis,
			ArrayList<String> acteurs, ArrayList<String> genres, int duree,
			String type, String realisateur) {
		
		this.titre = titre;
		this.note = -1;
		this.vu = false;
		
		if(synopsis == null){
			this.synopsis = ""; // Par défaut
		}else this.synopsis = synopsis;
		
		if(type == null){
			this.type = "Film"; // Par defaut
		}else this.type = type;
		
		if(acteurs == null){
			this.acteurs = new ArrayList<String>();
		}else this.acteurs = acteurs;
		
		if(genres == null){
			this.genres = new ArrayList<String>();
		}else this.genres = genres;
		
		if(realisateur == null){
			this.realisateur = "Inconnu";  // Par defaut
		}else this.realisateur = realisateur;
		
		if(annee > 1900 && annee < 2050){
			this.annee = annee;
		}else this.annee = 0;
		
		if(duree == -1 || duree>240){
			this.duree = 0;
		}else this.duree = duree;
		
		id++;
	}
	
	
	/**
	 * Affiche une Ressource
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
	
	public int getDuree(){
		return duree;
	}
	
	public ArrayList<String> getGenre() {
		return genres;
	}
	
	public int getNote() {
		return note;
	}
	
	public String getRealisateur() {
		return realisateur;
	}
	
	public String getSynopsis() {
		return synopsis;
	}
	
	public String getTitre() {
		return titre;
	}
	
	public String getType() {
		return type;
	}
	
	public boolean isVu() {
		return vu;
	}
}
