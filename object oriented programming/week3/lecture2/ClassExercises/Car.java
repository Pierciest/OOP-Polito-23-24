package week3.lecture2.ClassExercises;

public class Car {
    //If instead of static int we use int, than for all the classes that we create the attribute would be unique
    //When we use static int, there is one attribute for all the created classes.
    static int countBuiltCars = 0;

    public Car(){
        countBuiltCars++;
    }

    public void printBuiltCars(){
        System.out.println(countBuiltCars);
    }
}
