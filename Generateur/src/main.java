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

		boolean fin = false;
		do {
			switch (choix) {
			case 1:
				ma_videotheque.creer("films.txt");
				break;

			case 2:
				// L'utilisateur renseigne le nom du fichier .bi existant
				Scanner scan2 = new Scanner(System.in);
				System.out.print("Nom du fichier .bi : ");
				String fichier = scan2.nextLine();

				// On associe l'entrée de l'utilisateur à un nouvel objet de
				// type File
				File filename = new File(fichier);

				// On vérifie qu'il a la bonne extension
				if (!fichier.endsWith(".bi")) {
					System.out.println("Le fichier renseigné n'est pas valide");
				}
				// On vérifie que le fichier existe réellement
				else if (!filename.exists()) {
					System.out.println("Le fichier renseigné n'existe pas");
				} else {
					/*
					 * try { ma_videotheque.charger(fichier); } catch
					 * (ClassNotFoundException | IOException e) {
					 * e.printStackTrace(); }
					 */
					fin = true;
				}
				break;

			case 3:
				System.out.println("Sorti");
				System.exit(0);

			default:
				System.out.println("Entrez une sélection valide");
			};
		} while (!fin);
	}
}