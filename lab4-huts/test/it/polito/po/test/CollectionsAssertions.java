package it.polito.po.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.Map;
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

	/**
	 * Expected entry descriptor.
	 * The expected value may be compared to the map entry value
	 * or to a value derived from it
	 *
	 * @param <K> Type of entry key
	 * @param <V> Type of entry value
	 * @param <R> Type of expected value
	 */
	static class Entry<K,V,R> {
		final K k;
		final R v;
		final Function<V,R> get;
		Entry(K k, R v){
			this.k=k;
			this.v=v;
			this.get=null;
		}
		Entry(K k, Function<V,R> getter, R v){
			this.k=k;
			this.v=v;
			this.get=getter;
		}
	}

	/**
	 * Factory method to define an expected entry descriptor
	 * The value obtained from the map for the key is compared to the given expected value
	 * 
	 * @param <K>	type of map keys
	 * @param <V>	type of map values
	 * @param key		key
	 * @param expectedValue		value
	 * @return an expected Entry descriptor
	 */
	static <K,V> Entry<K,V,V> entry(K key, V expectedValue){ return new Entry<>(key,expectedValue); }

	/**
	 * Factory method to define an expected entry.
	 * The expected value is compared to the result of the getter function applied to the value obtained from the map with the key.
	 * 
	 * @param <K>	type of map keys
	 * @param <V>	type of map values
	 * @param <R>	type of the expected value
	 * @param key		key
	 * @param getter	getter function that 
	 * @param expected		value
	 * @return an expected Entry descriptor
	 */
	static <K,V,R> Entry<K,V,R> entry(K key, Function<V,R> getter, R expected){ return new Entry<>(key,getter,expected); }

	/**
	 * Checks that a map contains the given entries.
	 * Expected entries can be defined using the {@link #entry} methods. 
	 * 
	 * @param <K>	type of map keys
	 * @param <V>	type of map values
	 * @param <R>	type of the expected values
	 * @param message	message in case of assertion failure
	 * @param map		the map to be checked
	 * @param entries	the sequence of entries to be used in the check
	 */
	@SafeVarargs
	static <K,V,R> void assertMapContains(String message, Map<K,V> map, Entry<K,V,R>... entries) {
		for(Entry<K,V,R> e : entries) {
			if(e.get != null)
				assertEquals(message + "For key " + e.k, e.v, e.get.apply(map.get(e.k)));
			else
				assertEquals(message + "For key " + e.k, e.v, map.get(e.k));
		}
	}
}
