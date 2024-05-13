package it.polito.oop.test;

import hydraulic.*;
import static hydraulic.SimulationObserver.NO_FLOW;
import static org.junit.Assert.*;
import org.junit.Test;


public class TestR4_Simulation {

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
		
		StoreObserver obs = new StoreObserver();

		s.simulate(obs);
		
		obs.assertHasType("Src","source");
		obs.assertHasType("Tap","tap");
		
		assertTrue("Missing simulation trace for element Tap",obs.contains("Tap"));
		assertTrue("Missing simulation trace for element Sink",obs.contains("Sink"));
		double inTap = obs.inFlowOf("Tap");
		double outTap = obs.outFlowOf("Tap");
		double inSink = obs.inFlowOf("Sink");

		assertEquals("Wrong input flow of 'Tap'", flow, inTap, 0.01);
		assertEquals("Wrong output flow of 'Tap'", flow, outTap, 0.01);
		assertEquals("Wrong input flow of 'Sink'", flow, inSink, 0.01);
	}

	@Test
	public void testSimpleElementsClosedTap(){
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
		
		StoreObserver obs = new StoreObserver();

		s.simulate(obs);
		
		obs.assertHasType("Src","Source");
		
		assertTrue("Missing simulation trace for element Tap",obs.contains("Tap"));
		assertTrue("Missing simulation trace for element Sink",obs.contains("Sink"));
		double inTap = obs.inFlowOf("Tap");
		double outTap = obs.outFlowOf("Tap");
		double inSink = obs.inFlowOf("Sink");

		assertEquals("Wrong input flow of 'Tap'", flow, inTap, 0.01);
		assertEquals("When 'Tap' is closed output flow should be 0.0", 0.0, outTap, 0.01);
		assertEquals("Wrong input flow of 'Sink'", 0.0, inSink, 0.01);
	}

	@Test
	public void testSplit(){
		HSystem s = new HSystem();
		Source src = new Source("Src");		
		Split t = new Split("T");	
		Element sink1 = new Sink("Sink 1");		
		Element sink2 = new Sink("Sink 2");		
		s.addElement(src);
		s.addElement(t);
		s.addElement(sink1);
		s.addElement(sink2);
		
		src.connect(t);
		t.connect(sink1,0);
		t.connect(sink2,1);
		
		double flow = 100.0;
		src.setFlow(flow);
		
		StoreObserver obs = new StoreObserver();
		s.simulate(obs);
		
		assertTrue("There was not simulation notification for element T",obs.contains("T"));
		double[] splitOut = obs.outFlowsOf("T");

		assertEquals("There should be two outputs for the T split",2,splitOut.length);
		assertEquals("Wrong outputs for the T split",50.0,splitOut[0],0.01);
		assertEquals("Wrong outputs for the T split",50.0,splitOut[1],0.01);
		assertEquals("Wrong input flow of 'Sink 1'", 50, obs.inFlowOf("Sink 1"), 0.01);
		assertEquals("Wrong input flow of 'Sink 2'", 50, obs.inFlowOf("Sink 2"), 0.01);
	}
	
	@Test
	public void testMissingFlow(){
		HSystem s = new HSystem();
		Source src = new Source("Src");		
		Sink sink = new Sink("Sink");		
		s.addElement(src);
		s.addElement(sink);
		
		src.connect(sink);
		
		double flow = 10.0;
		src.setFlow(flow);
		
		StoreObserver obs = new StoreObserver();

		s.simulate(obs);
		
		assertTrue("There was not simulation notification for element Src",obs.contains("Src"));
		assertTrue("There was not simulation notification for element Sink",obs.contains("Sink"));
		double inSrc = obs.inFlowOf("Src");
		double outSrc = obs.outFlowOf("Src");
		double inSink = obs.inFlowOf("Sink");
		double outSink = obs.outFlowOf("Sink");

		assertEquals("Wrong output flow of 'Src'", flow, outSrc, 0.01);
		assertEquals("Wrong input flow of 'Sink'", flow, inSink, 0.01);
		assertEquals("Input flow of source should be NO_FLOW",
							NO_FLOW,inSrc,0.0);
		assertEquals("Output flow of sink should be NO_FLOW",
				NO_FLOW,outSink,0.0);
	}

}
