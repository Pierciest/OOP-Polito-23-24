package Lecture2.TheoryExamples;

public class PassingParameters {
    // Parameters are always passed by value, they can be primitive types or object references.
    // Only the object reference is copied not the whole object.
    public static void main(String[] args) {
        int number = 10;
        String name = "John";

        // Passing primitive types
        number = modifyNumber(number);
        System.out.println("Number after modification: " + number);

        // Passing object references
        name = modifyName(name);
        System.out.println("Name after modification: " + name);
    }

    public static int modifyNumber(int num) {
        num = 20;
        return num;
    }

    public static String modifyName(String str) {
        str = "Jane";

        return str;
    }

}
