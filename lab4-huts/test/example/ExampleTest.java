package example;
import mountainhuts.*;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

public class ExampleTest {

	@Test
	public void testR1() {
		Region r = new Region("Piemonte");
		
		assertEquals("Missing region name", "Piemonte", r.getName());
		assertEquals("Missing range name", "0-INF", r.getAltitudeRange(1));
		
		r.setAltitudeRanges("0-1000", "1000-2000", "2000-3000");
		assertEquals("Wrong value in range", "1000-2000", r.getAltitudeRange(1500));
	}

	@Test
	public void testR2() {
		Region r = new Region("Piemonte");
		
		Municipality acceglio = r.createOrGetMunicipality("Acceglio", "Cuneo", 1200);
		assertNotNull("Missing municipality", acceglio);
		assertEquals("Wrong municipality name", "Acceglio", acceglio.getName());
		assertEquals("Wrong municipality province", "Cuneo", acceglio.getProvince());

		r.createOrGetMunicipality("Bobbio Pellice", "Torino", 732);
		
		Collection<Municipality> ms = r.getMunicipalities();
		
		assertNotNull("Missing municipalities", ms);
		assertEquals("Wrong number of municipalities", 2, ms.size());
		
		MountainHut h = r.createOrGetMountainHut("Campo Base", 1660, "Rifugio Escursionistico",
										   32, acceglio);
		
		assertNotNull("Missing hut", h);
		assertEquals("Wrong hut name","Campo Base", h.getName());
		assertSame("Wrong hut municipality",acceglio, h.getMunicipality());

		Collection<MountainHut> hs = r.getMountainHuts();
		
		assertNotNull("Missing huts", hs);
		assertEquals("Wrong number of huts", 1, hs.size());
	}


	@Test
	public void testR3() {
		Region r = Region.fromFile("Piemonte", "data/mountain_huts.csv");

		assertNotNull("No region from file", r);

		Collection<Municipality> municipalities = r.getMunicipalities();
		assertNotNull("Missing municipalities", municipalities);
		assertEquals("Wrong number of municipalities", 94, municipalities.size());

		Collection<MountainHut> mountainHuts = r.getMountainHuts();
		assertNotNull("Missing mountain huts", mountainHuts);
		assertEquals("Wrong number of mountain huts", 167, mountainHuts.size());

	}

}
