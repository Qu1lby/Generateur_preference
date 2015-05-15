/**
 * Cette classe Junit teste la classe Analyse 
 * 
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
		assertEquals(0, Analyse.Hashage('A'));
		assertEquals(9, Analyse.Hashage('J'));
		assertEquals(25, Analyse.Hashage('Z'));
		// Alphabet minuscule
		assertEquals(0, Analyse.Hashage('a'));
		assertEquals(9, Analyse.Hashage('j'));
		assertEquals(25, Analyse.Hashage('z'));
		// Caractère spéciaux
		assertEquals(26, Analyse.Hashage('é'));
		assertEquals(26, Analyse.Hashage(','));
	}

	// Test pour charger -> Dur à faire
	
}
