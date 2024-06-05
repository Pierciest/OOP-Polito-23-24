package clinic;

import java.io.Reader;

/**
 * Error listener interface
 * 
 * It is used by {@link Clinic#loadData(Reader,ErrorListener)}
 * to notify offending lines by calling the {@link #offending} method.
 *
 */
public interface ErrorListener {
	/**
	 * Accepts an offending line during file read
	 * i.e. a line that caused a read error and was discarded
	 * 
	 * @param line the line that caused the error
	 * @param lineNo the line numner (the first one being line 1)
	 */
	void offending(int lineNo, String line);

}