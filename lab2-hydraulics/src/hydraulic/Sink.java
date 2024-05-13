package hydraulic;

/**
 * Represents the sink, i.e. the terminal element of a system
 *
 */
public class Sink extends Element {
	/**
	 * Constructor
	 * @param name name of the sink element
	 */
	public Sink(String name) {
		super(0);
		this.name = name;
	}
}
