package example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import it.polito.med.MedException;
import it.polito.med.MedManager;

public class TestExample {

	@Test
	public void testAll() throws MedException {
		MedManager mgr = new MedManager();
		
		// R1 : specialities and doctors
		
		mgr.addSpecialities("Ecography","Orthopedy","Cardiology");
		
		Collection<String> specs = mgr.getSpecialities();
		assertNotNull(specs);
		assertEquals(3, specs.size());
		assertTrue(specs.contains("Cardiology"));
		
		String code = "XD345";
		mgr.addDoctor(code,"John","Smith","Cardiology");
		mgr.addDoctor("AH876","Jane","Black","Cardiology");
		mgr.addDoctor("OK358","Jack","Bones","Orthopedy");
		
		assertThrows(MedException.class,
					 ()-> mgr.addDoctor("FD845","Mary","White","Pediatry"));

		Collection<String> cardiologists = mgr.getSpecialists("Cardiology");
		assertNotNull(cardiologists);
		assertEquals(2,cardiologists.size());
		
		assertEquals("John",mgr.getDocName(code));
		assertEquals("Smith",mgr.getDocSurname(code));
		
		// R2 : schedule
		
		// define slots on 2023-06-28 from 10:00 to 12:00 every 20 minutes
		int numSlots = mgr.addDailySchedule(code, "2023-06-28", "10:00", "12:00", 20);
		assertEquals(6, numSlots);
		mgr.addDailySchedule(code, "2023-07-04", "15:00", "18:00", 20);
		mgr.addDailySchedule("OK358", "2023-07-03", "09:00", "11:00", 30);
		
		Map<String,List<String>> slots = mgr.findSlots("2023-07-04","Cardiology");
		assertNotNull(slots);
		assertEquals(1,slots.size());
		assertTrue(slots.containsKey(code));
		assertEquals(9, slots.get(code).size());
		assertTrue(slots.get(code).contains("15:00-15:20"));
		
		
		// R3 : booking appointments
		
		String ssn = "GVNBNC80B14F219K";
		String a1 = mgr.setAppointment(ssn,"Giovanni","Bianchi",code,"2023-06-28","10:40-11:00");
		String a2 = mgr.setAppointment("LRARSS87G64A341J","Laura","Rossi",code,"2023-06-28","10:00-10:20");


		assertEquals(code, mgr.getAppointmentDoctor(a1));
		assertEquals(ssn, mgr.getAppointmentPatient(a1));
		assertEquals("10:40", mgr.getAppointmentTime(a1));
		assertEquals("2023-06-28", mgr.getAppointmentDate(a2));
		
		Collection<String> appointments = mgr.listAppointments(code,"2023-06-28");
		assertNotNull(appointments);
		assertEquals(2,appointments.size());
		assertTrue(appointments.contains("10:40="+ssn));
		
		
		// R4 : welcome patients
		
		int numApps = mgr.setCurrentDate("2023-06-28");
		assertEquals(2, numApps);
		
		mgr.accept(ssn);
		
		String nextApp = mgr.nextAppointment(code);
		assertNotNull(nextApp);
		assertEquals(a1,nextApp);
		
		mgr.completeAppointment(code,nextApp);
		
		assertNull(mgr.nextAppointment(code));
		
		
		// R5 : stats
		
		double noShow = mgr.showRate(code, "2023-06-28");
		assertEquals(0.5, noShow, 0.001);
		
		Map<String,Double> completeness = mgr.scheduleCompleteness();
		assertNotNull(completeness);
		assertEquals(3,completeness.size());
		assertEquals(2.0/15.0, completeness.get(code), 0.001);
		
	}
		
}
