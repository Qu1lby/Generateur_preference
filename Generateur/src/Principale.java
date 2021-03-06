/**
 * Classe Principale, application console
 * @author Guillaume Haben
 * @author Kilian Cuny
 * @version 1.0
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;

public class Principale {

	public static void main(String[] args) {

		/* ---------- Cr�ation de la vid�oth�que ---------- */

		Videotheque ma_videotheque = new Videotheque();
		Recommandation mes_recommandations = new Recommandation();

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
				else {
					if (!filename.exists()) {
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
			System.out.println("[1] Afficher les films de la Vid�oth�ques");
			System.out.println("[2] Afficher les s�ries de la Vid�oth�ques");
			System.out.println("[3] Recherche");
			System.out.println("[4] Recommandation");
			System.out.println("[5] Reinitialiser");
			System.out.println("[6] Quitter");
			System.out.print("Selection: ");
	
			choix = scan.nextInt();
			fin = false;
			int ss_choix;
			do {
				switch (choix) {
				case 1:
					//Pour chaque entr�e du HashMap
					for (Entry<Integer, ArrayList<Ressource>> entry : ma_videotheque.getTab_film().entrySet()) {
						//Si la valeur de l'entr�e n'est pas nulle (si des films existent pour la lettre actuelle)
						if (!entry.getValue().isEmpty()) {
							//On affiche la lettre correspondante au HashMap
							System.out.println("\n--------- " + String.valueOf((char)(entry.getKey() + 65)) + " ---------");
							//On affiche les entr�es (films) correspondant � la lettre
							for (Ressource R : ma_videotheque.getTab_film().get(entry.getKey())) {
								System.out.println(R.getTitre());
							}
						}
					}
					fin = true;
					break;
				case 2:
					//Pour chaque entr�e du HashMap
					for (Entry<Integer, ArrayList<Ressource>> entry : ma_videotheque.getTab_serie().entrySet()) {
						//Si la valeur de l'entr�e n'est pas nulle (si des s�ries existent pour la lettre actuelle)
						if (!entry.getValue().isEmpty()) {
							//On affiche la lettre correspondante au HashMap
							System.out.println("\n--------- " + String.valueOf((char)(entry.getKey() + 65)) + " ---------");
							//On affiche les entr�es (s�ries) correspondant � la lettre
							for (Ressource R : ma_videotheque.getTab_serie().get(entry.getKey())) {
								System.out.println(R.getTitre());
							}
						}
					}
					fin = true;
					break;
				case 3:
					System.out.print("\nRentrez le titre de la Ressource � rechercher : ");
					scan.nextLine();
					String nom = scan.nextLine();
					Ressource r = ma_videotheque.recherche(nom);
					if(r!=null) System.out.println("\n"+r.toString());
					else System.out.println("\nRessource introuvable");
					
					do{
						System.out.println("\n[1] Effectuer une autre recherche ");
						System.out.println("[2] Film et S�rie similaires ");
						System.out.println("[3] J'ai visionn� ce film/cette s�rie ");
						System.out.println("[4] Modifier la note de ce film/cette s�rie ");
						System.out.println("[5] Retour ");
						System.out.print("Selection: ");
						ss_choix = scan.nextInt();
					
						if(ss_choix == 5) fin = true;
						if(ss_choix == 2){
							ArrayList<Association> asso = ma_videotheque.getSimilarite(nom);
							if(asso != null){
								for(Association a : asso){
									System.out.println(a);
								}
							}
							else System.out.println("Aucune association possible");
						}
						if(ss_choix == 3) { //while � faire :(
							if(ma_videotheque.recherche(nom).isVu()) {
								System.out.print("Vous avez d�j� vu ce film");								
							}
							else {
								ma_videotheque.recherche(nom).setVu(true);
								ma_videotheque.sauvegarder("myBibli.bi");
								System.out.print("Ajouter une note [0-10]: ");
								int note = scan.nextInt();
								if (note < 0 || note > 10) {
									System.out.print("Note invalide");					
								}
								else {
									ma_videotheque.recherche(nom).setNote(note);
									ma_videotheque.sauvegarder("myBibli.bi");
								}
							}
							fin = true;
						}
						if(ss_choix == 4) { //Pareil, while � faire
							System.out.print("Ajouter une note [0-10]: ");
							int note = scan.nextInt();
							if (note < 0 || note > 10) {
								System.out.print("Note invalide");					
							}
							else {
								ma_videotheque.recherche(nom).setNote(note);
								ma_videotheque.sauvegarder("myBibli.bi");
							}
						}
					}while(ss_choix>5 || ss_choix<1);
					
					break;
					
				case 4:
					mes_recommandations.init(ma_videotheque);
					if(!mes_recommandations.getListeDesSimilaires().isEmpty()) {
						mes_recommandations.afficher();
						fin = true;
					}
					break;
					
				case 5:
					System.out.println("\nEtes vous sur ?");
					System.out.println("[1] Confirmer la r�initialisation ");
					System.out.println("[2] Annuler ");
					System.out.print("Selection: ");
					ss_choix = scan.nextInt();
					
					if(ss_choix == 1)
					if(ss_choix == 2) fin = true;
					
					break;
					
				case 6:
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