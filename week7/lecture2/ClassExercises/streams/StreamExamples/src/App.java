import java.util.ArrayList;
import static java.util.stream.Collectors.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class App {

	static class Dummy<T> {
		private T dummyValue;

		public Dummy(T dummyValue) {
			this.dummyValue = dummyValue;
		}

		public T getDummy() {
			return dummyValue;
		}
	}

	public static void main(String[] args) {

		// Stream source generation

		// Method 1: using arrays
		String[] animals = { "lion", "rabbit", "monkey", "dog" };
		Stream<String> animalsStream = Arrays.stream(animals);
		animalsStream.forEach(System.out::println);
		System.out.println();

		// Method 2: directly from a list of parameters
		Stream<String> fruitsStream = Stream.of("Orange", "Lemon", "Banana");
		fruitsStream.forEach(System.out::println);

		System.out.println();

		// Method 3: from a collection
		List<Dummy<String>> dummyList = new LinkedList<>();
		dummyList.add(new Dummy<String>("jhgjdff"));
		dummyList.add(new Dummy<String>("jvvdfvd"));
		dummyList.add(new Dummy<String>("abhc"));
		Stream<Dummy<String>> dummyStream = dummyList.stream();
		// dummyStream.forEach(System.out::println);
		dummyStream.forEach(x -> System.out.println(x.getDummy()));

		System.out.println();

		// Method 4: source generation
		Stream<Double> randomDoubleStream = Stream.generate(Math::random);
		randomDoubleStream.limit(10).forEach(System.out::println);

		// Case 1: create an empty stream
		System.out.println();
		Stream emptyStream = Stream.empty();
		if (emptyStream.count() == 0)
			System.out.println("Ops, this stream is empty!");

		System.out.println();

		// Case 2: use a Supplier to generate the elements
		System.out.println();

		// another example with Supplier : generate a stream made of random strings
		// (ajck, zkdc, ldrkj)
		Random random = new Random();
		int low = 97; // a
		int high = 122; // z
		System.out.println();
		Stream<String> randomStringsStream = Stream.generate(
				() -> new StringBuilder().append((char) (random.nextInt(high - low) + low))
						.append((char) (random.nextInt(high - low) + low))
						.append((char) (random.nextInt(high - low) + low))
						.append((char) (random.nextInt(high - low) + low)).toString());

		randomStringsStream.limit(10).forEach(System.out::println);

		// Case 3: use an UnaryOperator interface and generate stream form a seed
		Stream<Integer> evenNumbersStream = Stream.iterate(0, i -> i + 2);
		evenNumbersStream.limit(10).forEach(System.out::println);
		System.out.println();

		// Intermediate operations examples
		// filter: apply a Predicate interface to verify a condition
		// sorted: default method of Stream<T> to perform sorting given a Comparator
		// interface
		// limit: stop considering elements, after a certain position
		// skip: skip first n elements in the stream
		// distinct: generate a stream containing only unique elements

		Stream<String> familyStream = Stream.of("Dad", "Mom", "grandmother", "uncle", "niece", "daughter");
		familyStream.filter(x -> x.contains("m")).forEach(System.out::println);

		Stream<Integer> numbersStream = Stream.of(9, 2, 33, 33, 78, 10, 23, 9, 2, 2);
		numbersStream.skip(1)
				.sorted()
				.filter(x -> x > 30 && x < 100)
				.distinct()
				.forEach(System.out::println);

		// Map examples

		// Example 1: print all dummies using map
		System.out.println();

		Stream<Dummy<Integer>> dummyIntStream = Stream.of(new Dummy<Integer>(9), new Dummy<Integer>(5),
				new Dummy<Integer>(3));
		dummyIntStream.map(Dummy::getDummy).forEach(System.out::println);

		System.out.println();

		// Example 2: map each String in an array of String to their length
		Stream<String> vegetablesStream = Stream.of("pumpkin", "carrot", "onion", "pumpkin");
		vegetablesStream.map(String::length).distinct().forEach(System.out::println);
		System.out.println();

		// Example 3: two level mapping, first from Dummy object to String, then int
		// mapping to string length
		Stream<Dummy<String>> anotherDummyStream = dummyList.stream();
		anotherDummyStream.map(Dummy::getDummy).map(String::length).map(x -> x * 10).forEach(System.out::println);

		System.out.println();

		// Try it out1: mapping strings to their length after filtering
		Stream<String> yetAnotherDummyStream = Stream.of("Hello", "World", "GitHub", "Copilot", "a", "a", "bbbb", "bb",
			"bb", "bbb");
		yetAnotherDummyStream.filter(x -> x.length() > 4).distinct().map(String::length)
			.sorted(Comparator.reverseOrder()).forEach(System.out::println);
		/* Also can do sorted(Comparator.comparing(String::length).reversed()) */

		// Try it out2: map values in a Stream according to rule in a dictionary
		System.out.println();
		Stream<String> stringStream2 = Stream.of("apple", "banana", "pear", "avocado", "mandarin", "pineapple",
			"orange", "kiwi", "mango");
		stringStream2
			.map(x -> x.replace('a', '0').replace('e', '1').replace('i', '2').replace('o', '3').replace('u', '4'))
			.filter(x -> x.replaceAll("[^0-9]", "").length() > 2).forEach(System.out::println);
		System.out.println();

		Stream<String> stringStream3 = Stream.of("apple", "banana", "pear", "avocado", "mandarin", "pineapple",
			"orange", "kiwi", "mango");
		Map<Character, Character> encodingMap = new HashMap<>();
		encodingMap.put('a', '0');
		encodingMap.put('e', '1');
		encodingMap.put('i', '2');
		encodingMap.put('o', '3');
		encodingMap.put('u', '4');

		Function<String, String> encoder;
		encoder = (str) -> {
		    StringBuffer encr = new StringBuffer(str);
		    for (int i = 0; i < encr.length(); i++) {
			Character c = encr.charAt(i);
			if (encodingMap.containsKey(c)) {
			    encr.setCharAt(i, encodingMap.get(c));
			}
		    }
		    return encr.toString();
		};

		stringStream3.map(encoder).filter(s -> {
			int count = 0;
			for(int i = 0; i<s.length(); i++){
				if(Character.isDigit(s.charAt(i))){
					count++;
				}
			}
			return count>2;
		}).forEach(System.out::println);
		;

		System.out.println();

		// END LESSON 2/05 FIRST HALF

		Person[] personsArray = {new Person("Alice",37,Arrays.asList("Running","Meditating")),
								new Person("Bob",27,Arrays.asList("Walking","Fishing")),
								new Person("Marco",18,Arrays.asList("Fishing","Meditating")),
								new Person("John",25,Arrays.asList("Driving","Fishing"))};

		Stream<Person> personStream = Arrays.stream(personsArray);
								
		personStream.map(Person::getHobbies).flatMap(Collection::stream).distinct().forEach(System.out::println);
		System.out.println();

		List<String> sentences = new LinkedList<>();
		sentences.add("The quick brown fox");
		sentences.add("Jumps over the lazy dog");
		sentences.add("The dog sleeps");

		Stream<String> distinctWords = sentences.stream();
		distinctWords.flatMap(sentence -> Arrays.stream(sentence.split(" "))).map(String::toLowerCase).distinct().forEach(System.out::println);
		System.out.println();

		Stream<String> againStringStream = Stream.of("Panda","Sunglasses","Kyoto");
		Stream<String> againStringStream2 = Stream.of("Panda","Sunglasses","Kyoto");
		Optional<String> anyString = againStringStream.findAny();
		Optional<String> firstString = againStringStream2.findFirst();

		System.out.println(anyString.orElse("Empty"));
		System.out.println(firstString.orElse("Empty"));
		System.out.println();

		Optional<String> minString = Stream.of("Beet","Bears","Battlestar galactica").min(Comparator.comparing(String :: length));
		System.out.println(minString.orElse("Empty"));
		System.out.println();

		// Count //

		Stream<Person> againPersonStream = Arrays.stream(personsArray);
		long countMeditating = againPersonStream.map(Person::getHobbies).flatMap(Collection::stream).filter(s->s.equals("Meditating")).count();
		System.out.println(countMeditating);

		System.out.println();

		//AnyMatch//

		boolean somebodyRuns = Arrays.stream(personsArray).map(Person::getHobbies).flatMap(Collection::stream).anyMatch(h->h.equals("Running"));
		boolean somebodyReads = Arrays.stream(personsArray).map(Person::getHobbies).flatMap(Collection::stream).anyMatch(h->h.equals("Reading"));

		System.out.println(somebodyRuns);
		System.out.println(somebodyReads);

	}

}
