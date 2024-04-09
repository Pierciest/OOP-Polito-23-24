package week2.lecture1.TheoryExamples;

public class Objects {
    //An object is identified by Class which defines its
    //Structure
    //State (Values of attributes)
    //Internal unique identifier.
    //An object can be accessed through a references
    //Any object can be pointed to by one or more references. (Aliasing)
    public static void main(String[] args) {
        // Creating an object of the Car class
        Car myCar = new Car("Tesla", "Model S", "Red");
        //The keyword new creates a new instance of the specific class
        //Allocates the required memory in the heap
        //An object is created and the "pointer" stored into the reference myCar
        Car sikMobil = myCar;

        //I copied the myCar into sikMobil, now every change I apply to myCar or sikMobil would effect each other.
        // Accessing the properties of the car object
        System.out.println("Brand: " + myCar.brand);
        System.out.println("Model: " + myCar.model);
        System.out.println("Color: " + myCar.color);

        // Calling a method of the car object
        myCar.startEngine();
        sikMobil.startEngine();
    }
}

class Car {
    String brand;
    String model;
    String color;

    // Constructor to initialize the car object
    public Car(String brand, String model, String color) {
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    // Method to start the car's engine
    public void startEngine() {
        System.out.println("Engine started!");
    }
}

    
