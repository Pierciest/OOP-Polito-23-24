package it.polito.po.test;

import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.function.Function;

public class CollectionsAssertions {

	/**
	 * Checks the elements of a collection are sorted.
	 * Natural ordering is applied to the result of the getter Function.
	 * 
	 * @param <T> type of collection elements 
	 * @param <R> attribute used for comparison
	 * @param msg Message in case of assertion failure
	 * @param c	  collection
	 * @param getter getter function
	 */
	public static <T, R extends Comparable<? super R> > 
	void assertSorted(String msg, Collection<T> c, Function<T, R> getter) {
		R prev = null;
		for (T e : c) {
			R current = getter.apply(e);
			if( prev!=null && current.compareTo(prev) <= 0 ) {
				fail(msg + ": [" + prev + "] precedes [" + current + "]" );
			}
			prev = current;
		}
	}
	
}
