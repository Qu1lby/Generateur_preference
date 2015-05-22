import java.util.ArrayList;


public abstract class Recommandation {
	/**
	 * Permet de créer une liste de recommandation de films à partir des films de la vidéothèque 
	 * @param source : Liste de Ressources déjà visionnées
	 * @return code retour
	 */
	
	public static void init(Videotheque v) {
		ArrayList<Ressource> liste_films_vus = v.list_films_sup_moy();
		ArrayList<Ressource> liste_des_similaires = new ArrayList<Ressource>();
		
		for(int i=0; i<liste_films_vus.size(); i++) {
			//liste_des_similaires.add(liste_films_vus.get(i).getSimilaire());
		}
		
	}
	
	
}

