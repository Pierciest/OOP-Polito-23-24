package it.polito.library;

public class LibException extends Exception {

	private static final long serialVersionUID = 1L;

	public LibException() {super("Library exception");}
	
	public LibException(String msg) {super(msg);}

}

