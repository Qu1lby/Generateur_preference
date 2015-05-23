import java.util.ArrayList;

public class Recommandation {
	/**
	 * Permet de créer une liste de recommandation de films à partir des films de la vidéothèque 
	 * @param source : Liste de Ressources déjà visionnées
	 * @return code retour
	 */
	ArrayList<Ressource> liste_des_similaires = new ArrayList<Ressource>();
	
	public Recommandation() {
		liste_des_similaires = null;
	}
	
	public void init(Videotheque v) {
		ArrayList<Ressource> liste_films_vus = v.list_films_sup_moy();
		
		for(int i=0; i<liste_films_vus.size(); i++) {
            ArrayList<Association> liste = liste_films_vus.get(i).getSimilaire();
            for( Association a : liste){
                if(!a.getRessemblance().getVu()) {
                    if(!liste_des_similaires.contains(a.getRessemblance())){
                        liste_des_similaires.add(a.getRessemblance());
                    }
                }
            }
    	}
	}
	
	public void afficher() {
		for(int i=0; i<liste_des_similaires.size(); i++) {
			liste_des_similaires.get(i).toString();
		}
	}

	/**
	 * @return the liste_des_similaires
	 */
	public ArrayList<Ressource> getListe_des_similaires() {
		return liste_des_similaires;
	}
	
	
	
}

