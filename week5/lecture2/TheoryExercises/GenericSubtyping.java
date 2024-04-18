import java.util.ArrayList;
import java.util.List;

public class GenericSubtyping {
    public static void main(String[] args) {
        // Creating a list of integers
        List<Number> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);

        // Creating a list of numbers
        List<Number> numberList = new ArrayList<>();
        numberList.add(4);
        numberList.add(5.5);
        numberList.add(6);

        // We can assign a list of integers to a list of numbers
        numberList = integerList;

        // We can add a double to the list of numbers
        numberList.add(7.7);

        // However, we cannot assign a list of numbers to a list of integers
        // This will result in a compilation error
        // integerList = numberList;

        // Print the lists
        System.out.println("Integer List: " + integerList);
        System.out.println("Number List: " + numberList);
    }
}
    
