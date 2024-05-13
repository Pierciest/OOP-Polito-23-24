package hydraulic;

/**
 * Represents a split element, a.k.a. T element
 * 
 * During the simulation each downstream element will
 * receive a stream that is half the input stream of the split.
 */

public class Split extends Element {
	int dir;

	/**
	 * Constructor
	 * @param name name of the split element
	 */
	public Split(String name) {
		super(2);
		this.name = name;
	}
	public Split(String name, int numOutput){
		super(numOutput);
		this.name = name;
	}
}
