/**
 * Cette classe permet de créer une liste de recommandation de Ressource
 *  à partir de celles vues de la vidéothèque 
 * 
 * @author Guillaume Haben
 * @author Kilian Cuny
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Recommandation {

	ArrayList<Ressource> liste_des_similaires;
	public HashMap<Integer, ArrayList<String>> recommandation_par_date;

	public Recommandation() {
		liste_des_similaires = new ArrayList<Ressource>();
		recommandation_par_date = new HashMap<Integer, ArrayList<String>>();
	}

	/**
	 * Permet d'initialiser la liste des recommandations
	 * @param v : Vidéothèque
	 */
	public void init(Videotheque v) {
		ArrayList<Ressource> liste_films_vus = v.list_films_sup_moy();
		Similarite.init(v, liste_films_vus);

		// Cas où l'utilisateur a plus de 5 films vu et sup à moy
		if (liste_films_vus.size() > 5) {
			// Tant qu'elle est pas remplie :
			while (liste_des_similaires.size() <= 5) {
				//On prend une ressource au hasard
				ArrayList<Association> ressource_random = getRandomList(liste_films_vus).getSimilaire();
				// Si elle est pas dans la liste
				if (ressource_random.get(0).getRessemblance().isVu() && !liste_des_similaires.contains(ressource_random.get(0).getRessemblance())) {
					//On l'ajoute
					liste_des_similaires.add(ressource_random.get(0).getRessemblance());
				}
			}
		}
		// Cas où l'utilisateur a moins de 5 films vu
		else if (liste_films_vus.size() < 5) {
			// Tant qu'elle est pas remplie :
			for (int k = 0; k < 5; k++) {
				for (int j = 0; j < liste_films_vus.size(); j++) {
					if (!liste_films_vus.get(j).getSimilaire().get(k).getRessemblance().isVu() && !liste_des_similaires.contains(liste_films_vus.get(j).getSimilaire().get(k).getRessemblance())) {
						liste_des_similaires.add(liste_films_vus.get(j).getSimilaire().get(k).getRessemblance());
					}
					if (liste_des_similaires.size() == 5) break;
				}
				if (liste_des_similaires.size() == 5) break;
			}
			
		}
		// Sinon liste_films_vus = 5
		else {
			// Tant qu'elle est pas remplie :
			for (int j = 0; j < liste_films_vus.size(); j++) {
				// Si elle est pas dans la liste
				if (!liste_films_vus.get(j).getSimilaire().get(0).getRessemblance().isVu() && !liste_des_similaires.contains(liste_films_vus.get(j).getSimilaire().get(0).getRessemblance())) {
					liste_des_similaires.add(liste_films_vus.get(j).getSimilaire().get(0).getRessemblance());
				}
			}
		}
			
	}
	
	public HashMap<Integer, ArrayList<String>> recommandation_date(Videotheque v) {
		ArrayList<Ressource> liste_films_vus = v.list_films_sup_moy();
		for(int i=0; i<liste_films_vus.size(); i++) {
            ArrayList<Association> liste = liste_films_vus.get(i).getSimilaire();
            
            for( Association a : liste){
                if(!a.getRessemblance().isVu()) {
                    if(!liste_des_similaires.contains(a.getRessemblance())){
                		int annee = a.getRessemblance().getAnnee();
                		String titre = a.getRessemblance().getTitre();
                    	//Si l'année existe déjà dans le hashmap
                    	if (recommandation_par_date.containsKey(annee)) {
                    		recommandation_par_date.get(annee).add(titre);                   	
                    	}
                    	else { //On créé la clé correspondant à l'année
                    		ArrayList<String> nouvelle_liste = new ArrayList<String>();
                    		nouvelle_liste.add(titre);
                    		recommandation_par_date.put(annee, nouvelle_liste);//nouvelle liste
                    	}
                    }
                }
            }
    	}
		return recommandation_par_date;
	}
	
	/**
	 * Récupère une ressource aléatoire dans une liste de ressource
	 * @return Ressource
	 */
	private Ressource getRandomList(ArrayList<Ressource> liste) {
		int index = (int)(Math.random()*liste.size());	
	    return liste.get(index);
	}

	/**
	 * Affiche la liste des Ressources recommandées
	 */
	public void afficher() {
		for (int i = 0; i < liste_des_similaires.size(); i++)
			System.out.println(liste_des_similaires.get(i).toString());
	}

	/**
	 * Retourne la liste des Ressources recommandées en entier
	 * @return the liste_des_similaires
	 */
	public ArrayList<Ressource> getListe_des_similaires() {
		return liste_des_similaires;
	}

}
