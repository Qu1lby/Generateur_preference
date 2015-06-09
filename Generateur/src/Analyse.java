/**
 * Classe Analyse, contient les méthodes pour le traitement des flux
 * et des tables de Films/Séries
 * 
 * @author Kilian Cuny
 * @author Guillaume Haben
 * @version 2.0
 */

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

public final class Analyse {

	private static final int ERROR = -1;
	private static final int EXIT = 0;
	
	/** Constructeur privé */
	private Analyse() {
		
	}
	
	/**
	 * Charge le fichier, le décompose et instancie les ressources
	 * correspondantes
	 * @param v : Vidéothèque pour laquelle on attachera les ressources
	 * @param fichier : Doit être au format standard de lecture
	 * @return int : code_retour
	 */
	public static int charger(Videotheque v, String fichier) {
		try{
			if (fichier == null) return ERROR;

			BufferedReader fr = new BufferedReader(new FileReader(fichier));
			String ligne = fr.readLine();
			
			// Prepare instantiation
			ArrayList<String> genres, acteurs;
			boolean serie;
			int annee, duree;
			String titre, synopsis, director, type;
			String[] decoupage;

			do {
				// Echappe d'eventuelles lignes vides en début de fichier
				while (ligne != null && ligne.length() == 0) {
					ligne = fr.readLine();
				}

				// Ligne du Titre
				serie = ligne.contains("Series");

				decoupage = ligne.split(" ", 2);
				decoupage = decoupage[1].split("\\(", 2);

				titre = decoupage[0].substring(1, decoupage[0].length() - 1);
				titre = new String(titre.getBytes(), Charset.forName("UTF-8"));
				decoupage = decoupage[1].split("\\)", 2);

				// Vérification que la ressource sera valide
				if (titre != null && titre != ""){
					annee = -1;
					if (serie) annee = Integer.parseInt(decoupage[0].substring(0, 4));
					else annee = Integer.parseInt(decoupage[0]);

					ligne = fr.readLine();

					// Synopsis
					synopsis = "";
					director = "";

					if (!serie) {
						while (true) {
							if (ligne.length() >= 8) {
								if (ligne.substring(0, 8).compareTo("Director") != 0) {
									synopsis += ligne;
									ligne = fr.readLine();
								}else break;
							}else ligne = fr.readLine();
						}

						decoupage = ligne.split(":");
						director = decoupage[1].substring(1, decoupage[1].length() - 1);
						ligne = fr.readLine();

						synopsis = new String(synopsis.getBytes(), Charset.forName("UTF-8"));
						director = new String(director.getBytes(), Charset.forName("UTF-8"));

					}else {
						while (true) {
							if (ligne.length() >= 8) {
								if (ligne.substring(0, 4).compareTo("With") != 0) {
									synopsis += ligne;
									ligne = fr.readLine();
								}else break;
							}else ligne = fr.readLine();
						}
					}

					// Acteurs
					decoupage = ligne.split(":");
					decoupage = decoupage[1].split(",");

					// Dernier nom contient un espace
					decoupage[decoupage.length - 1] = decoupage[decoupage.length - 1].substring(0,
							decoupage[decoupage.length - 1].length() - 1);

					acteurs = new ArrayList<String>();
					for (String nom : decoupage) {
						nom = nom.substring(1);
						acteurs.add(nom);
					}

					// Genre
					ligne = fr.readLine();
					decoupage = ligne.split(" ");
					genres = new ArrayList<String>();
					duree = -1;

					if (decoupage[decoupage.length - 1].compareTo("mins.") == 0) {
						for (int i = 0; i < decoupage.length - 2; i += 2) {
							genres.add(decoupage[i]);
						}
						duree = Integer.parseInt(decoupage[decoupage.length - 2]);
					}else {
						for (int i = 0; i < decoupage.length; i += 2) {
							genres.add(decoupage[i]);
						}
					}

					type = "Film";
					if (serie) type = "Serie";

					Ressource res = new Ressource(titre, annee, synopsis, acteurs, genres, duree,
							type, director);

					// Obtention de la clé de Hashage
					final int cle = Analyse.hashage(titre.toUpperCase().charAt(0));
					v.ajouter(cle, res, type);
				}

				// Echappe d'eventuelles lignes vides
				ligne = fr.readLine();
				while (ligne != null && ligne.length() == 0) {
					ligne = fr.readLine();
				}
			}while (ligne != null);

			fr.close();

		}catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		}
		return EXIT;
	}

	/**
	 * Fonction static de Hashage
	 * @param car : Caractère à analyser
	 * @return Place dans l'alphabet du caractère
	 */
	public static int hashage(char car) {
		final int place = (int) car;
		// Majuscule
		if (place <= 90 && place >= 65) return (place - 65);
		// Minuscule
		if (place <= 122 && place >= 97) return (place - 97);
		// Défaut
		return 26;
	}
}
