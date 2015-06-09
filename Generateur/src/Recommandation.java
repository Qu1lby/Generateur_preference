/**
 * Cette classe permet de créer une liste de recommandation de Ressources
 * à partir de celles déjà vues de la vidéothèque 
 * 
 * @author Guillaume Haben
 * @author Kilian Cuny
 * @version 2.0
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Recommandation {

	/** Liste des ressources recommandées globalement */
	private ArrayList<Ressource> listeRecom;
	
	/** Table des ressources triées par année */
	private HashMap<Integer, ArrayList<String>> recomParDate;
	
	/** Nombre de films dans la recommandation */
	private static final int nbRecom = 5;

	/** Constructeur par défaut */
	public Recommandation() {
		listeRecom = new ArrayList<Ressource>();
		recomParDate = new HashMap<Integer, ArrayList<String>>();
	}

	/**
	 * Permet d'initialiser la liste des recommandations
	 * @param v : Vidéothèque
	 */
	public void init(Videotheque v) {
		ArrayList<Ressource> listeFilmsVus = v.list_films_sup_moy();
		Similarite.init(v, listeFilmsVus);

		// L'utilisateur a plus de 'nbRecom' films vus > moyenne
		if (listeFilmsVus.size() > nbRecom) {
			while (listeRecom.size() <= nbRecom) {
				
				//On prend une ressource au hasard
				ArrayList<Association> srcRandom = getRandomList(listeFilmsVus).getSimilaire();
				if (srcRandom.get(0).getRessemblance().isVu() &&
						!listeRecom.contains(srcRandom.get(0).getRessemblance())) {
					listeRecom.add(srcRandom.get(0).getRessemblance());
				}
			}
		}
		else if (listeFilmsVus.size() < nbRecom) {
			for (int i = 0; i < nbRecom; i++) {
				for (int j = 0; j < listeFilmsVus.size(); j++) {
					if (!listeFilmsVus.get(j).getSimilaire().get(i).getRessemblance().isVu() &&
							!listeRecom.contains(listeFilmsVus.get(j).getSimilaire().get(i).getRessemblance())) {
						listeRecom.add(listeFilmsVus.get(j).getSimilaire().get(i).getRessemblance());
					}
					if (listeRecom.size() == nbRecom) break;
				}
				if (listeRecom.size() == nbRecom) break;
			}
			
		}
		// Liste_films_vus.size() = nbRecom
		else {
			for (int j = 0; j < listeFilmsVus.size(); j++) {
				if (!listeFilmsVus.get(j).getSimilaire().get(0).getRessemblance().isVu() &&
						!listeRecom.contains(listeFilmsVus.get(j).getSimilaire().get(0).getRessemblance())) {
					listeRecom.add(listeFilmsVus.get(j).getSimilaire().get(0).getRessemblance());
				}
			}
		}	
	}
	
	/**
	 * Génére une HashMap des ressources avec pour clé leur date
	 * @param v : Vidéothèque
	 * @return Hashmap de Ressources
	 */
	public HashMap<Integer, ArrayList<String>> recommandationDate(final Videotheque v) {
		ArrayList<Ressource> liste_films_vus = v.list_films_sup_moy();
		ArrayList<String> newListe = new ArrayList<String>();
		
		for(int i=0; i<liste_films_vus.size(); i++) {
            ArrayList<Association> liste = liste_films_vus.get(i).getSimilaire();
            
            for(Association a : liste) {
                if(!a.getRessemblance().isVu() && !listeRecom.contains(a.getRessemblance())) {
            		final int annee = a.getRessemblance().getAnnee();
            		final String titre = a.getRessemblance().getTitre();
            		
                	//Si l'année existe déjà dans le hashmap
                	if (recomParDate.containsKey(annee)) {
                		if(!recomParDate.containsValue(titre))
                			recomParDate.get(annee).add(titre);                   	
                	}else { //On créé la clé correspondant à l'année
                		newListe.clear();
                		newListe.add(titre);
                		if(!recomParDate.containsValue(titre))
                			recomParDate.put(annee, newListe);
                	}
                }
            }
    	}
		return recomParDate;
	}
	
	/**
	 * Récupère une ressource aléatoire dans une liste de ressource
	 * @return Ressource
	 */
	private Ressource getRandomList(ArrayList<Ressource> liste) {
		final int index = (int)(Math.random()*liste.size());	
	    return liste.get(index);
	}

	/**
	 * Affiche la liste des Ressources recommandées
	 */
	public void afficher() {
		for (int i = 0; i < listeRecom.size(); i++)
			System.out.println(listeRecom.get(i).toString());
	}

	/**
	 * Retourne la liste des Ressources recommandées en entier
	 * @return the listeRecom
	 */
	public ArrayList<Ressource> getListeDesSimilaires() {
		return listeRecom;
	}
}