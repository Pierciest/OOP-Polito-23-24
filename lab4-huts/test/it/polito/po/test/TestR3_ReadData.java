package it.polito.po.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static it.polito.po.test.CollectionsAssertions.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import mountainhuts.MountainHut;
import mountainhuts.Municipality;
import mountainhuts.Region;

public class TestR3_ReadData {

	static String file;
	static {
		File outFile;

		try {
			outFile = File.createTempFile("mountain_huts", ".csv");
			outFile.deleteOnExit();
			System.out.println("Extracting data file: " + outFile.getAbsolutePath());
			URL resource = TestR3_ReadData.class.getResource("mountain_huts.csv");
			System.out.println("from: " + resource.toString());
			try (InputStream in = TestR3_ReadData.class.getResourceAsStream("mountain_huts.csv"); 
				FileOutputStream out = new FileOutputStream(outFile)) {
				byte[] b = new byte[2048];
				int n;
				while ((n = in.read(b)) != -1) {
					out.write(b, 0, n);
				}
				file = outFile.getCanonicalPath();
			}
		} catch (IOException e) {
			file = null;
			System.err.println(e.getMessage());
		}
	}

	private Region r;

	@Before
	public void setUp() {
		assertNotNull("Could not create temporary file", file);
		r = Region.fromFile("Piemonte", file);
	}

	@Test
	public void testReadMunicipalities() {
		assertNotNull("Missing region", r);

		Collection<Municipality> municipalities = r.getMunicipalities();
		assertNotNull("Missing municipalities", municipalities);
		assertEquals("Wrong number of municipalities", 94, municipalities.size());

		Map<String, Long> provinces = municipalities.stream()
				.collect(Collectors.groupingBy(Municipality::getProvince, Collectors.counting()));
		
		assertMapContains("Wrong number of municipalities in province ", provinces,
				entry("ALESSANDRIA",1L),
				entry( "CUNEO",25L),
				entry( "TORINO", 24L),
				entry( "VERCELLI", 13L),
				entry("BIELLA", 12L),
				entry("VERBANO-CUSIO-OSSOLA",19L));
	}

	@Test
	public void testReadMountainHuts() {
		assertNotNull("Missing region", r);

		Collection<MountainHut> mountainHuts = r.getMountainHuts();
		assertNotNull("Missing mountain huts", mountainHuts);
		assertEquals("Wrong number of mountain huts", 167, mountainHuts.size());

		Map<String, Long> categories = mountainHuts.stream()
				.collect(Collectors.groupingBy(MountainHut::getCategory, Collectors.counting()));
		
		assertMapContains("Wrong number of huts per category ", categories,
				entry("Bivacco Fisso", 27L),
				entry("Rifugio Alpino", 91L),
				entry("Rifugio Escursionistico", 33L),
				entry("Rifugio non gestito", 16L)
				);
	}

}
