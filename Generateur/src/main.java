import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {

		/* ---------- Création de la vidéothèque ---------- */
		
		Videotheque ma_videotheque = new Videotheque();
		
		/* --------------------- Menu --------------------- */
		
		Scanner scan1 = new Scanner(System.in);
		
		System.out.println("Choisissez une opération a effectuer:"); 
        System.out.println("[1] Créer une Bibliothèque"); 
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
       	        
       	        String extension = fichier.substring(0, fichier.lastIndexOf('.'));
       	        File filename = new File(fichier);
       	        
       	        if (extension.compareTo("bi") != 0) {
       	        	System.out.println("Le fichier renseigné n'est pas valide");
       	        }
       	        else if (!filename.exists()) {
       	        	System.out.println("Le fichier renseigné n'existe pas");
       	        }
       	        else {
    				try {
    					ma_videotheque.charger(fichier);
    				} catch (ClassNotFoundException | IOException e) {
    					e.printStackTrace();
    				}
       	        }

       			break;
             
       		case 3:System.out.println("Sorti");
                System.exit(0);
                        
       		default:System.out.println("Entrez une sélection valide");
           
       };
	}
}