package TheoryExercises;
import java.util.ArrayList;

public class ArrayLists {
public class ArrayListExample {
    public static void main(String[] args) {
        // Create an ArrayList to store integers
        ArrayList<Integer> numbers = new ArrayList<>();

        // Add elements to the ArrayList
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);

        // Access elements in the ArrayList
        System.out.println("Element at index 0: " + numbers.get(0));
        System.out.println("Element at index 1: " + numbers.get(1));
        System.out.println("Element at index 2: " + numbers.get(2));

        // Update an element in the ArrayList
        numbers.set(1, 25);
        System.out.println("Updated element at index 1: " + numbers.get(1));

        // Remove an element from the ArrayList
        numbers.remove(0);
        System.out.println("After removing element at index 0: " + numbers);

        // Check if the ArrayList contains a specific element
        System.out.println("Does the ArrayList contain 30? " + numbers.contains(30));

        // Get the size of the ArrayList
        System.out.println("Size of the ArrayList: " + numbers.size());

        // Iterate over the elements in the ArrayList
        System.out.println("Elements in the ArrayList:");
        for (int number : numbers) {
            System.out.println(number);
        }
    }
}
}
