import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.logging.Level;

import org.junit.Test;

import university.University;

/**
 * Test for extended requirements
 */
public class SimpleExtTest {	
	@Test
	public void testR5() {
		University poli = new University("PoliTo");
		int s1 = poli.enroll("Mario","Rossi");
		int s2 = poli.enroll("Giuseppe","Verdi");
		int macro = poli.activate("Macro Economics", "Paul Krugman");
		int oop = poli.activate("Object Oriented Programming", "James Gosling");
		poli.register(s1, macro);
		poli.register(s2, macro);
		poli.register(s2, oop);

		poli.exam(s1, macro, 27);
		poli.exam(s2, macro, 30);
		poli.exam(s2, oop, 28);
		
		System.out.println(poli.studentAvg(s2)); // 29.0
		
		System.out.println(poli.courseAvg(macro)); // 28.5
        
		assertEquals("Wrong student average", "Student 10001 : 29.0", poli.studentAvg(s2));		
	}

    @Test
    public void testR6() {
        University poli = new University("PoliTo");
        int s1 = poli.enroll("Mario","Rossi");
        int s2 = poli.enroll("Giuseppe","Verdi");
        int macro = poli.activate("Macro Economics", "Paul Krugman");
        int oop = poli.activate("Object Oriented Programming", "James Gosling");
        poli.register(s1, macro);
        poli.register(s2, macro);
        poli.register(s2, oop);

        poli.exam(s1, macro, 27);
        poli.exam(s2, macro, 30);
        poli.exam(s2, oop, 28);

        System.out.println("Best students:\n" + poli.topThreeStudents());

        String best = poli.topThreeStudents();

        assertNotNull("Missing top students", best);
        assertTrue("Missing best student Verdi", best.contains("Verdi : 39.0"));


    }

    private String lastMessage;
    private int logCount;
    @Test
    public void testR7() {
        University.logger.setFilter( record -> {
            lastMessage = record.getMessage();
            logCount++;
            return true;
        });
        lastMessage = "";
        logCount = 0;
        University.logger.setLevel(Level.ALL);

        University poli = new University("PoliTo");
        poli.enroll("Mario","Rossi");

        assertEquals("No log message was generated for new enrollment", 1, logCount);
        assertTrue("Message does not include student name", lastMessage.contains("Rossi"));
        
        poli.activate("Macro Economics", "Paul Krugman");

        assertEquals("No log message was generated for new course activation", 2, logCount);
        assertTrue("Message does not include course title", lastMessage.contains("Economics"));

        University.logger.setFilter(null);
    }
}

