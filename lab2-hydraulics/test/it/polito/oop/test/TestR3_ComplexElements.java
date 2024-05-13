package it.polito.oop.test;

import hydraulic.*;
import static org.junit.Assert.*;

import org.junit.Test;


public class TestR3_ComplexElements {

	
	@Test
	public void testSplitName(){
		Split t = new Split("Split name");	

		assertNotNull("Missing split name",t.getName());
		assertEquals("Wrong name for split element","Split name",t.getName());
	}
		

	@Test
	public void testSplit(){
		
		HSystem s = new HSystem();
		
		Element src = new Source("Src");		
		Split t = new Split("T");	
		Element sink1 = new Sink("Sink 1");		
		Element sink2 = new Sink("Sink 2");		
		
		s.addElement(src);
		s.addElement(t);
		s.addElement(sink1);
		s.addElement(sink2);
		
		src.connect(t);
		t.connect(sink1, 0);
		t.connect(sink2, 1);
		
		Element[] out = t.getOutputs();
		
		assertNotNull("Missing output for the split",out);
		OOPAssertions.assertSameElement("Wrong output 0 for Split", sink1, out[0]);
		OOPAssertions.assertSameElement("Wrong output 1 for Split", sink2, out[1]);
	}

}
