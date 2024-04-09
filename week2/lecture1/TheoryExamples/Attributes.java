package week2.lecture1.TheoryExamples;

public class Attributes {
    // Attributes are like variables defined sby Type or Name
    private String name; // Attribute of type String to store the name
    private int age; // Attribute of type int to store the age

    // Constructor to initialize the attributes
    public Attributes(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter methods to access the attributes
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Setter methods to modify the attributes
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Main method to demonstrate the usage of attributes
    public static void main(String[] args) {
        Attributes person = new Attributes("John Doe", 25);
        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());

        person.setName("Jane Smith");
        person.setAge(30);
        System.out.println("Updated Name: " + person.getName());
        System.out.println("Updated Age: " + person.getAge());
    }
}
