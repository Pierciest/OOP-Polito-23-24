package it.polito.po.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mountainhuts.Region;

public class TestR1_AltitudeRanges {

	private Region r;

	@Before
	public void setUp() {
		r = new Region("Piemonte");
	}

	@Test
	public void testGetName() {
		assertNotNull("Missing region name", r.getName());
		assertEquals("Wrong region name", "Piemonte", r.getName());
	}

	@Test
	public void testGetAltitudeRangeDefault() {
		assertNotNull("Missing range name", r.getAltitudeRange(0));
		assertEquals("Wrong empty range name", "0-INF", r.getAltitudeRange(0));
	}

	@Test
	public void testGetAltitudeRange() {
		r.setAltitudeRanges("0-1000", "1000-2000", "2000-3000");
		assertEquals("Wrong left value in range", "1000-2000", r.getAltitudeRange(1001));
		assertEquals("Wrong right value in range", "1000-2000", r.getAltitudeRange(2000));
		assertEquals("Wrong middle value in range", "2000-3000", r.getAltitudeRange(2500));
		assertEquals("Wrong default range name", "0-INF", r.getAltitudeRange(3001));
	}

}
