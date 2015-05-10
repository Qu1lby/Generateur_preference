/**
 * Cette classe Junit teste la classe Ressource 
 * 
 * @author Guillaume Haben
 * @author Kilian Cuny
 * 
 * @version 1.0
 */

import static org.junit.Assert.*;

import org.junit.Test;


public class RessourceTest {

	public Ressource r_test;
	
	public RessourceTest(){
		r_test = new Ressource("titre", 1000, "synopsis", null, null, 1000, "Film", "realisateur");
	}
	
	@Test
	// Différent test de constructeur dans la même methode de Test
	public void testRessource() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetNote() {
		assertEquals(-1, r_test.getNote());
		
		r_test.setNote(5); // 0<note<10
		assertEquals(5, r_test.getNote());
		
		r_test.setNote(11); // note>10
		assertEquals(10, r_test.getNote());
		
		r_test.setNote(-1); // note<0
		assertEquals(-1, r_test.getNote());
		
		r_test.setNote(10); // note=10
		assertEquals(10, r_test.getNote());
	
		r_test.setNote(0); // note=0
		assertEquals(0, r_test.getNote());

	}

	@Test
	public void testSetVu() {
		fail("Not yet implemented");
	}

	@Test
	// Avec un Film
	public void testToString1() {
		fail("Not yet implemented");
	}
	
	@Test
	// Avec une Série
	public void testToString2() {
		fail("Not yet implemented");
	}
	
	@Test
	// Avec un type indéfini
	public void testToString3() {
		fail("Not yet implemented");
	}
}
