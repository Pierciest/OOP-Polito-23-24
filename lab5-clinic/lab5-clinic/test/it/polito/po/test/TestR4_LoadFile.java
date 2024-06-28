package it.polito.po.test;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedHashMap;

import clinic.*;

import static org.junit.Assert.*;
import org.junit.Test;



public class TestR4_LoadFile {

  @Test
  public void testLoading() throws NoSuchPatient, NoSuchDoctor, IOException{
  	String regular = "P;Giovanni;Rossi;RSSGNN33B30F316I\n" +
					"P;Giuseppe;Verdi;VRDGPP76F09B666I\n" + 
					"M;345;Mario;Bianchi;BNCMRA44C99A320Z;Surgeon";
  	
  	Clinic s = new Clinic();
  	int n = s.loadData(new StringReader(regular));
  	
  	assertEquals("Wrong number of lines",3,n);
  	
  	String p = s.getPatient("VRDGPP76F09B666I");
  	assertNotNull("Missing patient Verdi",p);
  	assertTrue("Wrong name for patient Verdi", p.contains("Verdi"));

	String d = s.getDoctor(345);
	assertNotNull("Missing doctor Bianchi",d);
	assertTrue("Wrong name for doctor Bianchi", d.contains("Bianchi"));
  }

  final static 	String trivialErrors = "P;Giovanni;Rossi;RSSGNN33B30F316I\n" +
			"P;Giuseppe;Verdi;VRDGPP76F09B666I\n" + 
			"P;Giuseppe;VRDGPP76F09B444I\n" + // missing last name 
			"M;Mario;Bianchi;BNCMRA44C99A320Y;Surgeon\n" + // missing id
			"M;345;Mario;Bianchi;BNCMRA44C99A320Z;Surgeon\n";

  @Test
  public void testTrivialErrors() throws IOException, NoSuchPatient, NoSuchDoctor{

  	Clinic s = new Clinic();
  	int n = s.loadData(new StringReader(trivialErrors));
  	assertEquals("Wrong number of lines",3,n);
  	
  	String p = s.getPatient("VRDGPP76F09B666I");
  	assertNotNull("Missing patient Verdi",p);
  	assertTrue("Wrong name for Verdi", p.contains("Verdi"));

	String d = s.getDoctor(345);
	assertNotNull("Missing doctor Bianchi",d);
	assertTrue("Wrong name for doctor Bianchi", d.contains("Bianchi"));
  }

  @Test
  public void testTrivialErrorsListener() throws IOException, NoSuchPatient, NoSuchDoctor{
	LinkedHashMap<Integer,String> offending=new LinkedHashMap<>();

  	Clinic s = new Clinic();
  	int n = s.loadData(new StringReader(trivialErrors), offending::put);
  	assertEquals("Wrong number of lines",3,n);
  	assertEquals("Wrong number of discarded lines",2,offending.size());
	assertTrue("Missing error on line 3", offending.containsKey(3));
	assertTrue("Missing error on line 4", offending.containsKey(4));
	assertEquals("Missing error on line 3", "M;Mario", offending.get(4).substring(0,7));
  	
  	String p = s.getPatient("VRDGPP76F09B666I");
  	assertNotNull("Missing patient Verdi",p);
  	assertTrue("Wrong name for Verdi", p.contains("Verdi"));

	String d = s.getDoctor(345);
	assertNotNull("Missing doctor Bianchi",d);
	assertTrue("Wrong name for doctor Bianchi", d.contains("Bianchi"));
  }


  final static String extrBlanks = "P;Giovanni;Rossi;RSSGNN33B30F316I\n" +
			"P;Giuseppe; Verdi ; VRDGPP76F09B666I \n" + // added spaces
			"M;345;Mario;Bianchi;BNCMRA44C99A320Z;Surgeon\n";

  @Test
  public void testSpecialErrorsExtraBlanks() throws IOException, NoSuchPatient {

	  	Clinic s = new Clinic();
	  	s.loadData(new StringReader(extrBlanks));
  		String p = s.getPatient("VRDGPP76F09B666I");
  	  	assertNotNull("Missing patient Verdi",p);
  	  	assertTrue("Verdi is missing", p.contains("Verdi"));
  }

  final static String emptyLine = "P;Giovanni;Rossi;RSSGNN33B30F316I\n" +
			"P;Giuseppe; Verdi;VRDGPP76F09B666I\n" + 
			"\n" + // empty line 
			"M;345;Mario;Bianchi;BNCMRA44C99A320Z;Surgeon\n";

  @Test
  public void testSpecialErrorsEmptyLine() throws IOException, NoSuchDoctor {
	LinkedHashMap<Integer,String> offending=new LinkedHashMap<>();

	  	Clinic s = new Clinic();
	  	int n = s.loadData(new StringReader(emptyLine),offending::put);
	  	assertEquals("Wrong number of lines",3,n);
	  	assertEquals("Wrong discarded line" ,"",offending.get(3));
	  	
  		String d = s.getDoctor(345);
  		assertNotNull("Missing doctor Bianchi",d);
  		assertTrue("Wrong name doctor Bianchi", d.contains("Bianchi"));
  }

	final static 	String subtleErrors = "P;Giovanni;Rossi;RSSGNN33B30F316I\n" +
			"P;Giuseppe;Verdi;VRDGPP76F09B666I\n" +
			"M;B45;Mario;Bianchi;BNCMRA44C99A320Z;Surgeon\n"+
			"M;987;Francesco;Neri;NRIFNC76D18F215Q;Pedriatist\n"
			;
	@Test
	public void testSubtleErrorsIdNotInt() throws IOException {
		LinkedHashMap<Integer,String> offending=new LinkedHashMap<>();

		Clinic s = new Clinic();
		int n = s.loadData(new StringReader(subtleErrors),offending::put);
		assertEquals("Wrong number of lines",3,n);
		assertEquals("Detected file problems not confirmed", 1, offending.size());
		String line = offending.get(3);
		assertTrue("Non numeric doctor ID not detected", line.contains("B45"));
	}
}
