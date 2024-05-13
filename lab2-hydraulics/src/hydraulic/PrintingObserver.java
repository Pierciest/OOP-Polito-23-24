package hydraulic;

import java.util.Arrays;

/**
 * Sample implementation of {@link SimulationObserver}.
 * This simulation observer simply prints out the notification info
 * and keeps count of how many notification it received.
 * 
 * This class can be used for debugging and testing purposes.
 *  
 */
public class PrintingObserver implements SimulationObserver {
	private int countNotifications = 0;
	private int countErrorNotifications = 0;
	
	@Override
	public void notify(Level level, String type, String name, double inFlow, double... flows) {
		switch(level) {
		case STATUS:
			System.out.println(type + " " + name + ": ");
			if(exists(inFlow)) System.out.println("\t-> in flow=" + inFlow);
			if(exists(flows)) System.out.println("\t<- out flow=" + Arrays.toString(flows));
			countNotifications++;
			break;
		case ERROR:
			System.err.println(type + " " + name + " inFlow is " + inFlow + " but maximum flow is " + flows[0]);
			countErrorNotifications++;
			break;
		}
	}	
	
	/**
	 * retrieves the number of status notifications received
	 * 
	 * @return the notification count
	 */
	public int getCount() {
		return countNotifications;
	}

	/**
	 * retrieves the number of error notifications received
	 * 
	 * @return the cound of error notifications
	 */
	public int getErrorCount() {
		return countErrorNotifications;
	}

	/**
	 * method to check whether a flow is defined, (i.e. it is not a {@link #NO_FLOW}).
	 * 
	 * @param flows the flow to be tested
	 * 
	 * @return {@code true} if the flow is defined
	 */
	static boolean exists(double... flows) {
		boolean res=true;
		for(double f : flows) {
			res &= f != SimulationObserver.NO_FLOW;
		}
		return res;
	}

}
