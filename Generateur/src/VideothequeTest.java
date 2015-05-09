import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;


public class VideothequeTest {

	public Videotheque v_test;
	
	@Test
	public void testVideotheque() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTab_film() {
		v_test = new Videotheque();
		
		HashMap<Integer, ArrayList<Ressource>> table;
		table = new HashMap<Integer, ArrayList<Ressource>>();
		
		v_test.setTab_film(table);
		assertEquals(table, v_test.tab_film);
		
		v_test.setTab_film(null);
		assertEquals(null, v_test.tab_film);
	}

	@Test
	public void testSetTab_serie() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreer() {
		fail("Not yet implemented");
	}

	@Test
	public void testReintialiser() {
		fail("Not yet implemented");
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
