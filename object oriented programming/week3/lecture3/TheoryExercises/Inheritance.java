package week3.lecture3.TheoryExercises;

// Parent class
class Vehicle {
    protected String brand;

    public Vehicle(String brand) {
        this.brand = brand;
    }

    public void drive() {
        System.out.println("Driving the " + brand);
    }
}

// Child class inheriting from Vehicle
class Car extends Vehicle {
    private int numOfSeats;

    public Car(String brand, int numOfSeats) {
        super(brand);
        this.numOfSeats = numOfSeats;
    }

    public void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Number of seats: " + numOfSeats);
    }
}

public class Inheritance {
    public static void main(String[] args) {
        Car car = new Car("Toyota", 5);
        car.drive(); // Output: Driving the Toyota
        car.displayInfo(); // Output: Brand: Toyota, Number of seats: 5
    }
}
