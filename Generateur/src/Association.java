/**
 * Classe Association, associe une note à deux ressources
 * @author Kilian Cuny
 * @author Guillaume Haben
 * @version 2.0
 */

import java.io.Serializable;

@SuppressWarnings("serial")
public class Association implements Serializable, Comparable<Association> {

	/** Les deux ressources associées */
	final private Ressource source, ressemblance;
	
	/** Note de similarité */
	final private double note;

	/**
	 * Constructeur d'Association
	 * @param source
	 * @param ressemblance
	 * @param note
	 */
	public Association(final Ressource source, final Ressource ressemblance, final double note) {
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
