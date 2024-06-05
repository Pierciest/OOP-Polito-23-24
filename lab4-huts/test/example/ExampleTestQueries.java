package example;
import mountainhuts.*;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

public class ExampleTestQueries {

	private Region r;
	
	@Before
	public void setUp() {
		r = Region.fromFile("Piemonte", "data/mountain_huts.csv");
		assertNotNull("Cannot set up region from file", r);
		r.setAltitudeRanges("0-1000", "1000-2000", "2000-3000");
	}
	
	@Test
	public void testR4() {

		Map<String, Long> res1 = r.countMunicipalitiesPerProvince();
		assertNotNull("Missing count of municipalities per province", res1);
		assertEquals("Wrong number of municipalities in province of Torino", Long.valueOf(24), res1.get("TORINO"));

		Map<String, Map<String, Long>> res2 = r.countMountainHutsPerMunicipalityPerProvince();
		assertNotNull("Missing count of mountain huts per municipality per province", res2);
		Map<String, Long> resTo = res2.get("TORINO");
		assertNotNull("Missing count of mountain huts in province of Torino", resTo);

		Map<String, Long> res3 = r.countMountainHutsPerAltitudeRange();
		assertNotNull("Missing count of mountain huts per altitude range", res3);
		assertEquals("Wrong number of mountain huts in altitude range 0-1000", Long.valueOf(22), res3.get("0-1000"));

		Map<String, Integer> res4 = r.totalBedsNumberPerProvince();
		assertNotNull("Missing total beds number per province", res4);
		assertEquals("Wrong number of beds number in province of Torino", Integer.valueOf(953), res4.get("TORINO"));

		Map<String, Optional<Integer>> res5 = r.maximumBedsNumberPerAltitudeRange();
		assertNotNull("Missing maximum beds number per altitude range", res5);
		assertEquals("Wrong number of maximum beds number in altitude range 0-1000", Integer.valueOf(27),
				res5.get("0-1000").orElse(-1));

		Map<Long, List<String>> res6 = r.municipalityNamesPerCountOfMountainHuts();
		assertNotNull("Missing set of municipality names per count of mountain huts", res6);
		assertEquals("Wrong number of municipalities per count 1", 61, res6.get(1L).size());

	}

}
