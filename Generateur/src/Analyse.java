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
			BufferedReader fr = new BufferedReader(new FileReader("films.txt"));
			String fin = "";
			
			do{
				fin = fr.readLine();
				System.out.println(fin);
			}while (fin != null);
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
