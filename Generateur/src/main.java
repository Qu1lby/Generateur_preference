import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {

		/* ---------- Cr�ation de la vid�oth�que ---------- */

		Videotheque ma_videotheque = new Videotheque();

		/* --------------------- Menu --------------------- */

		Scanner scan1 = new Scanner(System.in);

		System.out.println("Choisissez une op�ration a effectuer:");
		System.out.println("[1] Cr�er une Biblioth�que");
		System.out.println("[2] Charger une Biblioth�que d�j� existante (format .bi)");
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

				// On associe l'entr�e de l'utilisateur � un nouvel objet de
				// type File
				File filename = new File(fichier);

				// On v�rifie qu'il a la bonne extension
				if (!fichier.endsWith(".bi")) {
					System.out.println("Le fichier renseign� n'est pas valide");
				}
				// On v�rifie que le fichier existe r�ellement
				else if (!filename.exists()) {
					System.out.println("Le fichier renseign� n'existe pas");
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
				System.out.println("Entrez une s�lection valide");
			};
		} while (!fin);
	}
}