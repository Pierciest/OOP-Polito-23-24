import java.util.ArrayList;
import java.util.Iterator;

public class Iterators {
    public static void main(String[] args) {
        // Create an ArrayList of integers
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(3);
        numbers.add(2);
        numbers.add(4);
        numbers.add(5);

        // Create an iterator object and initialize it with the ArrayList
        Iterator<Integer> iterator = numbers.iterator();

        // Iterate over the elements using a for loop
        for (int i = 0; i < numbers.size(); i++) {
            int number = numbers.get(i);
            System.out.println(number);
        }

        System.out.println();

        // Iterate over the elements using the iterator
        while (iterator.hasNext()) {
            int number = iterator.next();
            System.out.println(number);
        }
            
    }
}

/*
 * Iterators are important in Java because they provide a way to traverse and
 * manipulate collections of objects. They offer several advantages over normal
 * for/while loops:
 * 
 * Simplicity: Iterators provide a simple and consistent way to iterate over
 * collections, regardless of their underlying implementation.
 * 
 * Readability: Iterators make the code more readable by separating the
 * iteration logic from the collection itself. This improves code
 * maintainability and reduces the chances of introducing bugs.
 * 
 * Flexibility: Iterators allow for safe removal of elements during iteration
 * using the remove() method. This is not possible with normal for/while loops
 * without additional complexity.
 * 
 * Efficiency: Iterators are generally more efficient than traditional loops,
 * especially when dealing with large collections. They minimize memory usage
 * and provide optimized algorithms for different collection types.
 * 
 * Overall, iterators provide a standardized and efficient way to iterate over
 * collections, making code more readable, maintainable, and flexible.
 */