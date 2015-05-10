/**
 * Cette classe Junit teste la classe Ressource 
 * 
 * @author Guillaume Haben
 * @author Kilian Cuny
 * 
 * @version 1.0
 */

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;


public class RessourceTest {

	public Ressource r_test;
	
	public RessourceTest(){
		r_test = new Ressource("titre", 1000, "synopsis", null, null, 1000, "Film", "realisateur");
	}
	
	@Test
	// Test constructeur
	public void testRessource1() {
		ArrayList<String> arr_test = new ArrayList<String>();
		r_test = new Ressource("titre", 1000, "synopsis", arr_test, arr_test, 1000, "Film", "realisateur");

		assertEquals("titre", r_test.getTitre());
		assertEquals(0, r_test.getAnnee());
		assertEquals("synopsis", r_test.getSynopsis());
		assertEquals(arr_test, r_test.getActeur());
		assertEquals(arr_test, r_test.getGenre());
		assertEquals(0, r_test.getDuree());
		assertEquals("Film", r_test.getType());
		assertEquals("realisateur", r_test.getRealisateur());
	}

	@Test
	// Test constructeur 2
	public void testRessource2() {
		ArrayList<String> arr_test = new ArrayList<String>();
		r_test = new Ressource("titre", 2015, null, arr_test, arr_test, 125, null, null);
		
		assertEquals("titre", r_test.getTitre());
		assertEquals(2015, r_test.getAnnee());
		assertEquals("", r_test.getSynopsis());
		assertEquals(arr_test, r_test.getActeur());
		assertEquals(arr_test, r_test.getGenre());
		assertEquals(125, r_test.getDuree());
		assertEquals("Film", r_test.getType());
		assertEquals("Inconnu", r_test.getRealisateur());
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
