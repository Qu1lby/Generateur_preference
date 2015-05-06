import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {

		/* ---------- Création de la vidéothèque ---------- */
		
		Videotheque ma_videotheque = new Videotheque();
		
		/* --------------------- Menu --------------------- */
		
		Scanner scan1 = new Scanner(System.in);
		
		System.out.println("Choisissez une opération a effectuer:"); 
        System.out.println("[1] Créer une Bibliothèque (format .txt)"); 
        System.out.println("[2] Charger une Bibliothèque déjà existante (format .bi)"); 
        System.out.println("[3] Quitter"); 
        System.out.print("Selection: "); 
        
        int choix = scan1.nextInt();     
       
       switch (choix){
       		case 1:
       			ma_videotheque.creer("films.txt");
       			break;
             
       		case 2:
       			Scanner scan2 = new Scanner(System.in);
       	        System.out.print("Nom du fichier .bi : "); 
       	        String fichier = scan2.nextLine();     
       	        
				try {
					ma_videotheque.charger(fichier);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

       			break;
             
       		case 3:System.out.println("Sorti");
                System.exit(0);
                        
       		default:System.out.println("Entrez une sélection valide");
           
       };
		
	}
	
}