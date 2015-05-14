/**
 * Classe Preference, g�re les pr�f�rences utilisateurs
 * @author Kilian Cuny
 * @author Guillaume Haben
 *
 * @verion 1.0
 */

import java.util.ArrayList;
import java.util.Collections;

public abstract class Similarite {

	
	/**
	 * Ajoute (ou non) une ressource � la liste des films/series similaires
	 * d'une autre si la note est > a min(note) de la liste
	 * @param liste : ressources associ�es
	 * @param r : Ressource 
	 */
	public static void addSimilarite(ArrayList<Association> liste, Association a){
		if(liste.size()<5){
			liste.add(a);
		}
		
		for( Association r_tmp : liste){
			if(r_tmp.getNote()<a.getNote()){
				liste.remove(r_tmp);
				liste.add(a);
				break;
			}
		}
				
		Collections.sort(liste);
	}
	
	
	/**
	 * Syst�me de notation sur la ressemblance entre
	 * deux Ressources
	 * @param r1 : Ressource 1
	 * @param r2 : Ressource 2
	 * @return : 0 < x < 5
	 */
	public static int note(Ressource r1, Ressource r2){
		int note = 0;
		if(r1.getTitre().compareTo(r2.getTitre())!=0){
						
			// ANNEE DES RESSOURCES
			if(r1.getAnnee() == r2.getAnnee()){
				note += 0.5;
			}else{
				if(r1.getAnnee()+1 == r2.getAnnee() || r1.getAnnee()-1 == r2.getAnnee()){
					note += 0.25;
				}
			}
			
			// ACTEURS 
			ArrayList<String> r1_ac = r1.getActeur();
			ArrayList<String> r2_ac = r2.getActeur();
			
			note += givePointArray(r1_ac, r2_ac);
			
			// GENRES
			ArrayList<String> r1_ge = r1.getGenre();
			ArrayList<String> r2_ge = r2.getGenre();
			note += givePointArray(r1_ge, r2_ge);
			
			// TYPE
			if(r1.getType().compareTo(r2.getType())==0) note += 0.5;
			
			// REALISATEUR
			if(r1.getRealisateur().compareTo(r2.getRealisateur())==0) note += 1;
			
		}
		return note;
	}
	
	
	/**
	 * Retourne une note de ressemblance entre deux array
	 * + 0.5 par similarit�, up to 1.5
	 * @param r1_ac : array 1
	 * @param r2_ac : array 2
	 * @return 0 < x < 1.5
	 */
	public static int givePointArray(ArrayList<String> r1, ArrayList<String> r2) {
		int done = 0;
		int note = 0;
		for(String nom_1 : r1){
			for(String nom_2 : r2){
				if(nom_1.compareTo(nom_2)==0){
					note += 0.5;
					done += 1;
					if(done == 3)break;
				}
			}
			if(done == 3) break;
		}
		return note;
	}	
}