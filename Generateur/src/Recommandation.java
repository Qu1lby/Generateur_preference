import java.util.ArrayList;


public abstract class Recommandation {
	/**
	 * Permet de créer une liste de recommandation de films à partir des films de la vidéothèque 
	 * @param source : Liste de Ressources déjà visionnées
	 * @return code retour
	 */
	
	public static void init(Videotheque v) {
		ArrayList<Ressource> liste_films_vus = v.list_vu();
		
	}
}
