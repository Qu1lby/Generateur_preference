/**
 * Cette classe Junit teste la classe Videotheque 
 * 
 * @author Guillaume Haben
 * @author Kilian Cuny
 * 
 * @version 1.0
 */


import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;


public class VideothequeTest {

	public Videotheque v_test;
	
	public VideothequeTest(){
		v_test = new Videotheque();
	}
	
	 // Permet également de tester reinitialiser puisque
	 // le constructeur fait appel uniquement à cette methode
	@Test
	public void testVideotheque() {
		v_test = new Videotheque();
		assertEquals(27, v_test.tab_film.size());
		assertEquals(27, v_test.tab_serie.size());
	}

	@Test
	public void testSetTab_film() {
		HashMap<Integer, ArrayList<Ressource>> table;
		table = new HashMap<Integer, ArrayList<Ressource>>();
		
		v_test.setTab_film(table);
		assertEquals(table, v_test.tab_film);
		
		v_test.setTab_film(null);
		assertEquals(null, v_test.tab_film);
	}

	@Test
	public void testSetTab_serie() {
		HashMap<Integer, ArrayList<Ressource>> table;
		table = new HashMap<Integer, ArrayList<Ressource>>();
		
		v_test.setTab_serie(table);
		assertEquals(table, v_test.tab_serie);
		
		v_test.setTab_serie(null);
		assertEquals(null, v_test.tab_serie);
	}

	@Test
	public void testCreer() {
		assertEquals(-1, v_test.creer(null));
		assertEquals(-1, v_test.creer("inexistant.txt"));
		assertEquals(0, v_test.creer("films.txt"));
	}

	@Test
	public void testAjouter() {
		fail("Not yet implemented");
	}

	@Test
	public void testRecherche() {
		fail("Not yet implemented");
	}

	@Test
	public void testSauvegarder() {
		fail("Not yet implemented");
	}

	@Test
	public void testCharger() {
		fail("Not yet implemented");
	}

}
