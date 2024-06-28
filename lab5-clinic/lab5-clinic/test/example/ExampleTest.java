package example;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

import org.junit.Test;

import clinic.Clinic;
import clinic.NoSuchDoctor;
import clinic.NoSuchPatient;

public class ExampleTest {

	@Test
	public void testR1() throws NoSuchPatient, NoSuchDoctor, FileNotFoundException, IOException {
		Clinic clinic = new Clinic();

		clinic.addPatient("Alice", "Green", "ALCGRN");
		String alice = clinic.getPatient("ALCGRN");
		assertNotNull("Missing patient",alice);
		assertTrue("Wrong patient format for ALCGRN.",alice.matches("Green\\s+Alice\\s+\\(\\s*ALCGRN\\s*\\)"));
	}

	@Test
	public void testR2() throws NoSuchPatient, NoSuchDoctor, FileNotFoundException, IOException {
		Clinic clinic = new Clinic();

		clinic.addDoctor("Kate", "Love", "LVOKTA",86,"Physician");

		String kate = clinic.getDoctor(86);
		assertNotNull("Missing doctor",kate);
		assertTrue("Missing doctor's id",kate.contains("86"));
		assertTrue("Missing doctor's specialization",kate.contains("Physician"));
	}

	@Test
	public void testR3() throws NoSuchPatient, NoSuchDoctor, FileNotFoundException, IOException {
		Clinic clinic = new Clinic();

		clinic.addPatient("Alice", "Green", "ALCGRN");
		clinic.addPatient("Robert", "Smith", "RBTSMT");
		clinic.addPatient("Steve", "Moore", "STVMRE");
		clinic.addPatient("Susan", "Madison", "SNSMDS");
		
		
		clinic.addDoctor("George", "Sun","SNUGRG", 14,"Physician");
		clinic.addDoctor("Kate", "Love", "LVOKTA",86,"Physician");
		
		clinic.assignPatientToDoctor("SNSMDS", 86);
		clinic.assignPatientToDoctor("ALCGRN", 14);
		clinic.assignPatientToDoctor("RBTSMT", 14);
		clinic.assignPatientToDoctor("STVMRE", 14);
		
		int susanDoc = clinic.getAssignedDoctor("SNSMDS");
		assertEquals("Wrong doctor for Susan",86,susanDoc);

		Collection<String> patients = clinic.getAssignedPatients(14);

		assertNotNull("Missing George's patients",patients);
		
		assertThrows("Invalid doctor id should raise an exception",
		   NoSuchDoctor.class,
				    ()-> clinic.getAssignedPatients(-1));

	}
	
	@Test
	public void testR4() throws NoSuchPatient, NoSuchDoctor, FileNotFoundException, IOException {
		Clinic clinic = new Clinic();

		int n = clinic.loadData(new FileReader("data/data.txt"));
		assertEquals("Wrong number of lines",3,n);
		
		String gio = clinic.getPatient("RSSGNN33B30F316I");
		assertNotNull("Patient not read from file", gio);

		String mario = clinic.getDoctor(345);
		assertNotNull("Doctor not read from file", mario);
	}


	@Test
	public void testR5() throws NoSuchPatient, NoSuchDoctor, FileNotFoundException, IOException {
		Clinic clinic = new Clinic();

		clinic.addPatient("Alice", "Green", "ALCGRN");
		clinic.addPatient("Robert", "Smith", "RBTSMT");
		clinic.addPatient("Steve", "Moore", "STVMRE");
		clinic.addPatient("Susan", "Madison", "SSNMDS");
		
		clinic.addDoctor("George", "Sun","SNUGRG", 14,"Physician");
		clinic.addDoctor("Kate", "Love", "LVOKTA",86,"Dentist");
		clinic.addDoctor("Marie", "Curie", "MRICRU",88,"Chemist");
		
		clinic.assignPatientToDoctor("SSNMDS", 86);
		clinic.assignPatientToDoctor("ALCGRN", 14);
		clinic.assignPatientToDoctor("RBTSMT", 14);
		clinic.assignPatientToDoctor("STVMRE", 14);

		Collection<Integer> busy = clinic.busyDoctors();
		
		assertNotNull("Missing busy doctors",busy);
		assertEquals("Too many busy doctors detected",1,busy.size());
		assertTrue("Missing busy doctor",busy.contains(14));
		
		Collection<String> dbp = clinic.doctorsByNumPatients();
		assertNotNull("Missing doctors by num patients", dbp);
		assertEquals("Wrong number of doctors per num of patients", 3,dbp.size());

		Collection<String> pps = clinic.countPatientsPerSpecialization();
		assertNotNull("Missing doctors by num patients", pps);
		assertEquals("Wrong specializations in count patients", 2,pps.size());
	}

}
