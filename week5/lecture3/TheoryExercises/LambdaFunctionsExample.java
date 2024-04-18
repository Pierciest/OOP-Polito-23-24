import java.util.Arrays;
import java.util.List;
public class LambdaFunctionsExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Jane", "Alice", "Bob");

        // Lambda function example
        names.forEach(name -> System.out.println(name));

        // Method reference example using ::
        names.forEach(System.out::println);

        //without the :: operand and lambda functions
                // Traditional for loop example
        for (String name : names) {
            System.out.println(name);
        }
    }
}
