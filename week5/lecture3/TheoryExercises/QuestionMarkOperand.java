public class QuestionMarkOperand {
    public static void main(String[] args) {
        // Example lambda function using the "?" operand
        MyInterface myInterface = (x, y) -> (x > y) ? x : y;
        
        // Testing the lambda function
        int result1 = myInterface.getMax(5, 10);
        int result2 = myInterface.getMax(15, 7);
        
        System.out.println("Max value: " + result1); // Output: Max value: 10
        System.out.println("Max value: " + result2); // Output: Max value: 15
    }
    
    // Functional interface with a single abstract method
    interface MyInterface {
        int getMax(int a, int b);
    }
}
