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
		
		if(realisateur == null || realisateur == ""){
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
		if(this.type.compareTo("Serie") == 0) { //Bug : type = film lors d'un chargement de .bi (pas de bug lors de la création)
			String Serie = "Série \n Titre = " + titre;
			if(synopsis != null){
				Serie = Serie + "\n Synopsis = "+ synopsis; 
			}
			if(acteurs != null){
				Serie = Serie + "\n Acteurs = " + acteurs;
			}
			if(genres != null){
				Serie = Serie + "\n Genres = " + genres;
			}
			if(annee > 1900 && annee < 2050){
				Serie = Serie + "\n Année de sortie = " + annee;
			}
			if(duree != -1 && duree<240){
				int heures = duree/60;
		        int minutes = duree%60;
		        String time = heures + "h" + minutes;
				Serie = Serie + "\n Durée = " + time;
			}if(note != -1 && note <= 10 && note >= 0){
				Serie = Serie + "\n Note = " + note;
			}
			if (vu == false) {
				Serie = Serie + "\n Visionné = Non";
			}
			if (vu == true) {
				Serie = Serie + "\n Visionné = Oui";
			}
			return Serie;
		}else {
			String Film = "Film \n Titre = " + titre;
			if(synopsis != null){
				Film = Film + "\n Synopsis = "+ synopsis; 
			}
			if(acteurs != null){
				Film = Film + "\n Acteurs = " + acteurs;
			}
			if(genres != null){
				Film = Film + "\n Genres = " + genres;
			}
			if(realisateur != null || realisateur != ""){
				Film = Film + "\n Réalisateur = " + realisateur;
			}
			if(annee > 1900 && annee < 2050){
				Film = Film + "\n Année de sortie = " + annee;
			}
			if(duree != -1 && duree<240){
				int heures = duree/60;
		        int minutes = duree%60;
		        String time = heures + "h" + minutes;
				Film = Film + "\n Durée = " + time;
			}
			if(note != -1 && note <= 10 && note >= 0){
				Film = Film + "\n Note = " + note;
			}
			if (vu == false) {
				Film = Film + "\n Visionné = Non";
			}
			if (vu == true) {
				Film = Film + "\n Visionné = Oui";
			}
			return Film;
		}
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
