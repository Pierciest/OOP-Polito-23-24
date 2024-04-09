package Lecture2.TheoryExamples;

/**
 * The ClassObjects class represents a person with a name and age.
 */
// classes define the common structure of a set of objects. (Object descriptor)

public class ClassObjects {

    // Attributes
    // Attributes describe the data that can be stored within objects
    // They are like variables, defined by:
    // Type and Name
    // Each object has its own copy of the attributes
    private String name; // Name of the person
    private int age; // Age of the person

    // Constructor
    public ClassObjects(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Method
    //Methods represent the messages that an object can accept
    //They can accept arguments, in the classDefSlideExample, paint, turnOn, in this example displayInfo.
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }

    public static void main(String[] args) {
        // Creating objects using the constructor
        ClassObjects obj1 = new ClassObjects("John", 25);
        ClassObjects obj2 = new ClassObjects("Jane", 30);

        // Calling the method on objects
        obj1.displayInfo();
        obj2.displayInfo();
    }
}


    
