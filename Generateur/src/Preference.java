import java.util.ArrayList;

/**
 * Classe Preference, gère les préférences utilisateurs
 * @author Kilian Cuny
 * @author Guillaume Haben
 *
 * @verion 1.0
 */

public class Preference {

	
	public Preference(){
		
	}
	
	/**
	 * Système de notation sur la ressemblance entre
	 * deux Ressources
	 * @param r1 : Ressource 1
	 * @param r2 : Ressource 2
	 * @return : 0 < x < 5
	 */
	public int note(Ressource r1, Ressource r2){
		
		int note = 0;
		
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
		
		return note;
	}
	
	
	/**
	 * Retourne une note de ressemblance entre deux array
	 * + 0.5 par similarité, up to 1.5
	 * @param r1_ac : array 1
	 * @param r2_ac : array 2
	 * @return 0 < x < 1.5
	 */
	public int givePointArray(ArrayList<String> r1, ArrayList<String> r2) {
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
