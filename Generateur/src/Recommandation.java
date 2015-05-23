import java.util.ArrayList;

public class Recommandation {
	/**
	 * Permet de cr�er une liste de recommandation de films � partir des films de la vid�oth�que 
	 * @param source : Liste de Ressources d�j� visionn�es
	 * @return code retour
	 */
	ArrayList<Ressource> liste_des_similaires;
	
	/**
	 * Constructeur
	 */
	public Recommandation() {
		liste_des_similaires = new ArrayList<Ressource>();
	}
	
	/**
	 * Permet d'initialiser la liste des recommandations
	 * @param v : Vid�oth�que
	 */
	public void init(Videotheque v) {
		ArrayList<Ressource> liste_films_vus = v.list_films_sup_moy();
		Similarite.init(v, liste_films_vus);
		
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
	
	/**
	 * Affiche la liste des films recommand�s
	 */
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

