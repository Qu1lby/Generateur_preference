/**
 * Cette classe Junit teste la classe Association 
 * 
 * @author Guillaume Haben
 * @author Kilian Cuny
 * @version 1.0
 */

import static org.junit.Assert.*;
import org.junit.Test;

public class AssociationTest {

	@Test
	public void testCompareTo() {
		Association a = new Association(null, null, 5);
		Association b = new Association(null, null, 7);
		Association c = new Association(null, null, 3);

		assertEquals(1, a.compareTo(b));
		assertEquals(-1, a.compareTo(c));
		assertEquals(0, a.compareTo(a));
	}
}
