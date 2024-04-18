package example;
import hydraulic.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestExampleExt {
	private HSystem s;
	private Source src;
	private Element t;
	private Element sinkA;
	private Element sinkB;
	private Element sinkC;
	private Element sinkD;
	private Tap r;
	private Multisplit ms;

	@Before
	public void setUp(){
		s = new HSystem();

		src = new Source("Src");
		s.addElement(src);
		r = new Tap("R");
		s.addElement(r);
		t = new Split("T");
		s.addElement(t);
		sinkA = new Sink("sink A");
		s.addElement(sinkA);
		sinkB = new Sink("sink B");
		s.addElement(sinkB);

		// Create a multi-split and some additional sink
		ms = new Multisplit("MS",3);
		s.addElement(ms);
		sinkC = new Sink("sink C");
		s.addElement(sinkC);
		sinkD = new Sink("sink D");
		s.addElement(sinkD);

		// Change the system including the multi-split
		src.connect(r);
		r.connect(ms);
		ms.connect(t,0);
		ms.connect(sinkC,1);
		ms.connect(sinkD,2);
		t.connect(sinkA,0);
		t.connect(sinkB,1);
	}

	@Test
	public void testR5(){
		// simulation parameters are then defined
		src.setFlow(20);
		r.setOpen(true);
		ms.setProportions(.25,.35,.40);
		
		// simulation starts
		PrintingObserver obs = new PrintingObserver();
		s.simulate(obs);
		assertTrue("Expected at least 8 notifications but received just " + obs.getCount(), 
				   obs.getCount() >= 8);
	}

	@Test
	public void testR6(){
		// show the layout of the system
		String layout = s.layout();
		System.out.println(layout);
		assertNotNull("Missing layout", layout);
		assertTrue("Should start with the Src", layout.startsWith("[Src"));
		assertTrue("Should includes Sink B", layout.indexOf("sink B")>0);		
	}

	@Test
	public void testR7(){
		// delete the tap
		s.deleteElement("R");
		System.out.println(s.layout());
		assertSame("Output of src should be t",ms,src.getOutput());
	}

	@Test
	public void testR8(){
		src.setFlow(20);
		r.setOpen(true);
		ms.setProportions(.25,.35,.40);

		ms.setMaxFlow(20);
		r.setMaxFlow(25);
		t.setMaxFlow(10);
		sinkA.setMaxFlow(10);
		sinkB.setMaxFlow(15);
		sinkC.setMaxFlow(3); // should raise error message, inFlow 8.0 but maxFlow 3.0
		sinkD.setMaxFlow(8);
		PrintingObserver obs = new PrintingObserver();
		s.simulate(obs,true);
		assertEquals("Missing some simulation notification", 8, obs.getCount());
		assertEquals("Error notification not received", 1, obs.getErrorCount());
	}

	@Test
	public void testR9(){
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

		assertNotNull("Builder did not return any system", s);

		PrintingObserver obs = new PrintingObserver();
		s.simulate(obs);
		assertTrue("Expected at least 7 notifications but received just " + obs.getCount(), 
				   obs.getCount() >= 7);
	}

}
