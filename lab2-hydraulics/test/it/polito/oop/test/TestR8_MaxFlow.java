package it.polito.oop.test;

import hydraulic.*;
import static org.junit.Assert.*;
import org.junit.Test;


public class TestR8_MaxFlow {

	@Test
	public void testSimpleElements(){
		HSystem s = new HSystem();
		Source src = new Source("Src");		
		Tap tap = new Tap("Tap");		
		Sink sink = new Sink("Sink");		
		s.addElement(src);
		s.addElement(tap);
		s.addElement(sink);
		
		src.connect(tap);
		tap.connect(sink);
		
		double flow = 100.0;
		src.setFlow(flow);
		tap.setOpen(true);
		
		tap.setMaxFlow(90.0);
		sink.setMaxFlow(90.0);

		StoreObserver obs = new StoreObserver();

		s.simulate(obs,true);
		
		assertEquals("Wrong number of notifications",2,obs.getErrorCount());
		assertTrue("Missing simulation trace for element Tap",obs.containsError("Tap"));

		assertEquals("Wrong max flow of 'Tap'", 90.0, obs.maxFlowOf("Tap"), 0.01);
		assertEquals("Wrong max flow of 'Sink'", 90.0, obs.maxFlowOf("Sink"), 0.01);
	}

	@Test
	public void testSimpleElementsClosed(){
		HSystem s = new HSystem();
		Source src = new Source("Src");		
		Tap tap = new Tap("Tap");		
		Sink sink = new Sink("Sink");		
		s.addElement(src);
		s.addElement(tap);
		s.addElement(sink);
		
		src.connect(tap);
		tap.connect(sink);
		
		double flow = 100.0;
		src.setFlow(flow);
		tap.setOpen(false);

		tap.setMaxFlow(90.0);
		sink.setMaxFlow(90.0);
		
		StoreObserver obs = new StoreObserver();

		s.simulate(obs,true);
		
		assertEquals("Expected one error notifications", 1, obs.getErrorCount());

		assertTrue("There was no error notification for element Tap",obs.containsError("Tap"));

		assertEquals("Wrong max flow of 'Tap'", 90.0, obs.maxFlowOf("Tap"), 0.01);
	}

	
	//---------- utility class: observer that collects a log of notification events -----------ù
	

}
