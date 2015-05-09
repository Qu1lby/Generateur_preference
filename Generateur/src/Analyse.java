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
import java.util.ArrayList;

public abstract class Analyse {
	
	public static int charger(Videotheque v, String fichier){
		try {
			BufferedReader fr = new BufferedReader(new FileReader(fichier));
			String ligne = fr.readLine();
			
			do{
				// Echappe d'eventuelles lignes vides en d�but de fichier
				while(ligne != null && (ligne.compareTo("")==0)){
					ligne = fr.readLine();
				}
				
				// 1�re ligne				
				boolean serie = ligne.contains("Series");
	
				String[] decoupage = ligne.split(" ", 2);
				decoupage = decoupage[1].split("\\(", 2);
				
				String titre = decoupage[0].substring(1);
				decoupage = decoupage[1].split("\\)", 2);
				
				int annee = 0;
				if(serie) annee = Integer.parseInt(decoupage[0].substring(0,4));
				else{
					annee = Integer.parseInt(decoupage[0]);
				}
				
				// Ligne vide
				ligne = fr.readLine();
				
				// Synopsis
				String synopsis = "";
				String director = "";
				ligne = fr.readLine();

				if(!serie){
					while (ligne.substring(0, 8).compareTo("Director")!= 0){
						synopsis+= ligne;
						ligne = fr.readLine();
					}
					
					decoupage = ligne.split(":");
					director = decoupage[1].substring(1);
					ligne = fr.readLine();
					
				}else{
					while (ligne.substring(0, 4).compareTo("With")!= 0){
						synopsis+= ligne;
						ligne = fr.readLine();
					}
				}
				
				// Acteurs
				decoupage = ligne.split(":");
				decoupage = decoupage[1].split(",");
				
				ArrayList<String> acteurs = new ArrayList<String>();
				for(String nom : decoupage){
					nom = nom.substring(1);
					acteurs.add(nom);
				}
				
				// Genre
				ligne = fr.readLine();
				decoupage = ligne.split(" ");
				ArrayList<String> genres = new ArrayList<String>();
				int duree = 0;
				
				if(decoupage[decoupage.length-1].compareTo("mins.") == 0){
					for(int i = 0; i< decoupage.length-2; i+=2){
						genres.add(decoupage[i]);
					}
					duree = Integer.parseInt(decoupage[decoupage.length-2]);
				}else{
					for(int i = 0; i< decoupage.length; i+=2){
						genres.add(decoupage[i].substring(1));
					}
				}
				
				String type = "Film";
				if(serie) type = "Serie";
				Ressource r = new Ressource(titre, annee, synopsis, acteurs, genres, type, director, -1, false);
				
				int cle = Analyse.Hashage(titre.charAt(0));
				v.ajouter(cle, r, type);
				
				ligne = fr.readLine();
				
				while(ligne != null && (ligne.compareTo("")==0)){
					ligne = fr.readLine();
				}
				
			}while (ligne != null);
			fr.close();
		} catch (IOException e) {
			System.out.print("Please, report this message : ");
			e.printStackTrace();
			return -1;
		}
		
		return 0;
		
	}
	
	
	/**
	 * Fonction static de Hashage
	 * 
	 * @param c : Caract�re � analyser
	 * @return place dans l'alphabet de c
	 */
	public static int Hashage(char c){
		int place = (int)c;
		if(place<=90 & place>=65){
			return (place-65);
		}
		return 26;
	}
}
