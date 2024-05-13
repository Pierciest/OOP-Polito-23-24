package example;
import hydraulic.*;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestExampleBase {
	
	@Test
	public void testR1(){

		// R1
		HSystem s = new HSystem();
		
		Element[] elements = s.getElements();
		assertNotNull("Apparently getElements() is not implemented yet", elements);
		assertEquals("Initially no elements are present in the system", 0, elements.length);
		
	}	
	
	private boolean arrayContains(Element[] a, Element e){
		for(Element el : a){
			if( e == el){
				return true;
			}
		}
		return false;
	}

	@Test
	public void testR2(){
		HSystem s = new HSystem();
		// the elements of the system are defined and added
		Element src = new Source("Src");
		s.addElement(src);
		Element r = new Tap("R");
		s.addElement(r);
		Element sink = new Sink("sink B");
		s.addElement(sink);

		assertEquals("Src",src.getName());
		assertEquals("sink B",sink.getName());

		Element[] elements = s.getElements();
		assertNotNull("Apparently getElements() is not implemented yet",elements);
		assertEquals("Wrong number of elements; ", 3, elements.length);
		assertTrue("Missing source", arrayContains(elements, src));
		assertTrue("Missing source", arrayContains(elements, sink));
		
		// elements are then connected
		src.connect(r);
		r.connect(sink);
	
		assertSame("Output of src should be r", r, src.getOutput());
	}

	@Test
	public void testR3(){
		HSystem s = new HSystem();

		Element src = new Source("Src");
		s.addElement(src);
		Element r = new Tap("R");
		s.addElement(r);
		Element t = new Split("T");
		s.addElement(t);
		Element sinkA = new Sink("sink A");
		s.addElement(sinkA);
		Element sinkB = new Sink("sink B");
		s.addElement(sinkB);

		src.connect(r);
		r.connect(t);
		t.connect(sinkA,0);
		t.connect(sinkB,1);

		assertSame("Output of src should be r", r, src.getOutput());
		Element[] outputs = t.getOutputs();
		assertArrayEquals("Outputs of t should be sink A and sink B", new Element[] {sinkA,sinkB}, outputs);

	}

	@Test
	public void testR4(){
		HSystem s = new HSystem();

		Source src = new Source("Src");
		s.addElement(src);
		Tap r = new Tap("R");
		s.addElement(r);
		Element t = new Split("T");
		s.addElement(t);
		Element sinkA = new Sink("sink A");
		s.addElement(sinkA);
		Element sinkB = new Sink("sink B");
		s.addElement(sinkB);
		src.connect(r);
		r.connect(t);
		t.connect(sinkA,0);
		t.connect(sinkB,1);

		// Simulation parameters are then defined
		src.setFlow(20);
		r.setOpen(true);
		
		// simulation starts
		PrintingObserver obs = new PrintingObserver();
		s.simulate(obs);

		assertEquals("Expected 5 notifications",5,obs.getCount());
		
	}
}
