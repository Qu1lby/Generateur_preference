/**
 * Classe Analyse, contient les méthodes pour le traitement des flux
 * et des Tables de films/séries
 * 
 * @author Kilian Cuny
 * @author Guillaume Haben
 * 
 * @version 1.0
 */

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

public abstract class Analyse {
	
	public static int charger(Videotheque v, String fichier){
		try {
			if(fichier == null) return -1;
			
			BufferedReader fr = new BufferedReader(new FileReader(fichier));
			String ligne = fr.readLine();
			
			do{
				// Echappe d'eventuelles lignes vides en début de fichier
				while(ligne != null && (ligne.compareTo("")==0)){
					ligne = fr.readLine();
				}
				
				// Ligne du Titre				
				boolean serie = ligne.contains("Series");
	
				String[] decoupage = ligne.split(" ", 2);
				decoupage = decoupage[1].split("\\(", 2);
				
				String titre = decoupage[0].substring(1, decoupage[0].length()-1);
				titre = new String(titre.getBytes(),Charset.forName("UTF-8"));
				decoupage = decoupage[1].split("\\)", 2);
				
				int annee = -1;
				if(serie) annee = Integer.parseInt(decoupage[0].substring(0,4));
				else{
					annee = Integer.parseInt(decoupage[0]);
				}
				
				ligne = fr.readLine();
				
				// Synopsis
				String synopsis = "", director = "";

				if(!serie){
					while (true){
						if(ligne.length()>=8){
							if(ligne.substring(0, 8).compareTo("Director")!= 0){
								synopsis+= ligne;
								ligne = fr.readLine();
							}else break;
						}else ligne = fr.readLine();
					}
					
					decoupage = ligne.split(":");
					director = decoupage[1].substring(1, decoupage[1].length()-1);
					ligne = fr.readLine();
					
				}else{
					while (true){
						if(ligne.length()>=8){
							if(ligne.substring(0, 4).compareTo("With")!= 0){
								synopsis+= ligne;
								ligne = fr.readLine();
							}else break;
						}else ligne = fr.readLine();
					}
				}
				
				// Acteurs
				decoupage = ligne.split(":");
				decoupage = decoupage[1].split(",");
				
				// Dernier nom contient un espace
				decoupage[decoupage.length-1] = decoupage[decoupage.length-1].substring(0,
						decoupage[decoupage.length-1].length()-1);
				
				ArrayList<String> acteurs = new ArrayList<String>();
				for(String nom : decoupage){
					nom = nom.substring(1);
					acteurs.add(nom);
				}
				
				// Genre
				ligne = fr.readLine();
				decoupage = ligne.split(" ");
				ArrayList<String> genres = new ArrayList<String>();
				int duree = -1;
				
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
				Ressource r = new Ressource(titre, annee, synopsis, acteurs, genres, duree, type, director);
				
				// Obtention de la clé de Hashage
				int cle = Analyse.Hashage(titre.charAt(0));
				v.ajouter(cle, r, type);
				
				// Echappe d'eventuelles lignes vides
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
		} catch (Exception e) {
			System.out.print("Please, report this message : ");
			e.printStackTrace();
			return -1;
		}
		return 0;	
	}
	
	/**
	 * Fonction static de Hashage
	 * @param c : Caractère à analyser
	 * @return place dans l'alphabet du caractère
	 */
	public static int Hashage(char c){
		int place = (int)c;
		if(place<=90 & place>=65){
			return (place-65);
		}
		return 26;
	}
}
