package it.polito.med;

public class MedException extends Exception {

	private static final long serialVersionUID = 1L;

	public MedException() {super("Medical center exception");}
	
	public MedException(String msg) {super(msg);}

}
