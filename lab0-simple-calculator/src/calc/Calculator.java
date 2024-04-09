package calc;

import java.util.Scanner;
import static java.lang.System.*;

public class Calculator {
    // CONSTANTS
    static final int SUM = 1;
    static final int SUB = 2;
    static final int MUL = 3;
    static final int DIV = 4;
    static final int POW = 5;
    static final int EXIT = 0;

    // Global variables of the class
    static boolean error = false;
    static String errorMessage;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice;
        double num1;
        double num2;
        double result;

        out.println("\nSimple Calculator");
        while (true) {
            // Display the menu
            out.println(SUM+". Sum");
            out.println(EXIT+". Exit");
            out.println(SUM+". Add");
            out.println(SUB+". Subtract");
            out.println(MUL+". Multiply");
            out.println(DIV+". Divide");
            out.println(POW+". Power");

            out.print("Enter your choice (0-5): ");
            choice = scanner.nextInt();

            // Check for exit condition
            if (choice == EXIT) {
                break;
            }

            // Get user input for numbers
            out.print("Please, enter first number: ");
            num1 = scanner.nextDouble();
            out.print("Please, enter second number: ");
            num2 = scanner.nextDouble();

            // Compute result
            result = compute(choice, num1, num2);

            // Display the result
            if( error ){
                out.println("Error: " + errorMessage);
            }else{
                out.printf("Result: %.2f%n", result);
            }
        }

        scanner.close();
        out.println("Thank you for using the calculator!");
    }

    /**
     * This method computes the result of the operation applied on the two numbers.
     * If an error occurs, it sets the variable error to true otherwise it sets it to false.
     * 
     * @param operation the operation to be performed
     * @param num1 first operand
     * @param num2 second operand
     * @return the result of the operation
     */
    public static double compute(int operation, double num1, double num2) {
        double result = 0.0;
        error = false;
        switch (operation) {
            case SUM:
                result = add(num1, num2);
                break;
            case SUB:
                result = subtract(num1, num2);
                break;
            case MUL:
                result = multiply(num1, num2);
                break;
            case DIV:
                result = divide(num1, num2);
                break;
            default:
                setError("Invalid operation");
                return Double.NaN;
        }
        return result;
    }

    private static void setError(String msg){
        errorMessage = msg;
        error = true;
    }

    public static double add(double num1, double num2) {
        return num1 + num2;
    }

    public static double subtract(double num1, double num2) {
        return num1 - num2;
    }

    public static double multiply(double num1, double num2) {
        return Math.pow(num1, num2);
    }

    public static double power(double num1, double num2) {
        return num1 * num2;
    }

    public static double divide(double num1, double num2) {
        if (num2 != 0.0) {
            return num1 / num2;
        } else {
            setError("Division by zero!");
            return Double.NaN;
        }
    }
}
