/**
 * Cette classe permet de cr�er une liste de recommandation de Ressource
 *  � partir de celles vues de la vid�oth�que 
 * 
 * @author Guillaume Haben
 * @author Kilian Cuny
 * @version 1.0
 */

import java.util.ArrayList;

public class Recommandation {

	ArrayList<Ressource> liste_des_similaires;
	ArrayList<Ressource> liste_des_premiers_similaires;

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

		for (int i = 0; i < liste_films_vus.size(); i++){
			ArrayList<Association> liste = liste_films_vus.get(i).getSimilaire();
			liste_des_premiers_similaires.add(liste.get(0).getRessemblance());
			for (Association a : liste){
				if (!a.getRessemblance().getVu()){
					if (!liste_des_similaires.contains(a.getRessemblance()))
						liste_des_similaires.add(a.getRessemblance());
				}
			}
		}
	}

	/**
	 * Affiche la liste des Ressources recommand�es
	 */
	public void afficher() {
		for (int i = 0; i < liste_des_premiers_similaires.size(); i++)
			System.out.println(liste_des_premiers_similaires.get(i).toString());
	}

	/**
	 * Retourne la liste des Ressources recommand�es
	 * @return the liste_des_similaires
	 */
	public ArrayList<Ressource> getListe_des_similaires() {
		return liste_des_similaires;
	}

}
