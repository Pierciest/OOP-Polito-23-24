package it.polito.po.test;

import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import mountainhuts.MountainHut;
import mountainhuts.Municipality;
import mountainhuts.Region;

import static org.junit.Assert.*;

public class TestR2_MountainHuts {

	private Region r;

	@Before
	public void setUp() {
		r = new Region("Piemonte");
	}

	@Test
	public void testGetMunicipalities() {
		Municipality m1 = r.createOrGetMunicipality("Torino", "TO", 245);
		Municipality m2 = r.createOrGetMunicipality("Cuneo", "CN", 534);

		Collection<Municipality> m = r.getMunicipalities();

		assertNotNull("Missing municipalities", m);
		assertEquals("Wrong number of municipalities", 2, m.size());
		assertTrue("Missing municipality Torino", m.contains(m1));
		assertTrue("Missing municipality Cuneo", m.contains(m2));
	}

	@Test
	public void testGetMountainHut() {
		Municipality m = r.createOrGetMunicipality("Torino", "TO", 245);
		MountainHut h1 = r.createOrGetMountainHut("Alpe", "Rifugio", 10, m);
		MountainHut h2 = r.createOrGetMountainHut("Tappa", "Bivacco", 0, m);

		Collection<MountainHut> h = r.getMountainHuts();

		assertNotNull("Missing mountain huts", h);
		assertEquals("Wrong number of mountain huts", 2, h.size());
		assertTrue("Missing mountain hut Alpe", h.contains(h1));
		assertTrue("Missing mountain hut Tappa", h.contains(h2));
	}

	@Test
	public void testCreateOrGetMunicipality() {
		Municipality m1 = r.createOrGetMunicipality("Torino", "TO", 245);
		Municipality m2 = r.createOrGetMunicipality("Torino", "TO", 245);

		assertNotNull("Missing municipality", m1);
		assertSame("Duplicate municipality", m1, m2);
		assertEquals("Wrong municipality name", "Torino", m1.getName());
		assertEquals("Wrong municipality province", "TO", m1.getProvince());
		assertEquals("Wrong municipality altitude", Integer.valueOf(245), m1.getAltitude());
	}

	@Test
	public void testCreateOrGetMountainHut() {
		Municipality m = r.createOrGetMunicipality("Torino", "TO", 245);
		MountainHut h1 = r.createOrGetMountainHut("Alpe", "Rifugio", 10, m);
		MountainHut h2 = r.createOrGetMountainHut("Alpe", "Rifugio", 10, m);

		assertNotNull("Missing mountain hut", h1);
		assertSame("Duplicate mountain hut", h1, h2);
		assertEquals("Wrong mountain hut name", "Alpe", h1.getName());
		assertEquals("Wrong mountain hut category", "Rifugio", h1.getCategory());
		assertEquals("Wrong mountain hut altitude", Optional.empty(), h1.getAltitude());
		assertEquals("Wrong mountain hut beds number", Integer.valueOf(10), h1.getBedsNumber());
		assertEquals("Wrong mountain municipality", m, h1.getMunicipality());

		MountainHut h3 = r.createOrGetMountainHut("Tappa", 1250, "Bivacco", 0, m);
		assertEquals("Wrong mountain hut altitude", Integer.valueOf(1250), h3.getAltitude().orElse(-1));
	}

}
