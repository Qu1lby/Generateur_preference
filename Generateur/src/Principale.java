import java.io.File;
import java.io.IOException;
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
		
		do {
			switch (choix) {
			case 1:
				int code_retour = ma_videotheque.creer("films.txt");
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
					ma_videotheque.charger(fichier);
					fin = true;
				}
				break;

			case 3:
				System.out.println("Sorti");
				System.exit(0);

			default:
				System.out.println("Entrez une s�lection valide");
			};
		} while (!fin);
		
		/* ----------------- Sous Menu ----------------- */

		System.out.println("\nChoisissez une op�ration a effectuer:");
		System.out.println("[1] Afficher les films");
		System.out.println("[2] Afficher les s�ries");
		System.out.println("[3] Rechercher un film");
		System.out.println("[4] R�initialiser");
		System.out.println("[5] Quitter");
		System.out.print("Selection: ");

		choix = scan.nextInt();
		boolean fin2 = false;
		do {
			switch (choix) {
			case 1:
				System.out.println("Choix 1");
				break;
				/**
				 * Pour l'affichage des films ne lance pas la toString 
				 * get le Titre simplement et affiche tous les titres des films
				 * T'emb�te pas a trier on se garde �a pour plus tard si on a le temps
				 * de tout maniere avec l'IG pas mal de choses changeront
				 */
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				System.out.println("Sorti");
				System.exit(0);

			default:
				System.out.println("Entrez une s�lection valide");
			};
		} while (!fin);
		
		scan.close();
	}
}