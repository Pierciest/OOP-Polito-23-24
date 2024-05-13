package it.polito.oop.test;

import hydraulic.*;
import static org.junit.Assert.*;

import org.junit.Test;


public class TestR2_SimpleElements {

	@Test
	public void testNameSource(){
		String name="Test";
		Element el = new Source(name);
		
		assertEquals("Wrong name for element", name,el.getName());
	}

	@Test
	public void testNameTap(){
		String name="Test";
		Element el = new Tap(name);
		
		assertEquals("Wrong name for element", name,el.getName());
	}

	@Test
	public void testNameSink(){
		String name="Test";
		Element el = new Sink(name);
		
		assertEquals("Wrong name for element, ", name,el.getName());
	}

	@Test
	public void testConnections(){
		HSystem s = new HSystem();
		Source src = new Source("Src");		
		Tap tap = new Tap("Tap");		
		Sink sink = new Sink("Sink");
		
		s.addElement(src);
		s.addElement(tap);
		s.addElement(sink);
		
		src.connect(tap);
		tap.connect(sink);
		
		OOPAssertions.assertSameElement("Wrong output for element src, ", tap, src.getOutput());
		OOPAssertions.assertSameElement("Wrong output for element tap, ", sink, tap.getOutput());
	}

	@Test
	public void testSinkConnect(){
		Tap tap = new Tap("Tap");		
		Sink sink = new Sink("Sink");
		
		Element none = sink.getOutput();
		
		sink.connect(tap);
		
		Element out = sink.getOutput();
		
		OOPAssertions.assertSameElement("Connect on a sink should have no effect, ", none, out);
		
		tap.connect(sink);

		OOPAssertions.assertSameElement("Connect to a sink should work, ", sink, tap.getOutput());
	}

}
