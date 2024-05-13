package it.polito.oop.test;

import hydraulic.*;

import org.junit.Test;

public class TestR6_Visualization {

	@Test
	public void testSimpleSystem(){
		HSystem s = new HSystem();
		Source src = new Source("Src");		
		Multisplit ms = new Multisplit("MS",3);		
		Sink s1 = new Sink("S1");		
		Sink s2 = new Sink("S2");		
		Sink s3 = new Sink("S3");		
		s.addElement(src);
		s.addElement(ms);
		s.addElement(s1);
		s.addElement(s2);
		s.addElement(s3);
		
		src.connect(ms);
		ms.connect(s1,0);
		ms.connect(s3,1);
		ms.connect(s2,2);
		
		String layout = s.layout();
		
		OOPAssertions.assertInOrder(layout,"Src","MS","S1","S3","S2");
	
		OOPAssertions.assertContainTimes("Not enough multisplit branches",
							3, "+->", layout );
		
		OOPAssertions.assertSameIndent(layout, "S1","S2","S3");
	}

	@Test
	public void testIncompleteMultisplit(){
		HSystem s = new HSystem();
		Source src = new Source("Src");		
		Multisplit ms = new Multisplit("MS",3);		
		Sink s1 = new Sink("S1");		
		Sink s2 = new Sink("S2");		
		//Sink s3 = new Sink("S3");		
		s.addElement(src);
		s.addElement(ms);
		s.addElement(s1);
		s.addElement(s2);
		//s.addElement(s3);
		
		src.connect(ms);
		ms.connect(s1,0);
		//ms.connect(s3,1);
		ms.connect(s2,2);
		
		String layout = s.layout();
		
		OOPAssertions.assertInOrder(layout,"Src","MS","S1","S2");
	
		OOPAssertions.assertContainTimes("Not enough multisplit branches",
							3, "+->", layout );
		
		OOPAssertions.assertSameIndent(layout, "S1","S2");
	}

	@Test
	public void testIncompleteSource(){
		HSystem s = new HSystem();
		Source src = new Source("Src");		
		s.addElement(src);
		
		String layout = s.layout();
		
		OOPAssertions.assertInOrder(layout,"Src");
		OOPAssertions.assertEndsWith("Missing '*' for unconnected output", layout, "*");
	}

	@Test
	public void testIncompleteTap(){
		HSystem s = new HSystem();
		Source src = new Source("Src");
		Tap t = new Tap("Tap");
		s.addElement(src);
		s.addElement(t);
		
		src.connect(t);
		
		String layout = s.layout();
		
		OOPAssertions.assertInOrder(layout,"Src","Tap");
		
		OOPAssertions.assertEndsWith("Missing '*' for unconnected output", layout, "*");
	}

}
