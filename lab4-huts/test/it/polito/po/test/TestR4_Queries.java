package it.polito.po.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static it.polito.po.test.CollectionsAssertions.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import mountainhuts.Region;

public class TestR4_Queries {

	private Region r;

	@Before
	public void setUp() {
		r = Region.fromFile("Piemonte", TestR3_ReadData.file);
	}
	
	@Test
	public void testCountMunicipalitiesPerProvince() {
		assertNotNull("Missing region", r);

		Map<String, Long> res = r.countMunicipalitiesPerProvince();
		
		assertNotNull("Missing count of municipalities per province", res);

		assertMapContains("Wrong number of municipalities",res,
							entry("ALESSANDRIA",1L),
							entry("CUNEO", 25L),
							entry("TORINO", 24L),
							entry("VERCELLI", 13L),
							entry("BIELLA", 12L),
							entry("VERBANO-CUSIO-OSSOLA",19L)
							);
	}
	
	@Test
	public void testCountMountainHutsPerMunicipalityPerProvince() {
		assertNotNull("Missing region", r);

		Map<String, Map<String, Long>> res = r.countMountainHutsPerMunicipalityPerProvince();

		assertNotNull("Missing count of mountain huts per municipality per province", res);
		
		Map<String, Long> resTo = res.get("TORINO");
		assertNotNull("Missing count of mountain huts in province of Torino", resTo);
		assertEquals("Wrong number of municipalities in province of Torino", 24, resTo.size());
		assertNotNull("Missing count of mountain huts in municipality of Bussoleno", resTo.get("BUSSOLENO"));
		assertEquals("Wrong number of mountain huts in municipality of Bussoleno", Long.valueOf(2), resTo.get("BUSSOLENO"));
		
		Map<String, Long> resAl = res.get("ALESSANDRIA");
		assertNotNull("Missing count of mountain huts in province of Alessandria", resAl);
		assertEquals("Wrong number of municipalities in province of Alessandria", 1, resAl.size());
		assertNotNull("Missing count of mountain huts in municipality of Bosio", resAl.get("BOSIO"));
		assertEquals("Wrong number of mountain huts in municipality of Bosio", Long.valueOf(1), resAl.get("BOSIO"));
	}
	
	@Test
	public void testCountMountainHutsPerAltitudeRange() {
		assertNotNull("Missing region", r);

		r.setAltitudeRanges("0-1000", "1001-1500", "1501-2000");
		Map<String, Long> res = r.countMountainHutsPerAltitudeRange();
		
		assertNotNull("Missing count of mountain huts per altitude range", res);

		assertMapContains("Wrong number of mountain huts in altitude range ", res,
				entry("0-1000", 22L), 
				entry("1001-1500", 36L),  
				entry("1501-2000", 52L),  
				entry("0-INF", 57L));
	}
	
	@Test
	public void testTotalBedsNumberPerProvince() {
		assertNotNull("Missing region", r);

		Map<String, Integer> res = r.totalBedsNumberPerProvince();

		assertNotNull("Missing total beds number per province", res);
		
		assertMapContains("Wrong number of beds number in province ", res,
				entry("ALESSANDRIA",10),
				entry("CUNEO", 1046),
				entry("TORINO", 953),
				entry("VERCELLI", 534),
				entry("BIELLA", 237),
				entry("VERBANO-CUSIO-OSSOLA",852)
				);
	}
	
	@Test
	public void testMaximumBedsNumberPerAltitudeRange() {
		assertNotNull("Missing region", r);

		r.setAltitudeRanges("0-1000", "1001-1500", "1501-2000");
		Map<String, Optional<Integer>> res = r.maximumBedsNumberPerAltitudeRange();
		
		assertNotNull("Missing maximum beds number per altitude range", res);
		
		assertMapContains("Wrong number of maximum beds number in altitude range ", res,
				entry("0-1000", Optional.of(27)), 
				entry("1001-1500", Optional.of(89)),  
				entry("1501-2000", Optional.of(95)),  
				entry("0-INF", Optional.of(96)));
	}
	
	@Test
	public void testMunicipalityNamesPerCountOfMountainHuts() {
		assertNotNull("Missing region", r);

		Map<Long, List<String>> res = r.municipalityNamesPerCountOfMountainHuts();
		
		assertNotNull("Missing set of municipality names per count of mountain huts", res);
		
		assertMapContains("Wrong number of municipality names per count of mountain huts ", res,
				entry(1L, List::size, 61), 
				entry(2L, List::size, 15), 
				entry(3L, List::size, 11), 
				entry(4L, List::size, 3), 
				entry(5L, List::size, 2), 
				entry(10L, List::size, 1), 
				entry(11L, List::size, 1) 
		);


		assertMapContains("Wrong first municipality name per count of mountain huts ", res,
				entry(1L, l->l.get(0), "ANDORNO MICCA"),
				entry(1L, l->l.get(0), "ANDORNO MICCA"),
				entry(2L, l->l.get(0), "ARGENTERA"),
				entry(3L, l->l.get(0), "ACCEGLIO"),
				entry(4L, l->l.get(0), "BOGNANCO"),
				entry(5L, l->l.get(0), "ENTRACQUE"),
				entry(10L, l->l.get(0), "MACUGNAGA"),
				entry(11L, l->l.get(0), "ALAGNA VALSESIA")
		);
	}

}
