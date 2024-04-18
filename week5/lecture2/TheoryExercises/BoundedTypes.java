public class BoundedTypes {
    // Generic method that accepts only Number or its subclasses
    public static <T extends Number> void printNumber(T number) {
        System.out.println("Number: " + number);
    }
    public static <T> void printNumber(T number) {
        if (number instanceof String) {
            throw new IllegalArgumentException("Can not print a String");
        }
        System.out.println("Number: " + number);
    }


    public static void main(String[] args) {
        Integer integerNumber = 10;
        Double doubleNumber = 3.14;
        String string = "Hello";

        // Calling the generic method with different types
        printNumber(integerNumber); // Number: 10"
        printNumber(doubleNumber); // Number: 3.14
        // printNumber(string); // Compilation error: String is not a subclass of Number
        printNumber(string);
    }

}
