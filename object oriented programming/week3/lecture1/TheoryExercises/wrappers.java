package week3.lecture1.TheoryExercises;

public class wrappers {
    public static void main(String[] args) {
        // Wrappers allow us to work with primitive data types as objects
        
        // Example 1: Integer wrapper class
        Integer num1 = new Integer(10); // Creating an Integer object
        int num2 = num1.intValue(); // Converting Integer object to int
        System.out.println("num1: " + num1);
        System.out.println("num2: " + num2);
        
        // Example 2: Double wrapper class
        Double num3 = new Double(3.14); // Creating a Double object
        double num4 = num3.doubleValue(); // Converting Double object to double
        System.out.println("num3: " + num3);
        System.out.println("num4: " + num4);
        
        // Example 3: Boolean wrapper class
        Boolean flag1 = new Boolean(true); // Creating a Boolean object
        boolean flag2 = flag1.booleanValue(); // Converting Boolean object to boolean
        System.out.println("flag1: " + flag1);
        System.out.println("flag2: " + flag2);
    }
}
