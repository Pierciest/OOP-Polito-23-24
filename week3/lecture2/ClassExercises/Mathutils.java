package week3.lecture2.ClassExercises;

public class Mathutils {
    static int a;
    static int b;

    public static int sum(int a, int b ){
        Mathutils.a = a;
        Mathutils.b = b;

        return a+b;
    }

    public static int diff(int a, int b ){
        Mathutils.a = a;
        Mathutils.b = b;

        return a-b;
    }

}
