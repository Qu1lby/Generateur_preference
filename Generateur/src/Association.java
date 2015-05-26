/**
 * Classe Association, associe une note à deux ressources
 * @author Kilian Cuny
 * @author Guillaume Haben
 * @version 2.0
 */

import java.io.Serializable;

@SuppressWarnings("serial")
public class Association implements Serializable, Comparable<Association> {

	private Ressource source, ressemblance;
	private double note;

	public Association(Ressource source, Ressource ressemblance, double note) {
		this.source = source;
		this.ressemblance = ressemblance;
		this.note = note;
	}

	/**
	 * Permet de comparer deux Associations
	 * @param arg: Association
	 * @return Résultat d'un compareTo classique
	 */
	public int compareTo(Association arg) {
		if (note == arg.getNote()) return 0;
		if (note > arg.getNote()) return -1;
		return 1;
	}

	// ******* GETTER *******//

	public Ressource getSource() {
		return source;
	}

	public Ressource getRessemblance() {
		return ressemblance;
	}

	public double getNote() {
		return note;
	}

	public String toString() {
		return "\n" + ressemblance.getTitre() + " : " + note;
	}
}
