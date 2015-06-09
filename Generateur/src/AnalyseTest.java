/**
 * Cette classe Junit teste la classe Analyse 
 * @author Guillaume Haben
 * @author Kilian Cuny
 * @version 1.0
 */

import static org.junit.Assert.*;
import org.junit.Test;

public class AnalyseTest {

	@Test
	public void testHashage() {
		// Alphabet majuscule
		assertEquals(0, Analyse.hashage('A'));
		assertEquals(9, Analyse.hashage('J'));
		assertEquals(25, Analyse.hashage('Z'));
		// Alphabet minuscule
		assertEquals(0, Analyse.hashage('a'));
		assertEquals(9, Analyse.hashage('j'));
		assertEquals(25, Analyse.hashage('z'));
		// Caractère spéciaux
		assertEquals(26, Analyse.hashage('é'));
		assertEquals(26, Analyse.hashage(','));
	}

	// Test pour charger -> Pas vraiment réalisable

}
