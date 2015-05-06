import java.util.ArrayList;

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
	}
	
}
