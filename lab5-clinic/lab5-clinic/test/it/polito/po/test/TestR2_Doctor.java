package it.polito.po.test;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import clinic.*;

public class TestR2_Doctor {
	// "<Last> <First> (<SSN>) [<ID>]: <Specialization>"
	static Pattern doctorFormat = Pattern.compile(
			"\\s*([a-zA-Z',-]+)\\s*([a-zA-Z',-]+)\\s*\\(\\s*(\\w{16})\\s*\\)\\s*\\[(\\d+)\\]\\s*:\\s*(\\w+)\\s*");

	private Clinic s;
	private final static int id = 123;
	private final static String ssn = "THEPID12I99F181K";
	private final static String name = "Giova";
	private final static String surname = "Reds";
	private final static String specialization = "Surgeon";

    @Before
	public void setUp() {
	    s = new Clinic();

		s.addDoctor(name, surname, ssn,id, specialization);
	}
    
  @Test
  public void testAdd() throws NoSuchDoctor {
    String d = s.getDoctor(id);
    
    assertNotNull("Missing doctor with id " + id, d);
    
    Matcher m = doctorFormat.matcher(d);
    assertTrue("Wrong format for doctor info: " + d,m.matches());

    assertEquals("Wrong Name in doctor info",name, m.group(2));
    assertEquals("Wrong Last name in doctor info",surname, m.group(1));
    assertEquals("Wrong Id in doctor info",(""+id), m.group(4));
    assertEquals("Wrong specialization in doctor info",specialization, m.group(5));
  }

  @Test
  public void testDoctorIsAlsoPatient() throws NoSuchPatient {
	String p = s.getPatient(ssn);
	
	assertNotNull("Doctor "+surname+" should be lister among the patients too",p);
	assertTrue("Wrong name in doctor "+surname+" info", p.contains(name));

  }

  @Test
  public void testDoctorNotFound() {
    assertThrows( "Expected exception for invalid doctor id", NoSuchDoctor.class, 
    		     ()->s.getDoctor(id + 1000) );
  }

}
