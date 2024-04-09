package week3.lecture2.TheoryExercises;

public class StaticMethods {
    public static void main(String[] args) {
        // Calling a static method directly from the class
        int sum = MathUtils.add(5, 3);
        System.out.println("Sum: " + sum);

        // Creating an object of the class to call a non-static method
        MathUtils mathUtils = new MathUtils();
        int product = mathUtils.multiply(4, 2);
        System.out.println("Product: " + product);
    }
}

class MathUtils {
    public static int add(int a, int b) {
        return a + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }
}
