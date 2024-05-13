package it.polito.oop.test;

import static org.junit.Assert.*;
import org.junit.Test;

import hydraulic.*;

public class TestR1_Elements {
	
	@Test
	public void testEmptySystem(){
		HSystem s = new HSystem();
		
		Element[] elements = s.getElements();
		
		assertNotNull("Missing elements",elements);
		
		assertEquals("There should be any elements at first", 0,elements.length);
	}

	@Test
	public void testGetElements(){
		HSystem s = new HSystem();
		
		Element el1 = new Source("Test");		
		Element el2 = new Source("Test 1");
		Element el3 = new Source("Test 2");	
		s.addElement(el1);
		s.addElement(el2);
		s.addElement(el3);
		
		Element[] elements = s.getElements();
		
		OOPAssertions.assertArrayContainsAll("Missing elements",elements,el1,el2);
	}
}
