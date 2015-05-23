/**
 * Classe Ressource, repr�sente un film ou une s�rie
 * @author Guillaume Haben
 * @author Kilian Cuny
 * @version 1.0
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
	private ArrayList<Association> similaire;
	private boolean vu;
	private Date date;
	
	
	/**
	 * Constructeur de Ressource
	 * Effectue les v�rifications et changements ad�quats
	 * Il est necessaire que le titre du film ne soit pas null
	 * 
	 * @param : Toutes les donn�es n�cessaires
	 */
	public Ressource(String titre, int annee, String synopsis,
			ArrayList<String> acteurs, ArrayList<String> genres, int duree,
			String type, String realisateur) {
		
		this.titre = titre;
		
		this.note = -1;
		this.similaire = new ArrayList<Association>();
		this.vu = false;
		this.date = null;
		
		if(synopsis == null || synopsis.compareTo("")==0){
			this.synopsis = "Synopsis non renseign�"; // Par d�faut
		}else this.synopsis = synopsis;
		
		if(type == null || type.compareTo("")==0){
			this.type = "Film"; // Par defaut
		}else this.type = type;
		
		if(acteurs == null){
			this.acteurs = new ArrayList<String>();
		}else this.acteurs = acteurs;
		
		if(genres == null){
			this.genres = new ArrayList<String>();
		}else this.genres = genres;
		
		if(realisateur == null || realisateur.compareTo("")==0){
			this.realisateur = "Inconnu";  // Par defaut
		}else this.realisateur = realisateur;
		
		if(annee < 1900 && annee > 2050){
			this.annee = 0; // Par defaut
		}else this.annee = annee;
		
		if(duree == -1 || duree>240){
			this.duree = 0; // Par defaut
		}else this.duree = duree;
		
		id++;
	}
	
	void reinit(){
		this.vu = false;
		this.note = -1;
	}
	
	/* REVOIR LES TESTS PAR RAPPORT AU CONSTRUCTEUR ET INITIALISATION SI ATTRIBUT EST NULL*/
	
	/**
	 * Affiche une Ressource
	 */
	public String toString() {
		if(this.type.compareTo("Serie") == 0) { //Bug : type = film lors d'un chargement de .bi (pas de bug lors de la cr�ation)
			String Serie = "S�rie \n Titre = " + titre;
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
				Serie = Serie + "\n Ann�e de sortie = " + annee;
			}
			if(duree != -1 && duree<240){
				int heures = duree/60;
		        int minutes = duree%60;
		        String time = heures + "h" + minutes;
				Serie = Serie + "\n Dur�e = " + time;
			}if(note != -1 && note <= 10 && note >= 0){
				Serie = Serie + "\n Note = " + note;
			}
			if (vu == false) {
				Serie = Serie + "\n Visionn� = Non";
			}
			if (vu == true) {
				Serie = Serie + "\n Visionn� = Oui";
				Serie = Serie + "\n Date de visionnage = " + date;
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
				Film = Film + "\n R�alisateur = " + realisateur;
			}
			if(annee > 1900 && annee < 2050){
				Film = Film + "\n Ann�e de sortie = " + annee;
			}
			if(duree != -1 && duree<240){
				int heures = duree/60;
		        int minutes = duree%60;
		        String time = heures + "h" + minutes;
				Film = Film + "\n Dur�e = " + time;
			}
			if(note != -1 && note <= 10 && note >= 0){
				Film = Film + "\n Note = " + note;
			}
			if (vu == false) {
				Film = Film + "\n Visionn� = Non";
			}
			if (vu == true) {
				Film = Film + "\n Visionn� = Oui";
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
		this.vu = true;
	}

	public void setVu(boolean vu) {
		this.vu = vu;
	}
	
	public void setDate(Date d){
		this.date = d;
	}
	
	public Date getDate() {
		return this.date;
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
	
	public boolean getVu() {
		return vu;
	}
	
	public String getRealisateur() {
		return realisateur;
	}
	
	public ArrayList<Association> getSimilaire() {
		return similaire;
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
