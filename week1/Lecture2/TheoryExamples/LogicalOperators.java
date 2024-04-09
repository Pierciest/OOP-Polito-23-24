package Lecture2.TheoryExamples;

public class LogicalOperators {
    public static void main(String[] args) {
        int x = 5;
        int y = 10;
        boolean condition1 = (x > 0) && (y < 20); // Logical AND operator
        boolean condition2 = (x == 5) || (y == 15); // Logical OR operator
        boolean condition3 = !(x < 0); // Logical NOT operator

        if (condition1 && condition2) {
            System.out.println("Both condition1 and condition2 are true");
        } else if (condition1 || condition3) {
            System.out.println("Either condition1 or condition3 is true");
        } else {
            System.out.println("None of the conditions are true");
        }

        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0 || i % 3 == 0) {
                System.out.println(i + " is divisible by 2 or 3");
            }
        }
    }
}
