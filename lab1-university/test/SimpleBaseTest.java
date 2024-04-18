import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import university.University;

public class SimpleBaseTest {
	
	private static final int FIRST_ID = 10000;
	private static final int FIRST_CODE = 10;
	
	@Test
	public void testR1() {
		String universityName = "Politecnico di Torino";
		
		University poli = new University(universityName);
		
		poli.setRector("Stefano", "Corgnati");
		
		System.out.println("Rector of " + poli.getName() + " : " + poli.getRector());

		assertEquals("Wrong university name", universityName, poli.getName());
		assertEquals("Wrong rector", "Stefano Corgnati", poli.getRector());
	}
		
	@Test
	public void testR2() {
		University poli = new University("PoliTo");
		int s1 = poli.enroll("Mario","Rossi");
		int s2 = poli.enroll("Giuseppe","Verdi");
		
		System.out.println("Enrolled students " + s1 + ", " + s2); // 10000, 10001
		System.out.println("s1 = " + poli.student(s1)); // 10000 Mario Rossi

		assertEquals("First student id should be "+ FIRST_ID, FIRST_ID, s1 );
		assertEquals("Second student id should be "+ (FIRST_ID+1), (FIRST_ID+1), s2 );
	}
		
	@Test
	public void testR3() {
		University poli = new University("PoliTo");
		int macro = poli.activate("Macro Economics", "Paul Krugman");
		int oop = poli.activate("Object Oriented Programming", "James Gosling");
		
		System.out.println("Activated courses " + macro + " and " + oop); // 10 and 11

		assertEquals("First course id should be "+ FIRST_CODE, FIRST_CODE, macro );
		assertEquals("Second course id should be "+ (FIRST_CODE+1), (FIRST_CODE+1), oop );
	}
		
	@Test
	public void testR4() {
		University poli = new University("PoliTo");
		int s1 = poli.enroll("Mario","Rossi");
		int s2 = poli.enroll("Giuseppe","Verdi");
		int macro = poli.activate("Macro Economics", "Paul Krugman");
		int oop = poli.activate("Object Oriented Programming", "James Gosling");

		poli.register(s1, macro);
		poli.register(s2, macro);
		poli.register(s2, oop);
		
		System.out.println(poli.listAttendees(macro));
		// 10000 Mario Rossi
		// 10001 Giuseppe Verdi
		String attendees = poli.listAttendees(macro);
		assertNotNull("Missing attendees list", attendees);
		assertTrue("Missing student Rossi", attendees.contains("Rossi"));
		assertTrue("Missing student Verdi", attendees.contains("Verdi"));
		
		System.out.println(poli.studyPlan(s2));
		// 10,Macro Economics,Paul Krugman
		// 11,Object Oriented Programming,Marco Torchiano
		String plan = poli.studyPlan(s2);
		assertNotNull("Missing study plan", plan);
		assertTrue("Missing OOP course", plan.contains("Object"));
	}
}
