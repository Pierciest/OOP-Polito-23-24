package it.polito.oop.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;

import hydraulic.SimulationObserver;

class StoreObserver implements SimulationObserver {
	private static class Event {
		String type;
		String name;
		double inFlow;
		double[] outFlow;

		public Event(String type, String name, double inFlow, double... outFlow) {
			super();
			this.type = type;
			this.name = name;
			this.inFlow = inFlow;
			this.outFlow = outFlow;
		}
	}
	private final HashMap<String,Event> events = new HashMap<>();
	private final HashMap<String,Event> errors = new HashMap<>();

	@Override
	public void notify(Level level, String type, String name, double inFlow, double... flows) {
		Event evt = new Event(type.toLowerCase(),name.toLowerCase(),inFlow,flows);

		switch(level) {
		case STATUS: events.put(evt.name.toLowerCase(), evt );
					 break;
		case ERROR:  errors.put(evt.name.toLowerCase(), evt );
		 			 break;
		}
	}

	public boolean contains(String name) {
		return events.containsKey(name.toLowerCase());
	}


	public boolean containsError(String name) {
		return errors.containsKey(name.toLowerCase());
	}

	
	public Object getErrorCount() {
		return errors.size();
	}


	double maxFlowOf(String name) {
		if(!errors.containsKey(name.toLowerCase())) 
			fail("Could not find flow error notification for element " + name);
		return errors.get(name.toLowerCase()).outFlow[0];
	}


	void assertHasType(String name, String expectedType) {
		if(!events.containsKey(name.toLowerCase())) 
			fail("There was no simulation notification for element " + name);
		String type = events.get(name.toLowerCase()).type.toLowerCase();
		expectedType = expectedType.toLowerCase();
		assertTrue("Wrong type for element " + name + ": expected '" 
					+ expectedType + "' but was " + type,
					type.endsWith(expectedType));
	}

	double inFlowOf(String name) {
		if(!events.containsKey(name.toLowerCase())) 
			fail("There was no simulation notification for element " + name);
		return events.get(name.toLowerCase()).inFlow;
	}
	double outFlowOf(String name) {
		if(!events.containsKey(name.toLowerCase())) 
			fail("There was no simulation notification for element " + name);
		return events.get(name.toLowerCase()).outFlow[0];
	}
	double[] outFlowsOf(String name) {
		if(!events.containsKey(name.toLowerCase())) 
			fail("There was no simulation notification for element " + name);
		return events.get(name.toLowerCase()).outFlow;
	}
}
