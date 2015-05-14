/**
 * Classe Association, associe une note à deux ressources
 * @author Kilian Cuny
 * @author Guillaume Haben
 *
 * @verion 1.0
 */

public class Association implements Comparable<Association>{

	private Ressource source, ressemblance;
	private int note;
	
	/**
	 * Constructeur
	 */
	public Association(Ressource source, Ressource ressemblance, int note) {
		this.source = source;
		this.ressemblance = ressemblance;
		this.note = note;
	}

	/**
	 * Permet de comparer des ressources
	 * @param arg: Association
	 */
	public int compareTo(Association arg) {
		if(note == arg.getNote()) return 0;
		if(note > arg.getNote()) return 1;
		return -1;
	}
	
	public Ressource getSource() {
		return source;
	}

	public Ressource getRessemblance() {
		return ressemblance;
	}

	public int getNote() {
		return note;
	}	
}
