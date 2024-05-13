package it.polito.oop.test;

import hydraulic.*;

import static org.junit.Assert.*;
import org.junit.Test;


public class TestR5_Multisplit {

	@Test
	public void testCreationElements(){
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
		
		Element[] outs = ms.getOutputs();
		
		assertNotNull("Missing outputs",outs);
		assertEquals("Wrong number of outputs",3,outs.length);
		assertArrayEquals("Wrong outputs", outs, new Element[]{s1, s3, s2});
	}

	@Test
	public void testSimulation(){
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
		ms.connect(s2,1);
		ms.connect(s3,2);
		
		
		double flow = 100.0;
		src.setFlow(flow);
		double[] props = {0.25,0.35,0.40};
		ms.setProportions(props);
		
		StoreObserver obs = new StoreObserver();

		s.simulate(obs);
		
		assertTrue("Missing simulation trace for element MS",obs.contains("MS"));

		double inTap = obs.inFlowOf("MS");
		double[] outTap = obs.outFlowsOf("MS");
		double inSink = obs.inFlowOf("S3");

		assertEquals("Wrong input flow of 'MS'", flow, inTap, 0.01);
		for(int i=0; i<props.length; ++i)
			assertEquals("Wrong output flow " + i + " of 'MS'", flow*props[i], outTap[i], 0.01);
		assertEquals("Wrong input flow of 'S3'", flow*props[2], inSink, 0.01);
	}
	
}
