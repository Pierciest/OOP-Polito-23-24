package it.polito.oop.test;

import hydraulic.*;
import static org.junit.Assert.*;

import org.junit.Test;


public class TestR9_Builder {

	@Test
	public void testBuildSource(){
		HSystem s = HSystem.build().addSource("Src 1").complete();
				
		Element[] elements = s.getElements();
		
		assertNotNull(elements);
		assertEquals("There shoudl be one element;", 1, elements.length);
		assertEquals("Wrong element;", "Src 1", elements[0].getName());
	}

	@Test
	public void testConnections(){
		HSystem s = HSystem.build().
			addSource("Src").
			linkToTap("Tap").
			linkToSink("Sink").
			complete();

		Element[] elements = s.getElements();
	
		assertNotNull(elements);
		assertEquals("Wrong number of elements;", 3, elements.length);
		Element src = elements[0];
		assertEquals("Wrong element;", "Src", src.getName());

		Element tap = src.getOutput();
		assertNotNull(tap);
		assertEquals("Wrong element;", "Tap", tap.getName());

		
		Element sink = tap.getOutput();
		assertNotNull(sink);
		assertEquals("Wrong element;", "Sink", sink.getName());
	}

	@Test
	public void testSplit(){
		HSystem s = HSystem.build().
		addSource("Src").
		linkToSplit("T").withOutputs().
			linkToSink("Sink 1").
			then().linkToSink("Sink 2").
		complete();
		
		Element[] elements = s.getElements();
	
		assertNotNull(elements);
		assertEquals("Wrong number of elements;", 4, elements.length);
		Element src = elements[0];
		assertEquals("Wrong element;", "Src", src.getName());

		Element split = src.getOutput();

		Element[] out = split.getOutputs();
		
		assertNotNull("Missing outputs for the split",out);
		assertEquals("Wrong number of outputs", 2, out.length);
		assertEquals("Wrong output 0 for Split", "Sink 1", out[0].getName());
		assertEquals("Wrong output 1 for Split", "Sink 2", out[1].getName());
	}

	@Test
	public void testSimulate(){
		double flow = 100.0;
		
		HSystem s = HSystem.build().
		addSource("Src").withFlow(flow).
		linkToSplit("T").withOutputs().
			linkToSink("Sink 1").
			then().linkToSink("Sink 2").
		complete();
		
		
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
	public void testSimulateTap(){
		double flow = 100.0;
		
		HSystem s = HSystem.build().
		addSource("Src").withFlow(flow).
		linkToTap("Tap").open().
		linkToSink("Sink").
		complete();
		
		
		StoreObserver obs = new StoreObserver();
		s.simulate(obs);
		
		assertTrue("There was not simulation notification for element T",obs.contains("Tap"));
		double[] splitOut = obs.outFlowsOf("Tap");

		assertEquals("There should be two outputs for the T split",1,splitOut.length);
		assertEquals("Wrong outputs for the T split",flow,splitOut[0],0.01);
		assertEquals("Wrong input flow of 'Sink 1'", flow, obs.inFlowOf("Sink"), 0.01);
	}

	@Test
	public void testSimulateTapClose(){
		double flow = 100.0;
		
		HSystem s = HSystem.build().
		addSource("Src").withFlow(flow).
		linkToTap("Tap").closed().
		linkToSink("Sink").
		complete();
		
		
		StoreObserver obs = new StoreObserver();
		s.simulate(obs);
		
		assertTrue("There was not simulation notification for element T",obs.contains("Tap"));
		double[] splitOut = obs.outFlowsOf("Tap");

		assertEquals("There should be two outputs for the T split",1,splitOut.length);
		assertEquals("Wrong outputs for the T split", 0.0, splitOut[0],0.01);
		assertEquals("Wrong input flow of 'Sink 1'", 0.0, obs.inFlowOf("Sink"), 0.01);
	}

	@Test
	public void testSimulateMultisplit(){
		double flow = 100.0;
		double[] props = {0.25,0.35,0.40};

		HSystem s = HSystem.build().
		addSource("Src").withFlow(flow).
		linkToMultisplit("MS",3).withPropotions(props).withOutputs().
			linkToSink("S1").
			then().linkToSink("S2").
			then().linkToSink("S3").
		complete();
		
		
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

	@Test
	public void testSimulateMultisplitSplit(){
		double flow = 100.0;
		double[] props = {0.25,0.35,0.40};

		HSystem s = HSystem.build().
		addSource("Src").withFlow(flow).
		linkToMultisplit("MS",3).withPropotions(props).withOutputs().
			linkToSplit("T").withOutputs().
				linkToSink("S1").
				then().linkToSink("S2").
				done().
			then().linkToSink("S3").
			then().linkToSink("S4").
		complete();
				
		StoreObserver obs = new StoreObserver();
		s.simulate(obs);
				
		assertTrue("Missing simulation trace for element MS",obs.contains("MS"));

		double inTap = obs.inFlowOf("MS");
		double[] outTap = obs.outFlowsOf("MS");
		double inSink = obs.inFlowOf("S2");

		assertEquals("Wrong input flow of 'MS'", flow, inTap, 0.01);
		for(int i=0; i<props.length; ++i)
			assertEquals("Wrong output flow " + i + " of 'MS'", flow*props[i], outTap[i], 0.01);
		assertEquals("Wrong input flow of 'S2'", flow*props[0]*0.5, inSink, 0.01);
	}

	@Test
	public void testMaxFlow(){
		double flow = 100.0;
		
		HSystem s = HSystem.build().
		addSource("Src").withFlow(flow).
		linkToTap("Tap").open().maxFlow(90.0).
		linkToSink("Sink").maxFlow(90.0).
		complete();
		
		
		StoreObserver obs = new StoreObserver();
		s.simulate(obs, true);
		
		assertEquals("Wrong number of notifications",2,obs.getErrorCount());
		assertTrue("Missing simulation trace for element Tap",obs.containsError("Tap"));

		assertEquals("Wrong max flow of 'Tap'", 90.0, obs.maxFlowOf("Tap"), 0.01);
		assertEquals("Wrong max flow of 'Sink'", 90.0, obs.maxFlowOf("Sink"), 0.01);
	}


}
