import java.util.ArrayList;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		String titre = "Harry Potter";
		int annee = 2007;
		String synopsis = "Un gentil sorcier et un méchant sorcier";
		ArrayList<String> acteurs = new ArrayList<String>();
		acteurs.add("Daniel");
		acteurs.add("Rupert");
		acteurs.add("Emma");
		ArrayList<String> genres = new ArrayList<String>();
		genres.add("Fantastique");
		String type = "Film";
		String realisateur = "David Yates";
		int note = 10;
		boolean vu = true;
		
		Ressource HP = new Ressource(titre, annee, synopsis, acteurs, genres, type, realisateur, note, vu);
		System.out.println(HP.toString());
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Choisissez une opération a effectuer:"); 
        System.out.println("[1] Créer une Bibliothèque (format .txt)"); 
        System.out.println("[2] Charger une Bibliothèque déjà existante (format .bi"); 
        System.out.println("[3] Quitter"); 
        
        System.out.println("Selection: "); 
        int selection = sc.nextInt();     
       
       switch (selection){
             
           case 1:System.out.println("Bilbliothèque créée");
                break;
             
        
           case 2:System.out.println("Bilbliothèque chargée");
                break;
             
        
           case 3:System.out.println("Sorti");
                System.exit(0);
                        
           default:System.out.println("Entrez une sélection valide");
           
       };
		
	}
	
}
