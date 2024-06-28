package it.polito.po.test;

import clinic.*;
import java.util.Collection;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestR3_AssignPatient {
	
	private Clinic s;
	private final static String ssn = "THEPID12I99F181K";
	private final static String ssn2 = "BLKSRS11I88F981K";
	private final static String ssn3 = "RDSGSI12I97F181K";
	private final static int id = 124;
	private final static int id2 = 321;

	@Before
	public void setUp() {
		s = new Clinic();

		s.addPatient("Giova", "Reds", ssn);
		s.addPatient("Sirius", "Black", ssn2);
		s.addPatient("Giusy", "Reds", ssn3);
		s.addDoctor("Mario", "White", "THEFIT12I97F181Z", id, "Surgeon");
		s.addDoctor("Joe", "Nero", "JEONRO33K99X098Q", id2, "Dentist");
	}
	

	@Test
	public void testAssignDoctor() throws NoSuchPatient, NoSuchDoctor {
		s.assignPatientToDoctor(ssn, id);

		int res = s.getAssignedDoctor(ssn);

		assertEquals("Wrong assigned doctor id.", id, res);
	}

	@Test
	public void testAssignDoctorMultiple() throws NoSuchPatient, NoSuchDoctor {
		s.assignPatientToDoctor(ssn, id);

		int doc1 = s.getAssignedDoctor(ssn);

		s.assignPatientToDoctor(ssn, id2);

		int doc2 = s.getAssignedDoctor(ssn);

		assertNotEquals("Doctor should be replaced", doc1, doc2);
		assertEquals("Wrong assigned doctor id.", id2, doc2);
	}

	@Test
	public void testAssignDoctorNoPatient() {
		assertThrows( "Expected exception for non existing patient ssn", NoSuchPatient.class,
					  ()->s.assignPatientToDoctor("NonExisting", id) );
	}
	
	@Test
	public void testAssignDoctorNoDoctor() {
		assertThrows( "Expected exception for non existing doctor id", NoSuchDoctor.class,
		             ()->s.assignPatientToDoctor(ssn, id+1) );
	}


	@Test
	public void testNoAssignedDoctor() {
		assertThrows( "Expected exception from getAssignedDoctor() when no doctor assigned to patient", NoSuchDoctor.class,
					  ()->s.getAssignedDoctor(ssn) );
	}

	@Test
	public void testAssignedDoctorNoPatient() {
		assertThrows( "Expected exception from getAssignedDoctor() for non existent patient ssn", NoSuchPatient.class,
				  ()->s.getAssignedDoctor(ssn+"X") );
	}

	@Test
	public void testList() throws NoSuchPatient, NoSuchDoctor {
		s.assignPatientToDoctor(ssn, id);
		s.assignPatientToDoctor(ssn2, id);
		s.assignPatientToDoctor(ssn3, id);

		Collection<String> patients = s.getAssignedPatients(id);

		assertNotNull("Missing list of patients assigned to doctor White.", patients);
		assertEquals("Patient list should contain two patients.",3, patients.size());
		assertTrue(patients.contains(ssn));
		assertTrue(patients.contains(ssn2));
	}

	@Test
	public void testNoList() throws NoSuchDoctor {

		Collection<String> patients = s.getAssignedPatients(id);

		assertNotNull("Missing list of patients assigned to doctor White.", patients);
		assertEquals("Patient list should be empty.",0, patients.size());
	}

	@Test
	public void testListNoDoctor() throws NoSuchPatient, NoSuchDoctor {
		s.assignPatientToDoctor(ssn, id);
		s.assignPatientToDoctor(ssn2, id);

		assertThrows( "Expected exception from getAssignedPatients() for non existent doctor id", NoSuchDoctor.class,
				  ()->s.getAssignedPatients(id+1000) );
	}

}
