
public class Analyse {

	
	
	/**
	 * Fonction de Hashage
	 * 
	 * @param c : Caract�re � analyser
	 * @return place dans l'alphabet de c
	 */
	public int Hashage(char c){
		int place = (int)c;
		if(place<=122 & place>=97){
			return (place-96);
		}
		return 27;
	}
	
}