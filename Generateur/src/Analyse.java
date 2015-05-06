/**
 * Classe Analyse, contient les m�thodes pour le traitement des flux
 * et des Tables de films/s�ries
 * 
 * @author Kilian Cuny
 * @author Guillaume Haben
 * 
 * @version 1.0
 */

import java.io.*;

public abstract class Analyse {
	
	public static int charger(Videotheque v, String fichier){
		try {
			BufferedReader fr = new BufferedReader(new FileReader(fichier));
			String ligne = "";
			
			do{
				// 1�re ligne
				ligne = fr.readLine();
				
				boolean serie = ligne.contains("Series");
	
				String[] decoupage = ligne.split(" ", 2);
				decoupage = decoupage[1].split("\\(", 2);
				
				String titre = decoupage[0].substring(1, decoupage[0].length());
				int annee = Integer.parseInt(decoupage[1].substring(decoupage[1].length()-5,
						decoupage[1].length()-1));
				
				System.out.println(titre);
				System.out.println(annee);
				
				// Ligne vide
				ligne = fr.readLine();
				
				
				// Synopsis
				String synopsis = "";
				ligne = fr.readLine();
				
				while (ligne.substring(0, 8).compareTo("Director")!= 0 |
						ligne.substring(0, 5).compareTo("With")!= 0){
					synopsis+= ligne;
					ligne = fr.readLine();
				}
				
				
				
				
			}while (ligne != null);
			fr.close();
		} catch (IOException e) {
			System.out.println("Erreur");
		}
		
		return 1;
		
	}
	
	
	/**
	 * Fonction static de Hashage
	 * 
	 * @param c : Caract�re � analyser
	 * @return place dans l'alphabet de c
	 */
	public static int Hashage(char c){
		int place = (int)c;
		if(place<=122 & place>=97){
			return (place-96);
		}
		return 27;
	}
}
