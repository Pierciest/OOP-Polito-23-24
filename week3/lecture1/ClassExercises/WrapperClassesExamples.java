package week3.lecture1.ClassExercises;

public class WrapperClassesExamples {
    public static void main(String[] args){
        System.out.println("First part of the class.");

        //AUTOBOXING
        Integer n1 = new Integer(1);
        String s1 = "all strings are objects";
        int n2 = 2;
        int n3 = 2;
        Ball b1 = new Ball("Red"); //even if the object is empty, it is still defined hence no errors but you can not sum it as it is not defined as a string, int etc.
        Ball b2 = new Ball("Red"); 
        Ball b3 = b2;
        Integer sum = n1 + n2;
        String n1AsString = n1.toString();

        System.out.println("A: " + sum + " " + n1AsString);
        System.out.println("A: " + n1AsString.getClass().getSimpleName());
        System.out.println(b1.getColor());

        System.out.println(b1.getColor()==b2.getColor()); //Colors are the same hence they are the same string, so True
        System.out.println(n2==n3); //The numbers are same so True
        System.out.println(b2==b3); //Balls are same because we defined the object Ball b3 by b2
        System.out.println(b1==b3); //Even though the colors are same and it is the only attribute, they are different objects hence not equal.

        System.out.println("Second part of the class.");

        // UTILITY METHODS - Static methods of the Wrapper Class
        // 1. valueOf() - creates a Wrapper object for a given primitive or String

        Integer n8 = 8;
        Integer n9 = Integer.valueOf(n8);
        System.out.println(n9);

        String s8 = "8"; // It has to be an integer in string form to be able to convert to integer with ValueOf
        Integer n10 = Integer.valueOf(s8); //We take the value of the string as an integer.

        System.out.println(n10);



        // 2. xxxvalue() - gets the primitive for the given Wrapper Object

        int s9 = n10.intValue();
        // 3. parseXxx() - converts Strings to primitive

        int num1 = Integer.parseInt(s8);

        // 4. toString() - converts the Wrapper object or primitive to String

        String str1 = Integer.toString(num1);
        System.out.println(str1);
    }
}
