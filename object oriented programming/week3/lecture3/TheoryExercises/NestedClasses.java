package week3.lecture3.TheoryExercises;

public class NestedClasses {
    private int outerVariable = 10;

    public void outerMethod() {
        System.out.println("Outer method");
    }

    // Nested class
    public class InnerClass {
        private int innerVariable = 20;

        public void innerMethod() {
            System.out.println("Inner method");
        }

        public void accessOuter() {
            System.out.println("Outer variable: " + outerVariable);
            outerMethod();
        }
    }
}
