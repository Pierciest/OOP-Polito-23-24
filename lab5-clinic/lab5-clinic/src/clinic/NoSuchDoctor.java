package clinic;

/**
 * Exception class used to notify that a Doctor is not available
 */
public class NoSuchDoctor extends Exception {

	public NoSuchDoctor() {
	}

	public NoSuchDoctor(int id) {
		super("id: " + id);
	}

	private static final long serialVersionUID = 1L;
}
