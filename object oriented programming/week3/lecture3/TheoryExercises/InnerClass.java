package week3.lecture3.TheoryExercises;

public class InnerClass {
    private int outerVariable;

    public InnerClass(int outerVariable) {
        this.outerVariable = outerVariable;
    }

    public void outerMethod() {
        System.out.println("Outer method");
    }

    public class Inner {
        private int innerVariable;

        public Inner(int innerVariable) {
            this.innerVariable = innerVariable;
        }

        public void innerMethod() {
            System.out.println("Inner method");
            System.out.println("Outer variable: " + outerVariable);
            System.out.println("Inner variable: " + innerVariable);
        }
    }
}
