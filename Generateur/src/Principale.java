import java.io.File;
import java.util.Scanner;

public class Principale {

	public static void main(String[] args) {

		/* ---------- Cr�ation de la vid�oth�que ---------- */

		Videotheque ma_videotheque = new Videotheque();

		/* --------------------- Menu --------------------- */

		Scanner scan = new Scanner(System.in);

		System.out.println("Choisissez une op�ration a effectuer:");
		System.out.println("[1] Cr�er une Biblioth�que");
		System.out.println("[2] Charger une Biblioth�que d�j� existante (format .bi)");
		System.out.println("[3] Quitter");
		System.out.print("Selection: ");

		int choix = scan.nextInt();
		boolean fin = false;
		int code_retour;
		do {
			switch (choix) {
			case 1:
				code_retour = ma_videotheque.creer("films.txt");
				if(code_retour != -1){	
				// Sauvegarde automatique de la Vid�oth�que
					ma_videotheque.sauvegarder("myBibli.bi");
					fin = true;
					break;
				}else{
					System.out.println("Impossible de charger le fichier d'initialisation\n"
							+ "Le fichier est corrompu ou inexistant");
					System.exit(0);
				}break;

			case 2:
				// L'utilisateur renseigne le nom du fichier .bi existant
				System.out.print("Nom du fichier .bi : ");
				scan.nextLine();
				String fichier = scan.nextLine();

				// On associe l'entr�e � un nouvel objet de type File
				File filename = new File(fichier);

				// On v�rifie qu'il a la bonne extension
				if (!fichier.endsWith(".bi")) {
					System.out.println("Le fichier renseign� n'est pas valide");
				}
				// On v�rifie que le fichier existe r�ellement
				else if (!filename.exists()) {
					System.out.println("Le fichier renseign� n'existe pas");
				}else {
					code_retour = ma_videotheque.charger(fichier);
					if(code_retour == 0){
						fin = true;
					}else {
						System.out.println("Impossible de charger la Vid�oth�que\n"
								+ "Le fichier est corrompu ou inexistant");
						System.exit(0);
					}
				}
				break;

			case 3:
				System.exit(0);

			default:
				System.out.print("Entrez une s�lection valide : ");
				choix = scan.nextInt();
				break;
			};
		} while (!fin);
		
		/* ----------------- Sous Menu ----------------- */

		while(true){
			System.out.println("\nChoisissez une op�ration a effectuer:");
			System.out.println("[1] Afficher la Vid�oth�que");
			System.out.println("[2] Recherche");
			System.out.println("[3] Recommandation"); // Globale / Rapport � un film
			System.out.println("[4] Reinitialiser");
			System.out.println("[5] Quitter");
			System.out.print("Selection: ");
	
			choix = scan.nextInt();
			fin = false;
			int ss_choix;
			do {
				switch (choix) {
				case 1:
					System.out.println("Choix 1");
					break;
					/**
					 * Pour l'affichage des films ne lance pas la toString 
					 * get le Titre simplement et affiche tous les titres des films puis Serie
					 * T'emb�te pas a trier on se garde �a pour plus tard si on a le temps
					 * de tout maniere avec l'IG pas mal de choses changeront
					 */
				case 2:
					System.out.print("Rentrez le titre du film a rechercher : ");
					scan.nextLine();
					String nom = scan.nextLine();
					System.out.println(ma_videotheque.recherche(nom));
					
					do{
						System.out.println("\n[1] Effectuer une autre recherche ");
						System.out.println("[2] Retour ");
						System.out.print("Selection: ");
						ss_choix = scan.nextInt();
					
						if(ss_choix == 2) fin = true;
					}while(ss_choix>2 || ss_choix<1);
					
					break;
					
				case 3:
					break;
					
				case 4:
					System.out.println("\nEtes vous sur ?");
					System.out.println("[1] Confirmer la r�initialisation ");
					System.out.println("[2] Annuler ");
					System.out.print("Selection: ");
					ss_choix = scan.nextInt();
					
					break;
					
				case 5:
					scan.close();
					System.exit(0);
					break;
	
				default:
					System.out.println("Entrez une s�lection valide");
					choix = scan.nextInt();
					break;
				};
			} while (!fin);
		}
	}
}