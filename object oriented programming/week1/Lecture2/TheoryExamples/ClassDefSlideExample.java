package Lecture2.TheoryExamples;

/**
 * The ClassObjects class represents a person with a name and age.
 */
// classes define the common structure of a set of objects. (Object descriptor)
public class ClassDefSlideExample {

    String color;
    String brand;
    boolean turnedOn;

    void turnOn(){
        turnedOn = true;
    }

    void paint (String newCol){
        color = newCol;
    }

    void printState(){
        System.out.println("Car " + brand + " " + color);
        System.out.println("the engine is " + (turnedOn ? "on" : "off"));
    }

    public static void main(String[] args) {
        // Create an instance of ClassObjects2
        ClassDefSlideExample car = new ClassDefSlideExample();

        // Set the car's brand and color
        car.brand = "Toyota";
        car.color = "Red";

        // Turn on the car
        car.turnOn();

        // Print the car's state
        car.printState();

        // Paint the car with a new color
        car.paint("Blue");

        // Print the car's state again
        car.printState();
    }
}
