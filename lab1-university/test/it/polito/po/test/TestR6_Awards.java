package it.polito.po.test;

import static org.junit.Assert.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import university.University;

import static it.polito.po.test.TestR5_Exams.*;

public class TestR6_Awards {
	static final String universityName = "Politecnico di Torino";
	private University poli;
	
	@Before
	public void setUp() {

		Logger ul = Logger.getLogger("University");		
		ul.setLevel(Level.OFF);
		
		poli = new University(universityName);
		poli.setRector("Guido", "Saracco");
		
		poli.enroll("Mario","Rossi");
		poli.enroll("Francesca","Verdi");
		poli.enroll("Filippo","Neri");
		poli.enroll("Laura","Bianchi");
		
		
		poli.activate("Macro Economics", "Paul Krugman");
		poli.activate("Object Oriented Programming", "James Gosling");
		poli.activate("Virology", "Roberto Burioni");
		
		poli.register(10000, 10);
		poli.register(10001, 10);
		poli.register(10001, 11);
		poli.register(10001, 12);
		poli.register(10002, 11);
		poli.register(10003, 10);
		poli.register(10003, 11);
		
		//			10		11		12
		// 10000     X
		// 10001	X		X		X
		// 10002			X
		// 10003	X		X
		
	}
	

	@Test
	public void testTopSimilar() {
		final int studentId = 10001;
		int courseId = 10;
		poli.exam(studentId, courseId, 27);
		poli.exam(studentId, courseId+1, 27);
		poli.exam(studentId+1, courseId+1, 28);
		poli.exam(studentId+2, courseId, 29);
		poli.exam(studentId+2, courseId+1, 20);	

		String top = poli.topThreeStudents();
		
		assertNotNull("Missing top three students",top);
		
		String[] rank = top.split("\n");
		
		assertEquals("Expected three students",3,rank.length);
		
		assertContained("Wrong top student","Neri",rank[0]);
		assertContained("Wrong top student score","38",rank[0]);
	}

	@Test
	public void testTopBonus() {
		poli.exam(10000, 10, 25);
		poli.exam(10001, 10, 26);
		poli.exam(10001, 11, 28);
		poli.exam(10003, 10, 26);
		poli.exam(10003, 11, 26);

		String top = poli.topThreeStudents();
		
		assertNotNull("Missing top three students",top);
		
		String[] rank = top.split("\n");
		
		assertEquals("Expected three students",3,rank.length);
		
		assertContained("Wrong top student","Bianchi",rank[0]);
		assertContained("Wrong top student score","36",rank[0]);
	}

	@Test
	public void testTopTwo() {
		poli.exam(10000, 10, 27);
		poli.exam(10002, 11, 26);

		String top = poli.topThreeStudents();
		
		assertNotNull("Missing top three students",top);
		
		String[] rank = top.split("\n");
		
		assertEquals("Expected just two students",2,rank.length);
		
		assertContained("Wrong top student","Rossi",rank[0]);
		assertContained("Wrong top student score","37",rank[0]);
	}

}
