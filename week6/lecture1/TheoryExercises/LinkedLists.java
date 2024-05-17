package TheoryExercises;
import java.util.LinkedList;

public class LinkedLists {
public class LinkedListExample {
    public static void main(String[] args) {
        // Create a LinkedList
        LinkedList<String> linkedList = new LinkedList<>();

        // Add elements to the LinkedList using addFirst and addLast
        linkedList.addFirst("First");
        linkedList.addLast("Last");

        // Get the first and last elements using getFirst and getLast
        String firstElement = linkedList.getFirst();
        String lastElement = linkedList.getLast();

        System.out.println("First Element: " + firstElement);
        System.out.println("Last Element: " + lastElement);

        // Remove an element from the LinkedList using remove
        linkedList.removeFirst();

        // Get an element at a specific index using get
        String elementAtIndex = linkedList.get(0);

        System.out.println("Element at index 0: " + elementAtIndex);
    }
}
}
